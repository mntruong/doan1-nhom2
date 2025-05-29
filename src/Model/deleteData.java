/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import ConnectDatabase.connectDatabase;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class deleteData {
    public int delete(String tableName, String[] columns, Object[] values)
            throws ClassNotFoundException, SQLException {

        connectDatabase cd = new connectDatabase();
        Connection conn = cd.getConnection();

        StringBuilder whereClause = new StringBuilder();
        List<Object> parameters = new ArrayList<>();

        for (int i = 0; i < columns.length; i++) {
            Object value = values[i];
            if (value != null && !value.toString().trim().isEmpty()) {
                if (whereClause.length() > 0) {
                    whereClause.append(" AND ");
                }
                whereClause.append(columns[i]).append(" = ?");
                parameters.add(value);
            }
        }

       
        if (parameters.isEmpty()) {
            return 0;
        }

        String sql = "DELETE FROM " + tableName + " WHERE " + whereClause.toString();
        PreparedStatement ps = conn.prepareStatement(sql);

        for (int i = 0; i < parameters.size(); i++) {
            ps.setObject(i + 1, parameters.get(i)); 
        }

        return ps.executeUpdate();
    }
}
