import java.sql.*;

public class SelectDataMain {
    // JDBC driver name and database URL
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/emp";
    // Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
//STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
//STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, "root", "root");
//STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, firstname, lastname, age FROM Employee";
            ResultSet rs = stmt.executeQuery(sql);;
//STEP 5: Extract data from result set
            while (rs.next()) {
//Retrieve by column name
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
//Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + firstname);
                System.out.println(", Last: " + lastname);
            }
//STEP 6: Clean-up Resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
//Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
//Handle errors for Class.forName
            e.printStackTrace();
        } finally {
//finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing can be done
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
}
