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
public class updateData {
    public void updateData(String tableName, String[] columns, Object[] values, String condition) throws ClassNotFoundException, SQLException{
        connectDatabase cd = new connectDatabase();
        Connection conn = cd.getConnection();

        StringBuilder setClause = new StringBuilder();
        for(int i = 0; i < columns.length; i++){
            setClause.append(columns[i]).append("=?");
            if(i < columns.length - 1){
                setClause.append(", ");
            }
        }
        
        String sql = "update " + tableName + " set" + setClause + " where " + condition;
        PreparedStatement ps = conn.prepareStatement(sql);
        
        for(int i = 0; i < values.length; i++){
            ps.setObject(i + 1, values[i]);
        }
         int rowUpdate = ps.executeUpdate();
         if(rowUpdate > 0){
             JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công trong bảng");
         }else{
             JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu để cập nhật trong bảng ");
         }
         
        ps.close();
        conn.close();
    }
}
