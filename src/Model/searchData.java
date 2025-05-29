/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;
import ConnectDatabase.connectDatabase;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class searchData {
    public ResultSet search(String tableName, String[] columns, Object[] values) throws ClassNotFoundException, SQLException {

        connectDatabase cd = new connectDatabase();
        Connection conn = cd.getConnection();

        StringBuilder sql = new StringBuilder("SELECT * FROM " + tableName + " WHERE 1=1");
        List<Object> parameters = new ArrayList<>();

        for (int i = 0; i < columns.length; i++) {
            Object value = values[i];
            if (value != null && !value.toString().trim().isEmpty()) {
            sql.append(" AND ").append(columns[i]).append(" = ?");
            parameters.add(value);
        }
        }

        PreparedStatement ps = conn.prepareStatement(sql.toString(),
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

        for (int i = 0; i < parameters.size(); i++) {
            ps.setObject(i + 1, parameters.get(i));
        }

        return ps.executeQuery();
    }
    
    public ResultSet searchIn(String tableName, String column, List<?> values) throws ClassNotFoundException, SQLException {
        connectDatabase cd = new connectDatabase();
        Connection conn = cd.getConnection();

        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Danh sách giá trị không được rỗng");
        }

        StringBuilder sql = new StringBuilder("SELECT * FROM " + tableName + " WHERE " + column + " IN (");
        sql.append(String.join(",", Collections.nCopies(values.size(), "?")));
        sql.append(")");

        PreparedStatement ps = conn.prepareStatement(sql.toString(),
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        for (int i = 0; i < values.size(); i++) {
            ps.setObject(i + 1, values.get(i));
        }

        return ps.executeQuery();
    }
}
