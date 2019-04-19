package Test;

import simpledb.remote.SimpleDriver;

import java.sql.*;

public class MainTester {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Driver d = new SimpleDriver();
            conn = d.connect("jdbc:simpledb://localhost", null);
            Statement stmt = conn.createStatement();


            long startTime = System.nanoTime();
            String qry = "select PName "
                    + "from patient";
            stmt.executeQuery(qry);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("The time it took to select all patients was " + String.valueOf(duration));
            System.out.println("Let's insert some more patients and do that again");

            startTime = System.nanoTime();
            qry = "insert into PATIENT(patientId, doctorID, PName) values ";
            String[] patientvals = {"(45, 3, 'Steve')",
                    "(46, 4, 'Jackson')",
                    "(47, 2, 'Katie')",
                    "(48, 3, 'Alissa')",
                    "(49, 4, 'Lauren')",
                    "(50, 2, 'Maddy')",
                    "(51, 1, 'Matilda')",
                    "(52, 4, 'Cynthia')",
                    "(53, 2, 'Ciara')",
                    "(54, 3, 'Cierra')",
                    "(55, 4, 'Chloe')",
                    "(56, 2, 'Crystal')",
                    "(57, 1, 'Noah')",
                    "(58, 4, 'Nelly')",
                    "(59, 2, 'Nelson')",
                    "(60, 3, 'Neel')",
                    "(61, 4, 'Chuck')",
                    "(62, 2, 'Tyler')",
                    "(63, 3, 'Jake')",
                    "(64, 1, 'Dustin')"};
            for (String values : patientvals) {
                stmt.executeUpdate(qry + values);
            }

            endTime = System.nanoTime();
            duration = (endTime - startTime);
            System.out.println("Successfully inserted patients");
            System.out.println("The time it took to insert patients was " + String.valueOf(duration));
            System.out.println("Let's select all of these patients again");
            startTime = System.nanoTime();
            qry = "select PName "
                    + "from patient "
                    + "where doctorID = 2";
            stmt.executeQuery(qry);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            System.out.println("The time it took to select all patients was " + String.valueOf(duration));
            System.out.println("Let's do the exact same query to see how evictions went");
            startTime = System.nanoTime();
            qry = "select PName "
                    + "from patient "
                    + "where doctorID = 2";
            stmt.executeQuery(qry);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            System.out.println("The time it took to select all patients was " + String.valueOf(duration));
            System.out.println("Let's do the exact same query yet another time to see how evictions went");
            startTime = System.nanoTime();
            qry = "select PName "
                    + "from patient "
                    + "where doctorID = 2";
            stmt.executeQuery(qry);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            System.out.println("The time it took to select all patients was " + String.valueOf(duration));
            System.out.println("AND AGAIN!");
            startTime = System.nanoTime();
            qry = "select PName "
                    + "from patient "
                    + "where doctorID = 2";
            stmt.executeQuery(qry);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            System.out.println("The time it took to select all patients was " + String.valueOf(duration));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
