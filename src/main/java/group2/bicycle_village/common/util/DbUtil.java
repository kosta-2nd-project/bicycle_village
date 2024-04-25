package group2.bicycle_village.common.util;


import java.sql.*;
import java.util.Properties;

public class DbUtil {
    private static Properties proFile = new Properties();

    /**
     * 로드
     */
    static {
        try {
            Class.forName(DBProperties.DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Properties getProFile() {
        return proFile;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DBProperties.URL,
                DBProperties.USER,
                DBProperties.PASSWORD);
    }


    public static void close(Connection con, Statement st, ResultSet rs) {
        try {
            if(rs != null) rs.close();
            if(st != null) st.close();
            if(con != null) con.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
