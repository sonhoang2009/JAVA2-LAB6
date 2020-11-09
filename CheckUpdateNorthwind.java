package mySQL.lab6;

public class CheckUpdateNorthwind {
    public static void main(String[] args) {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root","");
                Statement st = con.createStatement()
        ) {
            // Doi ten seafood va check update
            String strUpdate = "update categories\n" +
                    "    set CategoryName = 'SeaFoodVN'\n" +
                    "    where CategoryName = 'Seafood';";
            System.out.println("The SQL statement is: " + strUpdate);
            int countedUpdate = st.executeUpdate(strUpdate);
            System.out.println(countedUpdate + " records updated");

            String strCheckUpdate = "select * from categories;";
            System.out.println("The SQL statement is: " + strCheckUpdate);
            ResultSet rs = st.executeQuery(strCheckUpdate);
            ResultSetMetaData rsMD = rs.getMetaData();

            int numColums = rsMD.getColumnCount();

            for (int i=1; i<=numColums; i++) {
                if (i == numColums-1) System.out.printf("%-60s", rsMD.getColumnName(i));
                else System.out.printf("%-30s", rsMD.getColumnName(i));
            }
            System.out.println();

            while (rs.next()) {
                for (int i=1; i<numColums; i++) {
                    System.out.printf("%-30s", rs.getString(i));
                }
                System.out.println();
            }

            // Thay doi thong tin dia chi
            strUpdate = "update customers\n" +
                    "    set Address = '1A Yet Kieu - Ha Noi'\n" +
                    "    where CustomerID = 'FRANK';";
            System.out.println("\nThe SQL statement is: \n" + strUpdate);
            System.out.println(st.executeUpdate(strUpdate) + " records updated");

            strCheckUpdate = "select * from customers;";
            System.out.println("The SQL statement is: \n" + strCheckUpdate);
            rs = st.executeQuery(strCheckUpdate);
            rsMD = rs.getMetaData();

            rs.next();
            numColums = rsMD.getColumnCount();
            System.out.println("The updated table is as below: ");
            Result(rs, rsMD, numColums);

            // Thay doi gia tri bang Products
            strUpdate = "update products\n" +
                    "    set UnitPrice = UnitPrice * 1.1\n" +
                    "    where CategoryID IN (5, 7, 8);";
            System.out.println("\nThe SQL statement is: \n" + strUpdate);
            System.out.println(st.executeUpdate(strUpdate) + " records updated");

            strCheckUpdate = "select * from products;";
            System.out.println("The SQL statement is: " + strCheckUpdate);
            rs = st.executeQuery(strCheckUpdate);
            rsMD = rs.getMetaData();

            numColums = rsMD.getColumnCount();
            Result(rs, rsMD, numColums);

            // Thay doi gia tri bang Order
            strUpdate = "update orders\n" +
                    "    set ShipVia = 3\n" +
                    "    where ShipVia = 2 AND OrderID = 10313;";
            System.out.println("\nThe SQL statement is: \n" + strUpdate);
            System.out.println(st.executeUpdate(strUpdate) + " records updated");

            strCheckUpdate = "select * from orders;";
            System.out.println("The SQL statement is: " + strCheckUpdate);
            rs = st.executeQuery(strCheckUpdate);
            rsMD = rs.getMetaData();

            numColums = rsMD.getColumnCount();
            Result(rs, rsMD, numColums);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void Result(ResultSet rs, ResultSetMetaData rsMD, int numColums) throws SQLException {
        for (int i=1; i<=numColums; i++) {
            System.out.printf("%-30s", rsMD.getColumnName(i));
        }
        System.out.println();

        while (rs.next()) {
            for (int i=1; i<=numColums; i++) {
                System.out.printf("%-30s", rs.getString(i));
            }
            System.out.println();
        }
    }
}
