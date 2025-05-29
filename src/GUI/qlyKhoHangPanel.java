/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;
import ConnectDatabase.connectDatabase;
import Model.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class qlyKhoHangPanel extends javax.swing.JPanel {
    private String selectedMaSP = null,
                   selectedTenSP = null,
                   selectedDVT = null,
                   selectedGiaNhap = null,
                   selectedTon = null,
                   selectedGiaBan = null,
                   selectedMaNCC = null,
                   selectedTenNCC = null,
                   selectedDiaChi = null,
                   selectedSDT = null,
                   selectedEmail = null;
    /**
     * Creates new form qlyKhoHangPanel
     */
    public qlyKhoHangPanel() {
        initComponents();
        setupTableSanPhamClickEvent();
        setupTableNCCClickEvent();
        try {
            loadTableSP();
            loadTableNCC();
            loadMaSPIntoComboBoxes();
            loadMaNVIntoComboBoxes();
            loadMaKHIntoComboBoxes();
            loadMaNCCIntoComboBoxes();
        } catch (Exception e) {
            e.printStackTrace();
           JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu sản phẩm: " + e.getMessage());
        }
        
        
    }
    
    private void loadMaSPIntoComboBoxes() throws ClassNotFoundException {
        cbSPnhap.removeAllItems();
        cbSPxuat.removeAllItems();
        getData get =  new getData();
        String[] columns = {"Ma_SP"};
        try {
            List<String[]> dataList = get.getData("sanpham", columns);
            for (String[] strings : dataList) {
                 cbSPnhap.addItem(strings[0]);
                 cbSPxuat.addItem(strings[0]);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tf_TonNhap.setText("0");
        tf_TonXuat.setText("0");
    }
     private void loadMaNVIntoComboBoxes() throws ClassNotFoundException {
        cbb_maNVnhap.removeAllItems();
        cbb_maNVxuat.removeAllItems();
        getData get =  new getData();
        String[] columns = {"Ma_NV"};
        try {
            List<String[]> dataList = get.getData("nhanvien", columns);
            for (String[] strings : dataList) {
                 cbb_maNVnhap.addItem(strings[0]);
                 cbb_maNVxuat.addItem(strings[0]);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void loadMaNCCIntoComboBoxes() throws ClassNotFoundException {
        cbb_NCC.removeAllItems();
        getData get =  new getData();
        String[] columns = {"Ma_NCC"};
        try {
            List<String[]> dataList = get.getData("nhacungcap", columns);
            for (String[] strings : dataList) {
                 cbb_NCC.addItem(strings[0]);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void loadMaKHIntoComboBoxes() throws ClassNotFoundException {
        cbb_KH.removeAllItems();
        getData get =  new getData();
        String[] columns = {"Ma_KH"};
        try {
            List<String[]> dataList = get.getData("khachhang", columns);
            for (String[] strings : dataList) {
                 cbb_KH.addItem(strings[0]);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    private void setupTableSanPhamClickEvent() {
        tb_SP.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tb_SP.getSelectedRow();
                if (selectedRow != -1) {
                    selectedMaSP = tb_SP.getValueAt(selectedRow, 0).toString(); 
                    selectedTenSP = tb_SP.getValueAt(selectedRow, 1).toString();
                    selectedDVT = tb_SP.getValueAt(selectedRow, 2).toString();
                    selectedGiaBan = tb_SP.getValueAt(selectedRow, 3).toString();
                    selectedGiaNhap = tb_SP.getValueAt(selectedRow, 4).toString();
                    selectedTon = tb_SP.getValueAt(selectedRow, 5).toString();
                    tf_MaHang.setEditable(false);
                    
                    tf_MaHang.setText(selectedMaSP);
                    tf_TenHang.setText(selectedTenSP);
                    tf_DVT.setText(selectedDVT);
                    tf_GiaBan.setText(selectedGiaBan);
                    tf_GiaNhap.setText(selectedGiaNhap);
                    tf_Ton.setText(selectedTon);
                    tf_TonNhap.setText(selectedTon);
                    tf_TonXuat.setText(selectedTon);
                    tf_dongianhap.setText(selectedGiaNhap);
                    tf_dongiaxuat.setText(selectedGiaBan);
                    cbSPnhap.setSelectedItem(selectedMaSP);
                    cbSPxuat.setSelectedItem(selectedMaSP);
                }
            }
        });        
    }
    
    private void setupTableNCCClickEvent() {
        tb_NCC.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tb_NCC.getSelectedRow();
                if (selectedRow != -1) {
                    selectedMaNCC = tb_NCC.getValueAt(selectedRow, 0).toString(); 
                    selectedTenNCC = tb_NCC.getValueAt(selectedRow, 1).toString();
                    selectedDiaChi = tb_NCC.getValueAt(selectedRow, 2).toString();
                    selectedSDT = tb_NCC.getValueAt(selectedRow, 3).toString();
                    selectedEmail = tb_NCC.getValueAt(selectedRow, 4).toString();
                    tf_maNCC.setEditable(false);
                    
                    tf_maNCC.setText(selectedMaNCC);
                    tf_tenNCC.setText(selectedTenNCC);
                    tf_diachiNCC.setText(selectedDiaChi);
                    tf_sdtNCC.setText(selectedSDT);
                    tf_emailNCC.setText(selectedEmail);
                    cbb_NCC.setSelectedItem(selectedMaNCC);
                }
            }
        });        
    }
    
    private void loadTableSP() throws SQLException, ClassNotFoundException{
        getData get = new getData();
        List<String[]> dataList = get.getData("sanpham", new String[]{"Ma_SP", "Ten_SP", "DonViTinh", "GiaBan", "GiaNhap", "SoLuongTon"});
            DefaultTableModel model = (DefaultTableModel) tb_SP.getModel();
            model.setRowCount(0); 

            for (String[] row : dataList) {
                 model.addRow(row);
            }
    }
    private void loadTableNCC() throws SQLException, ClassNotFoundException{
        getData get = new getData();
        List<String[]> dataList = get.getData("nhacungcap", new String[]{"Ma_NCC", "Ten_NCC", "DiaChi", "SDT", "Email"});
            DefaultTableModel model = (DefaultTableModel) tb_NCC.getModel();
            model.setRowCount(0); 

            for (String[] row : dataList) {
                 model.addRow(row);
            }
    }
    
    public String generateMaHDNH() {
    String prefix = "HDNH";
    int maxNumber = 0;

    try {
        Connection conn = new connectDatabase().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Ma_HDNH FROM hoadonnhaphang");

        while (rs.next()) {
            String ma = rs.getString("Ma_HDNH");
            // Lấy số phía sau tiền tố "HDNH"
            if (ma.startsWith(prefix)) {
                int number = Integer.parseInt(ma.substring(prefix.length()));
                if (number > maxNumber) {
                    maxNumber = number;
                }
            }
        }

        rs.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    

    // Tăng số lớn nhất và định dạng lại
    return prefix + String.format("%03d", maxNumber + 1);
}
    public String generateMaHDBH() {
    String prefix = "HDBH";
    int maxNumber = 0;

    try {
        Connection conn = new connectDatabase().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Ma_HDBH FROM hoadonbanhang");

        while (rs.next()) {
            String ma = rs.getString("Ma_HDBH");
            // Lấy số phía sau tiền tố "HDNH"
            if (ma.startsWith(prefix)) {
                int number = Integer.parseInt(ma.substring(prefix.length()));
                if (number > maxNumber) {
                    maxNumber = number;
                }
            }
        }

        rs.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    

    // Tăng số lớn nhất và định dạng lại
    return prefix + String.format("%03d", maxNumber + 1);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_SP = new javax.swing.JTable();
        tf_MaHang = new javax.swing.JTextField();
        tf_TenHang = new javax.swing.JTextField();
        bt_delete = new javax.swing.JButton();
        bt_update = new javax.swing.JButton();
        bt_add = new javax.swing.JButton();
        tf_DVT = new javax.swing.JTextField();
        tf_GiaBan = new javax.swing.JTextField();
        tf_GiaNhap = new javax.swing.JTextField();
        tf_Ton = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        bt_find = new javax.swing.JButton();
        combobox = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cbSPnhap = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        tf_TonNhap = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        dc_Nhap = new com.toedter.calendar.JDateChooser();
        tf_SoLuongNhap = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tf_dongianhap = new javax.swing.JTextField();
        cbb_NCC = new javax.swing.JComboBox<>();
        bt_XacNhanNhap = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        cbb_maNVnhap = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        cbSPxuat = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        tf_TonXuat = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        dc_xuat = new com.toedter.calendar.JDateChooser();
        tf_SoLuongXuat = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tf_dongiaxuat = new javax.swing.JTextField();
        cbb_KH = new javax.swing.JComboBox<>();
        bt_XacNhanXuat = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        cbb_maNVxuat = new javax.swing.JComboBox<>();
        bt_refresh = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_NCC = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        tf_maNCC = new javax.swing.JTextField();
        tf_tenNCC = new javax.swing.JTextField();
        tf_diachiNCC = new javax.swing.JTextField();
        tf_sdtNCC = new javax.swing.JTextField();
        tf_emailNCC = new javax.swing.JTextField();
        bt_deleteNCC = new javax.swing.JButton();
        bt_findNCC = new javax.swing.JButton();
        bt_addNCC = new javax.swing.JButton();
        bt_updateNCC = new javax.swing.JButton();
        cbb_hienthiNCC = new javax.swing.JComboBox<>();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("QUẢN LÝ KHO HÀNG");

        tb_SP.setBackground(new java.awt.Color(204, 204, 204));
        tb_SP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã hàng", "Tên hàng", "Đơn vị tính", "Giá bán", "Giá nhập", "Tồn"
            }
        ));
        jScrollPane1.setViewportView(tb_SP);

        bt_delete.setText("Xóa");
        bt_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_deleteMouseClicked(evt);
            }
        });
        bt_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_deleteActionPerformed(evt);
            }
        });

        bt_update.setText("Sửa");
        bt_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_updateMouseClicked(evt);
            }
        });

        bt_add.setText("Thêm");
        bt_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_addMouseClicked(evt);
            }
        });

        tf_DVT.setText(" ");

        tf_GiaBan.setText(" ");

        tf_GiaNhap.setText(" ");
        tf_GiaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_GiaNhapActionPerformed(evt);
            }
        });

        tf_Ton.setText(" ");
        tf_Ton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_TonActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã hàng:");

        jLabel7.setText("Tên hàng:");

        jLabel8.setText("Đơn vị tính:");

        jLabel9.setText("Giá bán:");

        jLabel10.setText("Giá nhập:");

        jLabel11.setText("Tồn:");

        bt_find.setText("Tìm");
        bt_find.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_findMouseClicked(evt);
            }
        });

        combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã hàng", "Tên hàng", "Đơn vị tính", "Giá bán", "Giá nhập", "Tồn" }));
        combobox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboboxMouseClicked(evt);
            }
        });
        combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_GiaBan)
                            .addComponent(tf_GiaNhap)
                            .addComponent(tf_Ton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_MaHang, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_DVT)
                            .addComponent(tf_TenHang))))
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bt_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_find, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bt_update, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_MaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_add)
                    .addComponent(jLabel1)
                    .addComponent(bt_update))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_TenHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_delete)
                    .addComponent(jLabel7)
                    .addComponent(combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_DVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(bt_find))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_GiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_Ton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Nhập kho");

        jLabel3.setText("Tồn:");

        tf_TonNhap.setText(" ");

        jLabel6.setText("Mã sản phẩm");

        jLabel15.setText("Nhập:");

        jLabel17.setText("Ngày nhập:");

        tf_SoLuongNhap.setText(" ");

        jLabel19.setText("NCC");

        jLabel21.setText("Đơn giá:");

        tf_dongianhap.setText(" ");

        bt_XacNhanNhap.setText("Xác nhận");
        bt_XacNhanNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_XacNhanNhapMouseClicked(evt);
            }
        });

        jLabel27.setText("NV:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jLabel12)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel3)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbb_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel27)
                                .addGap(18, 18, 18)
                                .addComponent(cbb_maNVnhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tf_SoLuongNhap)
                            .addComponent(tf_TonNhap))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel21)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(bt_XacNhanNhap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_dongianhap, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dc_Nhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbSPnhap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(58, 58, 58))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSPnhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(tf_TonNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(tf_SoLuongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addComponent(dc_Nhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cbb_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(cbb_maNVnhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(tf_dongianhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bt_XacNhanNhap)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Xuất kho");

        jLabel4.setText("Tồn:");

        tf_TonXuat.setText(" ");
        tf_TonXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_TonXuatActionPerformed(evt);
            }
        });

        jLabel14.setText("Mã sản phẩm");

        jLabel16.setText("Xuất:");

        jLabel18.setText("Ngày xuất:");

        tf_SoLuongXuat.setText(" ");

        jLabel20.setText("Khách hàng:");

        jLabel22.setText("Đơn giá:");

        tf_dongiaxuat.setText(" ");

        bt_XacNhanXuat.setText("Xác nhận");
        bt_XacNhanXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_XacNhanXuatMouseClicked(evt);
            }
        });

        jLabel28.setText("nv:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel4)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbb_KH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(cbb_maNVxuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tf_SoLuongXuat)
                    .addComponent(tf_TonXuat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel18)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dc_xuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbSPxuat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tf_dongiaxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bt_XacNhanXuat)
                    .addComponent(jLabel13))
                .addContainerGap(330, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSPxuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(tf_TonXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(tf_SoLuongXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20)
                                .addComponent(cbb_KH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28)
                                .addComponent(cbb_maNVxuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(2, 2, 2))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(dc_xuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tf_dongiaxuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(bt_XacNhanXuat)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        bt_refresh.setText("Làm mới");
        bt_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_refreshMouseClicked(evt);
            }
        });

        tb_NCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "SDT", "Email"
            }
        ));
        jScrollPane2.setViewportView(tb_NCC);

        jScrollPane3.setViewportView(jScrollPane2);

        jLabel5.setText("Mã nhà cung cấp:");

        jLabel23.setText("Tên nhà cung cấp:");

        jLabel24.setText("Địa chỉ");

        jLabel25.setText("SDT");

        jLabel26.setText("Email");

        tf_maNCC.setText(" ");

        tf_tenNCC.setText(" ");
        tf_tenNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_tenNCCActionPerformed(evt);
            }
        });

        tf_diachiNCC.setText(" ");
        tf_diachiNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_diachiNCCActionPerformed(evt);
            }
        });

        tf_sdtNCC.setText(" ");

        tf_emailNCC.setText(" ");

        bt_deleteNCC.setText("Xóa");
        bt_deleteNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_deleteNCCMouseClicked(evt);
            }
        });

        bt_findNCC.setText("Tìm");
        bt_findNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_findNCCMouseClicked(evt);
            }
        });

        bt_addNCC.setText("Thêm");
        bt_addNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_addNCCMouseClicked(evt);
            }
        });

        bt_updateNCC.setText("Sửa");
        bt_updateNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_updateNCCMouseClicked(evt);
            }
        });

        cbb_hienthiNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "SDT", "Email" }));
        cbb_hienthiNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbb_hienthiNCCMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tf_sdtNCC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(tf_tenNCC, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_maNCC, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_diachiNCC, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_emailNCC))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bt_addNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_deleteNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(83, 83, 83)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bt_findNCC)
                            .addComponent(bt_updateNCC))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbb_hienthiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(tf_maNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel23)
                                            .addComponent(tf_tenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(bt_addNCC)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(tf_diachiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(tf_sdtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(tf_emailNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(bt_updateNCC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bt_findNCC)
                                    .addComponent(bt_deleteNCC))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbb_hienthiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 49, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(539, 539, 539)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(605, 605, 605)
                        .addComponent(bt_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_refresh)
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tf_GiaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_GiaNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_GiaNhapActionPerformed

    private void bt_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_addMouseClicked
        // TODO add your handling code here:
        tf_MaHang.setEditable(true);
        if((tf_MaHang.getText()).equals("")){
            JOptionPane.showMessageDialog(null, "vui lòng nhập mã hàng");
        }
        
        addData add =  new addData();
        getData get = new getData();
        String[] columns = {"Ma_SP", "Ten_SP", "DonViTinh", "GiaBan", "GiaNhap", "SoLuongTon"};
        Object[] values = {
            tf_MaHang.getText().trim(),
            tf_TenHang.getText(),
            tf_DVT.getText(),
            Float.parseFloat(tf_GiaBan.getText().trim()),
            Float.parseFloat(tf_GiaNhap.getText().trim()),
            Integer.parseInt(tf_Ton.getText().trim())
        };
        
        try {
        add.addData("sanpham", columns, values);
        JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
        
        loadTableSP();
        loadMaSPIntoComboBoxes();
        } catch (SQLException e) {
            String message = e.getMessage().toLowerCase();
            if (message.contains("duplicate") || message.contains("primary key")) {
                JOptionPane.showMessageDialog(null, "Trùng mã sản phẩm");
            } 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_addMouseClicked

    private void comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxActionPerformed
        // TODO add your handling code here:
        tf_MaHang.setText("");
        tf_TenHang.setText("");
        tf_DVT.setText("");
        tf_GiaBan.setText("");
        tf_GiaNhap.setText("");
        tf_Ton.setText("");
        String selected = (String) combobox.getSelectedItem();
        if (selected == null) return;
        
        sortData sort = new sortData();
        ResultSet rs;
        System.out.println("Giá trị được chọn: '" + selected + "'");

        switch (selected) {
            case "Mã hàng":
                try {
                    rs = sort.sortData("sanpham", "Ma_SP", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_SP.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowKhachHang = {rs.getString("Ma_SP"),
                                                 rs.getString("Ten_SP"),
                                                 rs.getString("DonViTinh"),
                                                 rs.getString("GiaBan"), 
                                                 rs.getString("GiaNhap"), 
                                                 rs.getString("SoLuongTon"), 
                        };
                        model.addRow(rowKhachHang);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Tên hàng":
                try {
                    rs = sort.sortData("sanpham", "Ten_SP", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_SP.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowKhachHang = {rs.getString("Ma_SP"),
                                                 rs.getString("Ten_SP"),
                                                 rs.getString("DonViTinh"),
                                                 rs.getString("GiaBan"), 
                                                 rs.getString("GiaNhap"), 
                                                 rs.getString("SoLuongTon"), 
                        };
                        model.addRow(rowKhachHang);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Đơn vị tính":
                try {
                    rs = sort.sortData("sanpham", "DonViTinh", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_SP.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowKhachHang = {rs.getString("Ma_SP"),
                                                 rs.getString("Ten_SP"),
                                                 rs.getString("DonViTinh"),
                                                 rs.getString("GiaBan"), 
                                                 rs.getString("GiaNhap"), 
                                                 rs.getString("SoLuongTon"), 
                        };
                        model.addRow(rowKhachHang);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Giá bán":
                try {
                    rs = sort.sortData("sanpham", "Giaban", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_SP.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowKhachHang = {rs.getString("Ma_SP"),
                                                 rs.getString("Ten_SP"),
                                                 rs.getString("DonViTinh"),
                                                 rs.getString("GiaBan"), 
                                                 rs.getString("GiaNhap"), 
                                                 rs.getString("SoLuongTon"), 
                        };
                        model.addRow(rowKhachHang);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            case "Giá nhập":
                try {
                    rs = sort.sortData("sanpham", "Gianhap", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_SP.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowKhachHang = {rs.getString("Ma_SP"),
                                                 rs.getString("Ten_SP"),
                                                 rs.getString("DonViTinh"),
                                                 rs.getString("GiaBan"), 
                                                 rs.getString("GiaNhap"), 
                                                 rs.getString("SoLuongTon"), 
                        };
                        model.addRow(rowKhachHang);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            case "Tồn":
                try {
                    rs = sort.sortData("sanpham", "SoLuongTon", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_SP.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowKhachHang = {rs.getString("Ma_SP"),
                                                 rs.getString("Ten_SP"),
                                                 rs.getString("DonViTinh"),
                                                 rs.getString("GiaBan"), 
                                                 rs.getString("GiaNhap"), 
                                                 rs.getString("SoLuongTon"), 
                        };
                        model.addRow(rowKhachHang);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            default:
                System.out.println("Không rõ lựa chọn: " + selected);
        }
    }//GEN-LAST:event_comboboxActionPerformed

    private void tf_TonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_TonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_TonActionPerformed

    private void comboboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboboxMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_comboboxMouseClicked

    private void bt_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_deleteMouseClicked
        // TODO add your handling code here:
String maSP = tf_MaHang.getText().trim();
if (maSP.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã hàng cần xóa.");
    return;
}

try {
    searchData search = new searchData();

    // Kiểm tra sản phẩm có tồn tại
    String[] spColumns = {"Ma_SP"};
    Object[] spValues = {maSP};
    ResultSet rsSP = search.search("sanpham", spColumns, spValues);

    if (!rsSP.next()) {
        JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm với mã: " + maSP);
        return;
    }

    // Kiểm tra hóa đơn bán hàng và nhập hàng liên quan
    ResultSet rsHDBH = search.search("chitiethoadonbanhang", spColumns, spValues);
    List<String> maHoaDonBan = new ArrayList<>();
    while (rsHDBH.next()) {
        maHoaDonBan.add(rsHDBH.getString("Ma_HDBH"));
    }
    ResultSet rsHDNH = search.search("chitiethoadonnhaphang", spColumns, spValues);
    List<String> maHoaDonNhap = new ArrayList<>();
    while (rsHDNH.next()) {
        maHoaDonNhap.add(rsHDNH.getString("Ma_HDNH"));
    }

    // Xác nhận nếu có liên kết
    int confirm = JOptionPane.YES_OPTION;
    if (!maHoaDonBan.isEmpty() || !maHoaDonNhap.isEmpty()) {
        StringBuilder msg = new StringBuilder("Sản phẩm đang có liên kết với:\n");

        if (!maHoaDonBan.isEmpty()) {
            msg.append(" - Hóa đơn bán hàng: ").append(String.join(", ", maHoaDonBan)).append("\n");
        }
        if (!maHoaDonNhap.isEmpty()) {
            msg.append(" - Hóa đơn nhập hàng: ").append(String.join(", ", maHoaDonNhap)).append("\n");
        }

        msg.append("Bạn có chắc chắn muốn xóa tất cả các dữ liệu này không?");
        confirm = JOptionPane.showConfirmDialog(null, msg.toString(), "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
    }

    if (confirm == JOptionPane.YES_OPTION) {
        Connection conn = new connectDatabase().getConnection();

        try {
            // 1. Xóa chi tiết hóa đơn bán hàng dựa trên Ma_SP
            if (!maHoaDonBan.isEmpty() || !maHoaDonNhap.isEmpty()) {
                PreparedStatement psDelCTHDBH = conn.prepareStatement("DELETE FROM chitiethoadonbanhang WHERE Ma_SP = ?");
                psDelCTHDBH.setString(1, maSP);
                psDelCTHDBH.executeUpdate();
                psDelCTHDBH.close();
            }

            // 2. Xóa chi tiết hóa đơn nhập hàng dựa trên Ma_SP
            if (!maHoaDonNhap.isEmpty()) {
                PreparedStatement psDelCTHDNH = conn.prepareStatement("DELETE FROM chitiethoadonnhaphang WHERE Ma_SP = ?");
                psDelCTHDNH.setString(1, maSP);
                psDelCTHDNH.executeUpdate();
                psDelCTHDNH.close();
            }

            // 3. Xóa hóa đơn bán hàng (nếu không còn chi tiết)
            if (!maHoaDonBan.isEmpty()) {
                PreparedStatement psDelHDBH = conn.prepareStatement("DELETE FROM hoadonbanhang WHERE Ma_HDBH NOT IN (SELECT Ma_HDBH FROM chitiethoadonbanhang)");
                psDelHDBH.executeUpdate();
                psDelHDBH.close();
            }

            // 4. Xóa hóa đơn nhập hàng (nếu không còn chi tiết)
            if (!maHoaDonNhap.isEmpty()) {
                PreparedStatement psDelHDNH = conn.prepareStatement("DELETE FROM hoadonnhaphang WHERE Ma_HDNH NOT IN (SELECT Ma_HDNH FROM chitiethoadonnhaphang)");
                psDelHDNH.executeUpdate();
                psDelHDNH.close();
            }

            // 5. Xóa bản ghi trong sanpham_kho dựa trên Ma_SP
            PreparedStatement psDelKho = conn.prepareStatement("DELETE FROM sanpham_kho WHERE Ma_SP = ?");
            psDelKho.setString(1, maSP);
            psDelKho.executeUpdate();
            psDelKho.close();

            // 6. Xóa sản phẩm
            PreparedStatement psDelSP = conn.prepareStatement("DELETE FROM sanpham WHERE Ma_SP = ?");
            psDelSP.setString(1, maSP);
            int rows = psDelSP.executeUpdate();
            psDelSP.close();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Đã xóa sản phẩm và toàn bộ dữ liệu liên quan.");
                tf_MaHang.setText("");
                tf_TenHang.setText("");
                tf_DVT.setText("");
                tf_GiaBan.setText("");
                tf_GiaNhap.setText("");
                tf_Ton.setText("");
                DefaultTableModel model = (DefaultTableModel) tb_SP.getModel();
                model.setRowCount(0);

                // Lấy lại dữ liệu mới từ cơ sở dữ liệu
                getData get = new getData();
                List<String[]> dataList = get.getData("sanpham", new String[]{"Ma_SP", "Ten_SP", "DonViTinh", "GiaBan", "GiaNhap", "SoLuongTon"});
                for (String[] row : dataList) {
                    model.addRow(row);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Không thể xóa sản phẩm.");
            }
        } finally {
            if (conn != null) conn.close();
        }
    }
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Lỗi khi xóa dữ liệu: " + e.getMessage());
}
    }//GEN-LAST:event_bt_deleteMouseClicked

    private void bt_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_deleteActionPerformed

    private void bt_findMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_findMouseClicked
        // TODO add your handling code here:
        String[] columns = {"Ma_SP", "Ten_SP", "DonViTinh", "GiaBan", "GiaNhap", "SoLuongTon"};
        Object[] values = {
            tf_MaHang.getText(),
            tf_TenHang.getText(),
            tf_DVT.getText(),
            tf_GiaBan.getText().trim(),
            tf_GiaNhap.getText().trim(),
            tf_Ton.getText().trim()
        };
        searchData search  = new searchData();
        
        try {
            ResultSet rs = search.search("sanpham", columns, values);
            
            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "Không có kết quả tương ứng");
            }else{
                rs.beforeFirst(); 
            
                DefaultTableModel model = (DefaultTableModel) tb_SP.getModel();
                model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowSanPham = {rs.getString("Ma_SP"),
                                                 rs.getString("Ten_SP"),
                                                 rs.getString("DonViTinh"),
                                                 rs.getString("GiaBan"), 
                                                 rs.getString("GiaNhap"), 
                                                 rs.getString("SoLuongTon")
                        };
                        model.addRow(rowSanPham);
                    }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_bt_findMouseClicked

    private void bt_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_updateMouseClicked
        // TODO add your handling code here:
        if (selectedMaSP == null || selectedMaSP.equals("")) {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng cần sửa từ bảng.");
        return;
        }
        int confirm = JOptionPane.showConfirmDialog(null,
        "Bạn có chắc chắn muốn sửa hàng có mã: " + selectedMaSP + "?",
        "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
        
        String[] columns = {"Ma_SP", "Ten_SP", "DonViTinh", "GiaBan", "GiaNhap", "SoLuongTon"};
        Object[] values = {
            tf_MaHang.getText(),
            tf_TenHang.getText(),
            tf_DVT.getText(),
            tf_GiaBan.getText().trim(),
            tf_GiaNhap.getText().trim(),
            tf_Ton.getText().trim()
        };
        
        updateData updater = new updateData();
        getData get = new getData();
        
        try {
            updater.updateData("sanpham", "Ma_SP", selectedMaSP, columns, values);
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
            
            List<String[]> dataList = get.getData("sanpham", new String[]{"Ma_SP", "Ten_SP", "DonViTinh", "GiaBan", "GiaNhap", "SoLuongTon"});
            DefaultTableModel model = (DefaultTableModel) tb_SP.getModel();
            model.setRowCount(0); 

            for (String[] row : dataList) {
                 model.addRow(row);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật: " + e.getMessage());
        }
        }
    }//GEN-LAST:event_bt_updateMouseClicked

    private void bt_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_refreshMouseClicked
        // TODO add your handling code here:
        try {
            loadMaKHIntoComboBoxes();
            loadMaNCCIntoComboBoxes();
            loadMaSPIntoComboBoxes();
            loadTableSP();
            loadTableNCC();
        } catch (SQLException ex) {
            Logger.getLogger(qlyKhoHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(qlyKhoHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        tf_MaHang.setEditable(true);
        tf_MaHang.setText("");
        tf_TenHang.setText("");
        tf_DVT.setText("");
        tf_GiaBan.setText("");
        tf_GiaNhap.setText("");
        tf_Ton.setText("");
        tf_TonNhap.setText("");
        tf_TonXuat.setText("");
        tf_dongianhap.setText("");
        tf_dongiaxuat.setText("");
        tf_maNCC.setText("");
        tf_tenNCC.setText("");
        tf_diachiNCC.setText("");
        tf_sdtNCC.setText("");
        tf_emailNCC.setText("");
        
    }//GEN-LAST:event_bt_refreshMouseClicked

    private void tf_TonXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_TonXuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_TonXuatActionPerformed

    private void tf_tenNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_tenNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_tenNCCActionPerformed

    private void tf_diachiNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_diachiNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_diachiNCCActionPerformed

    private void cbb_hienthiNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbb_hienthiNCCMouseClicked
        // TODO add your handling code here:
        tf_maNCC.setText("");
        tf_tenNCC.setText("");
        tf_diachiNCC.setText("");
        tf_sdtNCC.setText("");
        tf_emailNCC.setText("");
        String selected = (String) cbb_hienthiNCC.getSelectedItem();
        if (selected == null) return;
        
        sortData sort = new sortData();
        ResultSet rs;
        System.out.println("Giá trị được chọn: '" + selected + "'");

        switch (selected) {
            case "Mã nhà cung cấp":
                try {
                    rs = sort.sortData("nhacungcap", "Ma_NCC", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_NCC.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowNCC = {rs.getString("Ma_NCC"),
                                                 rs.getString("Ten_NCC"),
                                                 rs.getString("DiaChi"),
                                                 rs.getString("SDT"), 
                                                 rs.getString("Email"), 
                        };
                        model.addRow(rowNCC);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            case "Tên nhà cung cấp":
                try {
                    rs = sort.sortData("nhacungcap", "Ten_NCC", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_NCC.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowNCC = {rs.getString("Ma_NCC"),
                                                 rs.getString("Ten_NCC"),
                                                 rs.getString("DiaChi"),
                                                 rs.getString("SDT"), 
                                                 rs.getString("Email"), 
                        };
                        model.addRow(rowNCC);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            case "Địa chỉ":
                try {
                    rs = sort.sortData("nhacungcap", "DiaChi", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_NCC.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowNCC = {rs.getString("Ma_NCC"),
                                                 rs.getString("Ten_NCC"),
                                                 rs.getString("DiaChi"),
                                                 rs.getString("SDT"), 
                                                 rs.getString("Email"), 
                        };
                        model.addRow(rowNCC);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            case "SDT":
                try {
                    rs = sort.sortData("nhacungcap", "SDT", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_NCC.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowNCC = {rs.getString("Ma_NCC"),
                                                 rs.getString("Ten_NCC"),
                                                 rs.getString("DiaChi"),
                                                 rs.getString("SDT"), 
                                                 rs.getString("Email"), 
                        };
                        model.addRow(rowNCC);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }                                
                break;
            case "Email":
                try {
                    rs = sort.sortData("nhacungcap", "Email", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_NCC.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowNCC = {rs.getString("Ma_NCC"),
                                                 rs.getString("Ten_NCC"),
                                                 rs.getString("DiaChi"),
                                                 rs.getString("SDT"), 
                                                 rs.getString("Email"), 
                        };
                        model.addRow(rowNCC);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            default:
                System.out.println("Không rõ lựa chọn: " + selected);
        }
    }//GEN-LAST:event_cbb_hienthiNCCMouseClicked

    private void bt_updateNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_updateNCCMouseClicked
        // TODO add your handling code here:
        if (selectedMaNCC == null || selectedMaNCC.equals("")) {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng cần sửa từ bảng.");
        return;
        }
        int confirm = JOptionPane.showConfirmDialog(null,
        "Bạn có chắc chắn muốn sửa khách hàng có mã: " + selectedMaNCC + "?",
        "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
        
        String[] columns = {"Ma_NCC", "Ten_NCC", "DiaChi", "SDT", "Email"};
        Object[] values = {
            tf_maNCC.getText(),
            tf_tenNCC.getText(),
            tf_diachiNCC.getText(),
            tf_sdtNCC.getText(),
            tf_emailNCC.getText()
        };
        
        updateData updater = new updateData();
        getData get = new getData();
        
        try {
            updater.updateData("nhacungcap", "Ma_NCC", selectedMaNCC, columns, values);
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
            loadTableNCC();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật: " + e.getMessage());
        }
        }
    }//GEN-LAST:event_bt_updateNCCMouseClicked

    private void bt_findNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_findNCCMouseClicked
        // TODO add your handling code here:
        String[] columns = {"Ma_NCC", "Ten_NCC", "DiaChi", "SDT", "Email"};
        Object[] values = {
            tf_maNCC.getText(),
            tf_tenNCC.getText(),
            tf_diachiNCC.getText(),
            tf_sdtNCC.getText(),
            tf_emailNCC.getText()
        };
        searchData search  = new searchData();
        
        try {
            ResultSet rs = search.search("nhacungcap", columns, values);
            
            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "Không có kết quả tương ứng");
            }else{
                rs.beforeFirst(); 
            
                DefaultTableModel model = (DefaultTableModel) tb_SP.getModel();
                model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowNCC = {rs.getString("Ma_NCC"),
                                                 rs.getString("Ten_NCC"),
                                                 rs.getString("DiaChi"),
                                                 rs.getString("SDT"), 
                                                 rs.getString("Email"), 
                        };
                        model.addRow(rowNCC);
                    }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_bt_findNCCMouseClicked

    private void bt_deleteNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_deleteNCCMouseClicked
        // TODO add your handling code here:
        String maNCC = tf_maNCC.getText().trim();
        if (maNCC.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã hàng cần xóa.");
            return;
        }

try {
    searchData search = new searchData();

    // Kiểm tra ncc có tồn tại
    String[] spColumns = {"Ma_NCC"};
    Object[] spValues = {maNCC};
    ResultSet rsNCC = search.search("nhacungcap", spColumns, spValues);

    if (!rsNCC.next()) {
        JOptionPane.showMessageDialog(null, "Không tìm thấy NCC với mã: " + maNCC);
        return;
    }

    // Kiểm tra b?ng liên quan
    ResultSet rsHDNH = search.search("hoadonnhaphang", spColumns, spValues);
    List<String> maHoaDonNhap = new ArrayList<>();
    while (rsHDNH.next()) {
        maHoaDonNhap.add(rsHDNH.getString("Ma_HDNH"));
    }

    // Xác nhận nếu có liên kết
    int confirm = JOptionPane.YES_OPTION;
    if ( !maHoaDonNhap.isEmpty()) {
        StringBuilder msg = new StringBuilder("Sản phẩm đang có liên kết với:\n");
        if (!maHoaDonNhap.isEmpty()) {
            msg.append(" - Hóa đơn nhập hàng: ").append(String.join(", ", maHoaDonNhap)).append("\n");
        }

        msg.append("Bạn có chắc chắn muốn xóa tất cả các dữ liệu này không?");
        confirm = JOptionPane.showConfirmDialog(null, msg.toString(), "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
    }

    if (confirm == JOptionPane.YES_OPTION) {
        Connection conn = new connectDatabase().getConnection();

        try {
          
            if (!maHoaDonNhap.isEmpty()) {
                PreparedStatement psDelCTHDNH = conn.prepareStatement(
                    "DELETE FROM chitiethoadonnhaphang WHERE Ma_HDNH = ?"
                );
                for (String maHD : maHoaDonNhap) {
                    psDelCTHDNH.setString(1, maHD);
                    psDelCTHDNH.executeUpdate();
                }
                psDelCTHDNH.close();
            }

            // 2. Xóa hóa đơn nhập hàng
            if (!maHoaDonNhap.isEmpty()) {
                PreparedStatement psDelHDNH = conn.prepareStatement(
                    "DELETE FROM hoadonnhaphang WHERE Ma_HDNH = ?"
                );
                for (String maHD : maHoaDonNhap) {
                    psDelHDNH.setString(1, maHD);
                    psDelHDNH.executeUpdate();
                }
                psDelHDNH.close();
            }

            // 3. Xóa nhà cung cấp
            PreparedStatement psDelNCC = conn.prepareStatement("DELETE FROM nhacungcap WHERE Ma_NCC = ?");
            psDelNCC.setString(1, maNCC);
            int rows = psDelNCC.executeUpdate();
            psDelNCC.close();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Đã xóa ncc và toàn bộ dữ liệu liên quan.");
                tf_maNCC.setText("");
                tf_tenNCC.setText("");
                tf_diachiNCC.setText("");
                tf_sdtNCC.setText("");
                tf_emailNCC.setText("");
                loadTableNCC();
                loadMaNCCIntoComboBoxes();
            } else {
                JOptionPane.showMessageDialog(null, "Không thể xóa sản phẩm.");
            }
        } finally {
            if (conn != null) conn.close();
        }
    }
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Lỗi khi xóa dữ liệu: " + e.getMessage());
}
    }//GEN-LAST:event_bt_deleteNCCMouseClicked

    private void bt_addNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_addNCCMouseClicked
        // TODO add your handling code here:
        tf_maNCC.setEditable(true);
        if((tf_maNCC.getText()).equals("")){
            JOptionPane.showMessageDialog(null, "vui lòng nhập mã ncc");
        }
        
        addData add =  new addData();
        getData get = new getData();
        String[] columns = {"Ma_NCC", "Ten_NCC", "DiaChi", "SDT", "Email"};
        Object[] values = {
            tf_maNCC.getText(),
            tf_tenNCC.getText(),
            tf_diachiNCC.getText(),
            tf_sdtNCC.getText(),
            tf_emailNCC.getText()
        };
        
        try {
        add.addData("nhacungcap", columns, values);
        JOptionPane.showMessageDialog(null, "Thêm ncc thành công");
        
        loadTableNCC();
        loadMaNCCIntoComboBoxes();
        } catch (SQLException e) {
            String message = e.getMessage().toLowerCase();
            if (message.contains("duplicate") || message.contains("primary key")) {
                JOptionPane.showMessageDialog(null, "Trùng mã nc");
            } 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_addNCCMouseClicked

    private void bt_XacNhanNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_XacNhanNhapMouseClicked
        // TODO add your handling code here:
        String maHDNH = generateMaHDNH();
        int soluong = Integer.parseInt(tf_SoLuongNhap.getText().trim());
        String masp = cbSPnhap.getSelectedItem().toString();
        java.util.Date utilDate = dc_Nhap.getDate();
        java.sql.Date ngaynhap = new java.sql.Date(utilDate.getTime());

        addData add =  new addData();
        updateData update = new updateData();
        String[] columnsHDNH = {"Ma_HDNH", "NgayNhap", "Ma_NCC", "Ma_NV"},
                 columnsCTHDNH = {"Ma_HDNH", "Ma_SP", "SoLuong","DonGia"};
        Object[] valuesHDNH = {
            maHDNH,
            ngaynhap,
            cbb_NCC.getSelectedItem().toString().trim(),
            cbb_maNVnhap.getSelectedItem().toString().trim()
        },
                valuesCTHDNH = {
                    maHDNH,
                    cbSPnhap.getSelectedItem().toString().trim(),
                    tf_SoLuongNhap.getText().trim(),
                    tf_dongianhap.getText().trim()
        };
        
        try {
            add.addData("hoadonnhaphang", columnsHDNH, valuesHDNH);
            add.addData("chitiethoadonnhaphang", columnsCTHDNH, valuesCTHDNH);
            boolean success = update.thayDoiGiaTriSoLuong("sanpham", "SoLuongTon","Ma_SP", masp, soluong, "increase");
            if (success) {
            JOptionPane.showMessageDialog(null, "Nhập hàng thành công và đã cập nhật tồn kho!");
            loadTableSP();
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật tồn kho thất bại! Không tìm thấy sản phẩm.");
        }
        } catch (SQLException ex) {
            Logger.getLogger(qlyKhoHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(qlyKhoHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_XacNhanNhapMouseClicked

    private void bt_XacNhanXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_XacNhanXuatMouseClicked
        // TODO add your handling code here:
        String maHDBH = generateMaHDBH();
        int soluong = Integer.parseInt(tf_SoLuongXuat.getText().trim());
        String masp = cbSPxuat.getSelectedItem().toString();
        java.util.Date utilDate = dc_xuat.getDate();
        java.sql.Date ngayxuat = new java.sql.Date(utilDate.getTime());

        addData add =  new addData();
        updateData update = new updateData();
        String[] columnsHDBH = {"Ma_HDBH", "NgayBan", "Ma_KH", "Ma_NV"},
                 columnsCTHDBH = {"Ma_HDBH", "Ma_SP", "SoLuong","DonGia"};
        Object[] valuesHDBH = {
            maHDBH,
            ngayxuat,
            cbb_KH.getSelectedItem().toString().trim(),
            cbb_maNVxuat.getSelectedItem().toString().trim()
        },
                valuesCTHDBH = {
                    maHDBH,
                    cbSPxuat.getSelectedItem().toString().trim(),
                    tf_SoLuongXuat.getText().trim(),
                    tf_dongiaxuat.getText().trim()
        };
        
        try {
            add.addData("hoadonbanhang", columnsHDBH, valuesHDBH);
            add.addData("chitiethoadonbanhang", columnsCTHDBH, valuesCTHDBH);
            boolean success = update.thayDoiGiaTriSoLuong("sanpham", "SoLuongTon","Ma_SP", masp, soluong, "decrease");
            if (success) {
            JOptionPane.showMessageDialog(null, "Nhập hàng thành công và đã cập nhật tồn kho!");
            loadTableSP();
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật tồn kho thất bại! Không tìm thấy sản phẩm.");
        }
        } catch (SQLException ex) {
            Logger.getLogger(qlyKhoHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(qlyKhoHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_XacNhanXuatMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_XacNhanNhap;
    private javax.swing.JButton bt_XacNhanXuat;
    private javax.swing.JButton bt_add;
    private javax.swing.JButton bt_addNCC;
    private javax.swing.JButton bt_delete;
    private javax.swing.JButton bt_deleteNCC;
    private javax.swing.JButton bt_find;
    private javax.swing.JButton bt_findNCC;
    private javax.swing.JButton bt_refresh;
    private javax.swing.JButton bt_update;
    private javax.swing.JButton bt_updateNCC;
    private javax.swing.JComboBox<String> cbSPnhap;
    private javax.swing.JComboBox<String> cbSPxuat;
    private javax.swing.JComboBox<String> cbb_KH;
    private javax.swing.JComboBox<String> cbb_NCC;
    private javax.swing.JComboBox<String> cbb_hienthiNCC;
    private javax.swing.JComboBox<String> cbb_maNVnhap;
    private javax.swing.JComboBox<String> cbb_maNVxuat;
    private javax.swing.JComboBox<String> combobox;
    private com.toedter.calendar.JDateChooser dc_Nhap;
    private com.toedter.calendar.JDateChooser dc_xuat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tb_NCC;
    private javax.swing.JTable tb_SP;
    private javax.swing.JTextField tf_DVT;
    private javax.swing.JTextField tf_GiaBan;
    private javax.swing.JTextField tf_GiaNhap;
    private javax.swing.JTextField tf_MaHang;
    private javax.swing.JTextField tf_SoLuongNhap;
    private javax.swing.JTextField tf_SoLuongXuat;
    private javax.swing.JTextField tf_TenHang;
    private javax.swing.JTextField tf_Ton;
    private javax.swing.JTextField tf_TonNhap;
    private javax.swing.JTextField tf_TonXuat;
    private javax.swing.JTextField tf_diachiNCC;
    private javax.swing.JTextField tf_dongianhap;
    private javax.swing.JTextField tf_dongiaxuat;
    private javax.swing.JTextField tf_emailNCC;
    private javax.swing.JTextField tf_maNCC;
    private javax.swing.JTextField tf_sdtNCC;
    private javax.swing.JTextField tf_tenNCC;
    // End of variables declaration//GEN-END:variables
}
