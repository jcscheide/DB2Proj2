package WarmUp;
import java.sql.*;
import simpledb.remote.SimpleDriver;

public class SelectAllPatientsFromDoctorID {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Driver d = new SimpleDriver();
            conn = d.connect("jdbc:simpledb://localhost", null);
            Statement stmt = conn.createStatement();
            String qry = "select PName "
                    + "from patient "
                    + "where doctorID = 1";
            ResultSet rs = stmt.executeQuery(qry);

            // Step 3: loop through the result set
            while (rs.next()) {
                String Pname = rs.getString("PName");
                System.out.println(Pname);
            }
            rs.close();
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
