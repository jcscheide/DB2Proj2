package WarmUp;

import java.sql.*;

import simpledb.remote.SimpleDriver;
import simpledb.server.SimpleDB;

public class WarmUp {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Driver d = new SimpleDriver();
            conn = d.connect("jdbc:simpledb://localhost", null);
            Statement stmt = conn.createStatement();

            String s = "create table DOCTOR(doctorID int, deptID int, DName varchar(15), Specialty varchar(15))";
            stmt.executeUpdate(s);
            System.out.println("Table DOCTOR created.");
            s = "insert into DOCTOR(doctorID, deptID, DName, Specialty) values ";
            String[] doctorVals = {"(1, 1, 'Dr. Mike', 'Neurology')",
                    "(2, 2, 'Dr. Jake', 'Orthopedics')",
                    "(3, 3, 'Dr. Peter', 'Cardiac')",
                    "(4, 2, 'Dr. Sarah', 'Pediatrics')",
                    "(5, 4, 'Dr. Melissa', 'Oncology')",
                    "(6, 2, 'Dr. Jim', 'Orthopedics')",
                    "(7, 3, 'Dr. Hannah', 'Cardiac')",
                    "(8, 2, 'Dr. Felicia', 'Pediatrics')",
                    "(9, 4, 'Dr. Pringles', 'Oncology')",
                    "(10, 2, 'Dr. FruitLoops', 'Orthopedics')",
                    "(11, 3, 'Dr. LuckyCharms', 'Cardiac')",
                    "(12, 2, 'Dr. CocoPuffs', 'Pediatrics')",
                    "(13, 4, 'Dr. Goldfish', 'Oncology')"};
            for(String doctor : doctorVals)
                stmt.executeUpdate(s + doctor);
            System.out.println("DOCTOR records inserted.");

            s = "create table DEPT(deptId int, DName varchar(12))";
            stmt.executeUpdate(s);
            System.out.println("Table DEPT created.");

            s = "insert into DEPT(deptId, DName) values ";
            String[] deptvals = {"(1, 'Neurosci')",
                    "(2, 'OrthoPed')",
                    "(3, 'Cardiac')",
                    "(4, 'Oncology')"};
            for(String dept : deptvals)
                stmt.executeUpdate(s + dept);
            System.out.println("DEPT records inserted.");

            s = "create table PATIENT(patientID int, doctorID int, PName varchar(15))";
            stmt.executeUpdate(s);
            System.out.println("Table PATIENT created.");

            s = "insert into PATIENT(patientId, doctorID, PName) values ";
            String[] patientvals = {"(1, 1, 'Mike')",
                    "(2, 2, 'Rachael')",
                    "(3, 3, 'Hannah')",
                    "(4, 4, 'Juan')",
                    "(5, 2, 'Adam')",
                    "(6, 3, 'Ray')",
                    "(7, 4, 'Britney')",
                    "(8, 2, 'Skylar')",
                    "(9, 1, 'Jessie')",
                    "(10, 4, 'Ryan')",
                    "(11, 2, 'Simone')",
                    "(12, 3, 'Mark')",
                    "(13, 4, 'Maria')",
                    "(14, 2, 'Roger')",
                    "(15, 1, 'Missie')",
                    "(16, 4, 'George')",
                    "(17, 2, 'Scarlett')",
                    "(18, 3, 'Will')",
                    "(19, 4, 'Dakota')",
                    "(20, 2, 'Emma')",
                    "(21, 3, 'Derek')",
                    "(22, 1, 'Aaron')",
                    "(23, 1, 'Jim')",
                    "(24, 2, 'Sam')",
                    "(25, 3, 'Steve')",
                    "(26, 4, 'Jackson')",
                    "(27, 2, 'Katie')",
                    "(28, 3, 'Alissa')",
                    "(29, 4, 'Lauren')",
                    "(30, 2, 'Maddy')",
                    "(31, 1, 'Matilda')",
                    "(32, 4, 'Cynthia')",
                    "(33, 2, 'Ciara')",
                    "(34, 3, 'Cierra')",
                    "(35, 4, 'Chloe')",
                    "(36, 2, 'Crystal')",
                    "(37, 1, 'Noah')",
                    "(38, 4, 'Nelly')",
                    "(39, 2, 'Nelson')",
                    "(40, 3, 'Neel')",
                    "(41, 4, 'Chuck')",
                    "(42, 2, 'Tyler')",
                    "(43, 3, 'Jake')",
                    "(44, 1, 'Dustin')"};
            for(String p : patientvals)
                stmt.executeUpdate(s + p);
            System.out.println("PATIENT records inserted.");
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
