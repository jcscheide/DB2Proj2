package index.extensible;

import java.util.ArrayList;

import index.Index;
import query.Constant;
import query.TableScan;
import record.RID;
import record.Schema;
import record.TableInfo;
import tx.Transaction;

public class ExtensibleHashIndex implements Index {

	private LocalDepthIndex local;
	private Schema globalWithLocalDepth;
	private Schema globalWithLocalIndex;
	private String t1;
	private String t2;
	private Transaction tx;
	private TableScan ts = null;
	private Constant searchkey = null;
	private boolean isInsert = true;
	
	public ExtensibleHashIndex(String idxname, Schema sch, Transaction tx) {
		this.local = new LocalDepthIndex(idxname,sch,tx);
		this.tx = tx;
		this.t1 = "globalWithLocalDepth";
		this.t2 = "globalWithLocalIndex";
		this.globalWithLocalDepth = new Schema();
		globalWithLocalDepth.addIntField("GlobalIndex");
		globalWithLocalDepth.addIntField("LocalIndex");
		
		//Create a TableInfo tracking the global index and local depth
		TableInfo ti = new TableInfo(t1, globalWithLocalDepth);
		ts = new TableScan(ti,tx);
		ts.insert();
		ts.setInt("GlobalIndex", 0);
		ts.setInt("LocalDepth", 0);
		ts.close();
		//Create another one tracking the global and local indices
		ti = new TableInfo(t2, globalWithLocalIndex);
		ts = new TableScan(ti,tx);
		ts.insert();
		ts.setInt("GlobalIndex", 0);
		ts.setInt("LocalIndex", 0);
		ts.close();
	}

	@Override
	public void beforeFirst(Constant searchkey) {
		close();
		this.searchkey = searchkey;
		int gd = getGlobalDepth();
		int gi = (int) (searchkey.hashCode() % Math.pow(2, gd));
		System.out.println("Global index: " + gi);
		int li = Integer.MIN_VALUE;
		TableInfo ti = new TableInfo(t1, globalWithLocalIndex);
		ts = new TableScan(ti,tx);
		while(ts.next()){
			if(ts.getInt("GlobalIndex") == gi){
				li = ts.getInt("LocalIndex");
			}
		}
		ts.close();
		System.out.println("Local index: " + li);
		if(local.isFull() && this.isInsert){
			System.out.println("Bucket is full, we need to insert");
			int ld = 0;
			ti = new TableInfo(t1, globalWithLocalDepth);
			ts = new TableScan(ti,tx);
			ts.beforeFirst();
			while(ts.next()){
				if(ts.getInt("GlobalIndex") == gi){
					ld = ts.getInt("LocalDepth");
				}
			}
			if(ld == gd){
				System.out.println("Increasing global depth from " + this.getGlobalDepth() + "to " + DoubleGlobalIndex()) ;
			}
			System.out.println("Splitting full bucket");
			li = split(li);
			
		}
		local.setSearchkey(searchkey);
		local.beforeFirst(li);

	}

	private int split(int li) {
		int newindex = (int) (li + Math.pow(2, getGlobalDepth()) - 1);
		local.beforeFirst(li);
		ArrayList<Constant> localval = local.LocalValue();
		ArrayList<RID> rid = local.Rid();
		int nor = rid.size();
		int depth = Integer.MIN_VALUE;
		local.getTs().beforeFirst();
		for(int i = 0; i < nor; i++){
			delete(localval.get(i), rid.get(i));
		}
		TableInfo ti = new TableInfo(t1, globalWithLocalDepth);
		TableScan ts = new TableScan(ti, tx);
		
		while(ts.next()){
			if(ts.getInt("GlobalIndex") == newindex){
				depth = ts.getInt("LocalDepth");
				ts.delete();
				break;
			}
		}
		while(ts.next()){
			if(ts.getInt("GlobalIndex") == li){
				ts.delete();
				break;
			}
		}
		ts.insert();
		ts.setInt("GlobalIndex", newindex);
		ts.setInt("LocalDepth", depth+1);
		ts.setInt("GlobalIndex", li);
		ts.setInt("LocalDepth", depth+1);
		ts.close();
		ti = new TableInfo(t1, globalWithLocalIndex);
		ts = new TableScan(ti,tx);
		while (ts.next()){
			if (ts.getInt("GlobalIndex") == newindex){
				ts.delete();
			}
		}
		
		ts.insert();
		ts.setInt("GlobalIndex", newindex);
		ts.setInt("LocalIndex", newindex);
		ts.close();
		for(int i = 0; i < nor; i++){
			insert(localval.get(i), rid.get(i));
		}
		int gd = getGlobalDepth();
		int gi = (int) (searchkey.hashCode() % Math.pow(2, gd));
		int local = Integer.MIN_VALUE;
		ti = new TableInfo(t1, globalWithLocalIndex);
		ts = new TableScan(ti,tx);
		while(ts.next()){
			if(ts.getInt("GlobalIndex") == gi){
				local = ts.getInt("LocalIndex");
			}
		}
		ts.close();
		return local;
		
	}

	private int DoubleGlobalIndex() {
		int maxglobal = (int) Math.pow(2,getGlobalDepth());
		int newgi = 0;
		for(int i =0; i < maxglobal; i++){
			TableInfo ti = new TableInfo(t1, globalWithLocalDepth);
			TableScan ts = new TableScan(ti,tx);
			newgi = i + maxglobal;
			int olddepth = Integer.MIN_VALUE;
			while(ts.next()){
				if(ts.getInt("GlobalIndex") == i){
					olddepth = ts.getInt("LocalDepth");
				}
			}
			ts.insert();
			ts.setInt("GlobalIndex", newgi);
			ts.setInt("LocalDepth", olddepth);
			ts.close();
			ti = new TableInfo(t1, globalWithLocalIndex);
			ts = new TableScan(ti, tx);
			int oldli = Integer.MIN_VALUE;
			while(ts.next()){
				if(ts.getInt("GlobalIndex") == i)
					oldli = ts.getInt("LocalIndex");
			}
			ts.insert();
			ts.setInt("GlobalIndex", newgi);
			ts.setInt("LocalIndex", oldli);
			ts.close();
		}
		return newgi;
	}

	private int getGlobalDepth() {
		TableInfo ti = new TableInfo(t1, globalWithLocalDepth);
		TableScan ts = new TableScan(ti, tx);
		int count = 0;
		while (ts.next()) {
			count++;
		}
		ts.close();
		return (int) (Math.log(count) / Math.log(2));
	}

	@Override
	public boolean next() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RID getDataRid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Constant dataval, RID datarid) {
		System.out.println("Inserting value: " + dataval + " RID: " + datarid);
		this.isInsert = true;
		beforeFirst(dataval);
		local.insert(dataval, datarid);

	}

	@Override
	public void delete(Constant dataval, RID datarid) {
		System.out.println("Deleting value: " + dataval + " RID: " +datarid);
		this.isInsert = false;
		beforeFirst(dataval);
		local.delete(dataval, datarid);
	}

	@Override
	public void close() {
		if(ts != null)
			ts.close();

	}

}
