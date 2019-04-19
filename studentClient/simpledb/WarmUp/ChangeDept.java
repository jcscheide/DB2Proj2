package WarmUp;

import java.sql.*;
import simpledb.remote.SimpleDriver;


public class ChangeDept {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Driver d = new SimpleDriver();
            conn = d.connect("jdbc:simpledb://localhost", null);
            Statement stmt = conn.createStatement();

            String cmd = "update DOCTOR set deptID=2 "
                    + "where doctorID = 1";
            stmt.executeUpdate(cmd);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn != null)
                    conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
