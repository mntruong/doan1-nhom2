/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;
import ConnectDatabase.ConnectDatabase;
/**
 *
 * @author ADMIN
 */
public class Model extends ConnectDatabase{
    public ResultSet getData(String tableName) throws SQLException{
        Statement st = this.conn.createStatement();
        ResultSet rs = null;
        String sql = "select * from " + tableName;
        rs = st.executeQuery(sql);
        return  rs;
    }
    
    public void addData(){
        
    }
}
