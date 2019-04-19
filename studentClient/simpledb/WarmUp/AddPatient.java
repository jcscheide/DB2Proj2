package WarmUp;
import java.sql.*;
import simpledb.remote.SimpleDriver;


public class AddPatient {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Driver d = new SimpleDriver();
            conn = d.connect("jdbc:simpledb://localhost", null);
            Statement stmt = conn.createStatement();
            String qry = "insert into PATIENT(patientId, doctorID, PName) values (8, 1, 'Jeff')";
            stmt.executeUpdate(qry);
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
