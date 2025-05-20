/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;
import ConnectDatabase.connectDatabase;
import javax.swing.JOptionPane;
/**
 *
 * @author ADMIN
 */
public class getData{
    public ResultSet getData(String tableName) throws ClassNotFoundException, SQLException{
        connectDatabase db = new connectDatabase();
        Connection conn = db.getConnection();
        Statement st = conn.createStatement();
        String sql = "select * from " + tableName;
        ResultSet rs = null;
        rs = st.executeQuery(sql);
        return rs;
    }
}
