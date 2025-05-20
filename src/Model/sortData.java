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
public class sortData {
    public ResultSet sortData(String tableName, String columnName, String order) throws ClassNotFoundException, SQLException{
        connectDatabase cd = new connectDatabase();
        Connection conn = cd.getConnection();
        
        if (!order.equalsIgnoreCase("ASC") && !order.equalsIgnoreCase("DESC")) {
            JOptionPane.showMessageDialog(null, "Thứ tự sắp xếp không hợp lệ. Chỉ dùng ASC hoặc DESC.");
            return null;
        }
        
        String sql = "select * from " + tableName + " order by " + columnName + " " + order;
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        
        if(!rs.isBeforeFirst()){
            JOptionPane.showMessageDialog(null, "Không có dữ liệu trong bảng.");
        }
        return rs;
    }
}
