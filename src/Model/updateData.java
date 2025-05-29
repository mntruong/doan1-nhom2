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
    public void updateData(String table, String conditionColumn, String conditionValue, String[] columns, Object[] values)
            throws SQLException, ClassNotFoundException {
        
        connectDatabase cd = new connectDatabase();
        Connection conn = cd.getConnection();
        StringBuilder query = new StringBuilder("UPDATE " + table + " SET ");

        for (int i = 0; i < columns.length; i++) {
            query.append(columns[i]).append(" = ?");
            if (i < columns.length - 1) query.append(", ");
        }

        query.append(" WHERE ").append(conditionColumn).append(" = ?");

        PreparedStatement pst = conn.prepareStatement(query.toString());

        for (int i = 0; i < values.length; i++) {
            pst.setObject(i + 1, values[i]);
        }

        pst.setString(values.length + 1, conditionValue); 

        pst.executeUpdate();
        conn.close();
    }
    
    public boolean thayDoiGiaTriSoLuong(String tableName, String columnName, String conditionColumn, String conditionValue,
                                        int giaTriThayDoi, String action) throws SQLException, ClassNotFoundException {
        connectDatabase cd = new connectDatabase();
        Connection conn = cd.getConnection();

        String phepToan = null;
        if ("increase".equalsIgnoreCase(action)) {
            phepToan = columnName + " = " + columnName + " + ?";
        } else if ("decrease".equalsIgnoreCase(action)) {
            phepToan = columnName + " = " + columnName + " - ?";
        } else {
            throw new IllegalArgumentException("Action phải là 'increase' hoặc 'decrease'");
        }

        String sql = "UPDATE " + tableName + " SET " + phepToan + " WHERE " + conditionColumn + " = ?";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, giaTriThayDoi);
        pst.setString(2, conditionValue);

        int affectedRows = pst.executeUpdate();
        conn.close();

        return affectedRows > 0;
    }
}
