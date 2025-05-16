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
public class searchData {
    public ResultSet searchData(String tableName, String columnsName, String keyword) throws ClassNotFoundException, SQLException{
        connectDatabase cd = new connectDatabase();
        Connection conn = cd.getConnection();
        
        String sql = "select * from " + tableName + " where " + columnsName + " like ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();
        
        if(!rs.isBeforeFirst()){
            JOptionPane.showMessageDialog(null,  "Không tìm thấy kết quả nào.");
        }
        
        return rs;
    }
}
