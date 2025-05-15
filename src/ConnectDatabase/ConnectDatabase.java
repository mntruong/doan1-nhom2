/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ConnectDatabase;

import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class ConnectDatabase {

    /**
     * @param args the command line arguments
     */
    private final Connection conn;

    public ConnectDatabase()throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/quanlycuahangquanao";
        this.conn = DriverManager.getConnection(url, "root", "");
        if(this.conn != null){
            JOptionPane.showMessageDialog(null, "Connected Successfully!");
        }else{
            JOptionPane.showMessageDialog(null, "Get Connect Fail!");
        }
    }
    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
    }
    
}
