package Test;

import simpledb.buffer.AdvancedBufferMgr;
import simpledb.buffer.BufferMgr;
import simpledb.buffer.PageFormatter;
import simpledb.file.Block;
import simpledb.file.Page;

public class ToStringTests {

    public static void main(String []args){
        BufferMgr simple= new BufferMgr(5,0);
        BufferMgr moderate = new BufferMgr(5,1);
        BufferMgr advanced = new BufferMgr(5,2);
        System.out.println(simple.toString());
        System.out.println(moderate.toString());
        System.out.println(advanced.toString());
    }
}
