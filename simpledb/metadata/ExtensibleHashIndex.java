package metadata;

import index.Index;
import query.Constant;
import record.RID;
import record.Schema;
import tx.Transaction;

public class ExtensibleHashIndex implements Index {

	public ExtensibleHashIndex(String idxname, Schema sch, Transaction tx) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beforeFirst(Constant searchkey) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Constant dataval, RID datarid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
