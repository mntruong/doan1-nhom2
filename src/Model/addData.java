/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javax.swing.JOptionPane;
import java.sql.*;
import ConnectDatabase.connectDatabase;

/**
 *
 * @author ADMIN
 */
public class addData {
    public void addData(String tableName, String[] columns, Object[] values) throws SQLException, ClassNotFoundException{
        connectDatabase db = new connectDatabase();
        Connection conn = db.getConnection();
        
        StringBuilder cols = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < columns.length; i++){
            cols.append(columns[i]);
            placeholders.append("?");
            if(i < columns.length - 1){
                cols.append(", ");
                placeholders.append(", ");
            }
        }
        
        String sql = "insert into " + tableName + " (" + cols + ") values (" + placeholders + ")";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        for(int i = 0; i < values.length; i++){
            ps.setObject(i + 1, values[i]);
        }
        
        int rowInserted = ps.executeUpdate();
        if(rowInserted > 0){
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công vào bảng");
        }else{
            JOptionPane.showMessageDialog(null, "Không thể thêm dữ liệu vào bảng");
        }
        
        ps.close();
        conn.close();
    }
}
