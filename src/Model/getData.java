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
public class getData{
        public List<String[]> getData(String tableName, String[] columns) throws SQLException, ClassNotFoundException {
            connectDatabase db = new connectDatabase();
            Connection conn = db.getConnection();
            List<String[]> resultList = new ArrayList<>();

            StringBuilder sql = new StringBuilder("SELECT ");
            for (int i = 0; i < columns.length; i++) {
                sql.append(columns[i]);
                if (i < columns.length - 1) {
                    sql.append(", ");
                }
            }
            sql.append(" FROM ").append(tableName);

            try (PreparedStatement ps = conn.prepareStatement(sql.toString());
                    ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    String[] row = new String[columns.length];
                    for (int i = 0; i < columns.length; i++) {
                        row[i] = rs.getString(columns[i]);
                    }
                    resultList.add(row);
                }
            }

            return resultList;
    }
}
