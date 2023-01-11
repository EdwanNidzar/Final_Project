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
    
    static Connection conn;
	public static Connection getConnetion(){
            try{
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/final_pbo", "root", "");
            } catch(Exception e) {
                    System.out.println(e.toString());
            }
	return conn;
    }
    
}
