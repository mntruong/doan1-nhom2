/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import ConnectDatabase.connectDatabase;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class deleteData {
    public void deleteData(String tableName, String condition) throws ClassNotFoundException, SQLException{
        connectDatabase cd = new connectDatabase();
        Connection conn = cd.getConnection();
        
        String sql = "delete from " + tableName + " where " + condition;
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        int rowDelete = ps.executeUpdate();
        if(rowDelete > 0){
            JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công trong bảng");
        }else{
             JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu để xóa trong bảng ");
        }
        
        ps.close();
        conn.close();
    }
}
