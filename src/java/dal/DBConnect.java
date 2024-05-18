/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author NgocHung_ighoorbeos
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DBConnect {
    public Connection con = null;

    public DBConnect(String URL, String username, String password) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(URL, username, password);
            System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public DBConnect() {
        String url = System.getenv("jdbc:sqlserver://localhost:1433;databaseName=PRJ301_BL5");
        String user = System.getenv("123");
        String pass = System.getenv("123");

        if (url == null || user == null || pass == null) {
            throw new IllegalStateException("Database credentials are not set in environment variables");
        }

        new DBConnect(url, user, pass);
    }

    public Connection getConnection() {
        return con;
    }


    //ham thuc hien truy van sql va xuat kq
    public ResultSet getData(String sql){
        ResultSet rs = null;
        Statement state;
        try {
            state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
          rs = state.executeQuery(sql);
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return rs;
    }
    public static void main(String[] args) {
        new DBConnect();
    }
}
