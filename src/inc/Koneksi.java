/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nidza
 */
public class Koneksi {
    
    private final String url = "jdbc:mysql://localhost:3306/penginapan";
    private final String user = "root";
    private final String pass = "";
    
    public Connection getConnetion(){
        Connection conn;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("koneksi berhasil");
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("koneksi gagal"+ex);
            return conn = null;
        }
    }
    
    public static void main(String[] args) {
        Koneksi k =  new Koneksi();
        k.getConnetion();
    }
    
}
