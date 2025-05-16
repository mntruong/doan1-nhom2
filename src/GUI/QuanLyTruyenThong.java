package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class QuanLyTruyenThong extends JFrame {
    private JTextField tfMa, tfTenChiTiet, tfMucTieu, tfKenh, tfNganSach, tfNgayBD, tfNgayKT, tfMaNV, tfSearchMaNV;
    private JTable table;
    private DefaultTableModel model;
    private Connection conn;

    public QuanLyTruyenThong() {
        setTitle("Quản lý Chiến Lược Truyền Thông");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // 1. Kết nối DB
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/quanlycuahangquanao", "root", ""
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối DB: " + e.getMessage());
            System.exit(1);
        }

        // 2. Các label + textfield
        JLabel[] labels = {
            new JLabel("Mã CLTT:"), new JLabel("Tên Chi Tiết:"), new JLabel("Mục Tiêu:"),
            new JLabel("Kênh:"), new JLabel("Ngân Sách:"), new JLabel("Bắt Đầu (yyyy-MM-dd):"),
            new JLabel("Kết Thúc (yyyy-MM-dd):"), new JLabel("Mã NV:")
        };
        JTextField[] fields = {
            tfMa       = new JTextField(),
            tfTenChiTiet = new JTextField(),
            tfMucTieu  = new JTextField(),
            tfKenh     = new JTextField(),
            tfNganSach = new JTextField(),
            tfNgayBD   = new JTextField(),
            tfNgayKT   = new JTextField(),
            tfMaNV     = new JTextField()
        };

        for (int i = 0; i < labels.length; i++) {
            labels[i].setBounds(20, 20 + i*35, 150, 25);
            fields[i].setBounds(180, 20 + i*35, 200, 25);
            add(labels[i]);
            add(fields[i]);
        }

        // 3. Nút chức năng
        JButton btnAdd = new JButton("Thêm");
        JButton btnUpdate = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnSearch = new JButton("Tìm NV");

        btnAdd.setBounds(420, 20, 100, 25);
        btnUpdate.setBounds(420, 60, 100, 25);
        btnDelete.setBounds(420, 100, 100, 25);

        tfSearchMaNV = new JTextField();
        tfSearchMaNV.setBounds(420, 140, 150, 25);
        btnSearch.setBounds(580, 140, 100, 25);

        add(btnAdd); add(btnUpdate); add(btnDelete);
        add(tfSearchMaNV); add(btnSearch);

        // 4. Table hiển thị
        model = new DefaultTableModel(new String[]{
            "Mã CLTT","Tên Chi Tiết","Mục Tiêu","Kênh","Ngân Sách","BĐ","KT","Mã NV"
        }, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 240, 840, 300);
        add(sp);

        // 5. Load dữ liệu ban đầu
        loadData("");

        // 6. Đăng ký sự kiện
        btnAdd.addActionListener(e -> insert());
        btnUpdate.addActionListener(e -> update());
        btnDelete.addActionListener(e -> delete());
        btnSearch.addActionListener(e -> loadData(tfSearchMaNV.getText()));

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r >= 0) {
                    tfMa.setText(model.getValueAt(r,0).toString());
                    tfTenChiTiet.setText(model.getValueAt(r,1).toString());
                    tfMucTieu.setText(model.getValueAt(r,2).toString());
                    tfKenh.setText(model.getValueAt(r,3).toString());
                    tfNganSach.setText(model.getValueAt(r,4).toString());
                    tfNgayBD.setText(model.getValueAt(r,5).toString());
                    tfNgayKT.setText(model.getValueAt(r,6).toString());
                    tfMaNV.setText(model.getValueAt(r,7).toString());
                }
            }
        });

        setVisible(true);
    }

    private void loadData(String maNVFilter) {
        model.setRowCount(0);
        try {
            String sql = "SELECT * FROM ChienLuocTruyenThong";
            if (maNVFilter != null && !maNVFilter.isEmpty()) {
                sql += " WHERE Ma_NV LIKE ?";
            }
            PreparedStatement pst = conn.prepareStatement(sql);
            if (maNVFilter != null && !maNVFilter.isEmpty()) {
                pst.setString(1, "%" + maNVFilter + "%");
            }
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("Ma_CLTT"),
                    rs.getString("TenChiTiet"),
                    rs.getString("MucTieu"),
                    rs.getString("Kenh"),
                    rs.getBigDecimal("NganSach"),
                    rs.getDate("ThoiGianBatDau"),
                    rs.getDate("ThoiGianKetThuc"),
                    rs.getString("Ma_NV")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Load lỗi: " + ex.getMessage());
        }
    }

    private void insert() {
        try {
            String sql = "INSERT INTO ChienLuocTruyenThong VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, tfMa.getText());
            pst.setString(2, tfTenChiTiet.getText());
            pst.setString(3, tfMucTieu.getText());
            pst.setString(4, tfKenh.getText());
            pst.setBigDecimal(5, new java.math.BigDecimal(tfNganSach.getText()));
            pst.setDate(6, java.sql.Date.valueOf(tfNgayBD.getText()));
            pst.setDate(7, java.sql.Date.valueOf(tfNgayKT.getText()));
            pst.setString(8, tfMaNV.getText());
            pst.executeUpdate();
            loadData("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi thêm: " + ex.getMessage());
        }
    }

    private void update() {
        try {
            String sql = "UPDATE ChienLuocTruyenThong SET TenChiTiet=?,MucTieu=?,Kenh=?,NganSach=?,ThoiGianBatDau=?,ThoiGianKetThuc=?,Ma_NV=? WHERE Ma_CLTT=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, tfTenChiTiet.getText());
            pst.setString(2, tfMucTieu.getText());
            pst.setString(3, tfKenh.getText());
            pst.setBigDecimal(4, new java.math.BigDecimal(tfNganSach.getText()));
            pst.setDate(5, java.sql.Date.valueOf(tfNgayBD.getText()));
            pst.setDate(6, java.sql.Date.valueOf(tfNgayKT.getText()));
            pst.setString(7, tfMaNV.getText());
            pst.setString(8, tfMa.getText());
            pst.executeUpdate();
            loadData("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi sửa: " + ex.getMessage());
        }
    }

    private void delete() {
        try {
            String sql = "DELETE FROM ChienLuocTruyenThong WHERE Ma_CLTT=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, tfMa.getText());
            pst.executeUpdate();
            loadData("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi xóa: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChienLuocForm::new);
    }
}
