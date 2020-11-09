package mySQL.lab6;

import java.sql.*;

public class BT1 {
    public static void main(String[] args) {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop", "root", "");
                Statement st = con.createStatement()
        ) {
            String strUpdate = "update books set price = price*0.7, qty = qty+1 where id = 1001";
            System.out.println("The SQL Statement is: " + strUpdate);
            int countUpdated = st.executeUpdate(strUpdate);
            System.out.println(countUpdated + " records affected.");

            String strSelect = "select * from books where id = 1001";
            System.out.println("The SQL Statement is: " + strSelect);
            ResultSet rs = st.executeQuery(strSelect);
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", "
                        + rs.getString("author") + ", "
                        + rs.getString("title") + ", " +
                        rs.getDouble("price") + ", " +
                        rs.getInt("qty"));
            }

            strUpdate = "update books set price = price * 1.5 where title = 'Java for Dummies'";
            System.out.println(strUpdate);
            countUpdated = st.executeUpdate(strUpdate);
            System.out.println(strUpdate + " records afftected.");

            strUpdate = "update books set qty = 0 where title = 'A Teaspoon of Java'";
            System.out.println(strUpdate);
            countUpdated = st.executeUpdate(strUpdate);
            System.out.println(strUpdate + " records afftected.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
