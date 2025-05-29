/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import ConnectDatabase.connectDatabase;
import Model.*;
import com.mysql.cj.protocol.Resultset;
import com.sun.source.tree.BreakTree;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class qlyKhachHangPanel extends javax.swing.JPanel {
    private String selectedMaKH = null,
                   selectedTenKH = null,
                   selectedGioiTinhKH = null,
                   selectedSDTKH = null,
                   selectedDiaChiKH = null,
                   selectedEmailKH = null,
                   selectedTuoiKH = null;
    /**
     * Creates new form qlyKhachHangPanel
     */
    public qlyKhachHangPanel() {
        initComponents();
        comboBox.setVisible(false);
        setupTableClickEvent();
    }
    
    private void setupTableClickEvent() {
        tb_Show.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tb_Show.getSelectedRow();
                if (selectedRow != -1) {
                    selectedMaKH = tb_Show.getValueAt(selectedRow, 0).toString(); 
                    selectedTenKH = tb_Show.getValueAt(selectedRow, 1).toString();
                    selectedGioiTinhKH = tb_Show.getValueAt(selectedRow, 2).toString();
                    selectedTuoiKH = tb_Show.getValueAt(selectedRow, 3).toString();
                    selectedSDTKH = tb_Show.getValueAt(selectedRow, 4).toString();
                    selectedDiaChiKH = tb_Show.getValueAt(selectedRow, 5).toString();
                    selectedEmailKH = tb_Show.getValueAt(selectedRow, 6).toString();
                    tf_KhID.setEditable(false);
                    
                    tf_KhID.setText(selectedMaKH);
                    tf_KhName.setText(selectedTenKH);
                    tf_SDT.setText(selectedSDTKH);
                    tf_DiaChi.setText(selectedDiaChiKH);
                    tf_Email.setText(selectedEmailKH);
                    buttonGroup1.clearSelection();
                    if(selectedGioiTinhKH.equals("Nam")){
                        rb_Nam.setSelected(true);
                    }else if(selectedGioiTinhKH.equals("Nữ")){
                        rb_Nu.setSelected(true);
                    }
                    sp_Tuoi.setValue(selectedTuoiKH);
                    
                    String[] columnsKhachHang = {"Ma_KH"};
                    Object[] valuesKhachHang = {selectedMaKH};
                    searchData search = new searchData();
                    DefaultTableModel modelTK = (DefaultTableModel) tb_TKKhach.getModel();
                    DefaultTableModel modelCTHD = (DefaultTableModel) tb_CTHoaDon.getModel();
                    modelTK.setRowCount(0);
                    modelCTHD.setRowCount(0);
                    
                    try {
                        ResultSet rsTK = search.search("tk_khachhang", columnsKhachHang, valuesKhachHang);
                        while (rsTK.next()) {                            
                            modelTK.addRow(new Object[]{
                                 rsTK.getString("Ma_TK"),
                                 rsTK.getString("Ma_KH"),
                                 rsTK.getString("TenDangNhap"),
                                 rsTK.getString("MatKhau")
                            });
                        }
                        rsTK.close(); 
                        tb_TKKhach.setModel(modelTK); 
                        
                        ResultSet rsHoaDon = search.search("hoadonbanhang", columnsKhachHang, valuesKhachHang);
                        ArrayList<String> maHDBHList = new ArrayList<>();
                        while (rsHoaDon.next()) {
                            String maHDBH = rsHoaDon.getString("Ma_HDBH");
                            if (!maHDBHList.contains(maHDBH)) { // Đảm bảo không trùng lặp
                                maHDBHList.add(maHDBH);
                            }
                        }
                       rsHoaDon.close();

                       if (!maHDBHList.isEmpty()) {
                           ResultSet rsCTHoaDon = search.searchIn("chitiethoadonbanhang", "Ma_HDBH", maHDBHList);
                           while (rsCTHoaDon.next()) {
                               modelCTHD.addRow(new Object[]{
                                   rsCTHoaDon.getString("Ma_HDBH"),
                                   rsCTHoaDon.getString("Ma_SP"),
                                   rsCTHoaDon.getInt("SoLuong"),
                                   rsCTHoaDon.getDouble("DonGia"),
                                   rsCTHoaDon.getDouble("ThanhTien")
                                });
                            }
                            rsCTHoaDon.close();
                       }
                        tb_CTHoaDon.setModel(modelCTHD);

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, "Lỗi khi tải driver JDBC", ex);
        JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException ex) {
        Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, "Lỗi khi truy vấn cơ sở dữ liệu", ex);
        JOptionPane.showMessageDialog(null, "Lỗi truy vấn dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
            }
        });
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_KhID = new javax.swing.JTextField();
        bt_Find = new javax.swing.JButton();
        bt_Add = new javax.swing.JButton();
        bt_Update = new javax.swing.JButton();
        bt_Delete = new javax.swing.JButton();
        bt_Refresh = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Show = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tf_KhName = new javax.swing.JTextField();
        rb_Nam = new javax.swing.JRadioButton();
        rb_Nu = new javax.swing.JRadioButton();
        sp_Tuoi = new javax.swing.JSpinner();
        tf_SDT = new javax.swing.JTextField();
        tf_DiaChi = new javax.swing.JTextField();
        tf_Email = new javax.swing.JTextField();
        bt_Show = new javax.swing.JButton();
        comboBox = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_CTHoaDon = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_TKKhach = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ KHÁCH HÀNG");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(587, 30, -1, -1));

        jLabel2.setText("Mã khách hàng :");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        tf_KhID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_KhIDActionPerformed(evt);
            }
        });
        add(tf_KhID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 360, -1));

        bt_Find.setText("Tìm kiếm");
        bt_Find.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_FindMouseClicked(evt);
            }
        });
        add(bt_Find, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 90, -1));

        bt_Add.setText("Thêm");
        bt_Add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_AddMouseClicked(evt);
            }
        });
        add(bt_Add, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 90, -1));

        bt_Update.setText("Sửa");
        bt_Update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_UpdateMouseClicked(evt);
            }
        });
        add(bt_Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 90, -1));

        bt_Delete.setText("Xóa");
        bt_Delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_DeleteMouseClicked(evt);
            }
        });
        add(bt_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, 90, -1));

        bt_Refresh.setText("Làm mới");
        bt_Refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_RefreshMouseClicked(evt);
            }
        });
        add(bt_Refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 110, 90, -1));
        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1426, 528, 100, 100));

        tb_Show.setBackground(new java.awt.Color(153, 255, 255));
        tb_Show.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Giới tính", "Tuổi", "Số điện thoại", "Địa chỉ", "Email"
            }
        ));
        jScrollPane1.setViewportView(tb_Show);

        jScrollPane3.setViewportView(jScrollPane1);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 1340, 440));

        jLabel4.setText("Tên khách hàng:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel5.setText("Giới tính:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel6.setText("Tuổi:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel7.setText("Số điện thoại:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        jLabel8.setText("Địa chỉ:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        jLabel9.setText("Email:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        tf_KhName.setText(" ");
        add(tf_KhName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 360, -1));

        buttonGroup1.add(rb_Nam);
        rb_Nam.setText("Nam");
        rb_Nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_NamActionPerformed(evt);
            }
        });
        add(rb_Nam, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, -1, -1));

        buttonGroup1.add(rb_Nu);
        rb_Nu.setText("Nữ");
        rb_Nu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_NuActionPerformed(evt);
            }
        });
        add(rb_Nu, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, -1, -1));

        sp_Tuoi.setModel(new javax.swing.SpinnerListModel(new String[] {" ", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60"}));
        add(sp_Tuoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 120, -1));

        tf_SDT.setText(" ");
        add(tf_SDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 360, -1));

        tf_DiaChi.setText(" ");
        add(tf_DiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 360, -1));

        tf_Email.setText(" ");
        add(tf_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 360, -1));

        bt_Show.setText("Hiển thị");
        bt_Show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ShowActionPerformed(evt);
            }
        });
        add(bt_Show, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, 90, -1));

        comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã khách", "Tên", "Giới tính", "Tuổi", "SDT", "Địa chỉ", "Email" }));
        comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxActionPerformed(evt);
            }
        });
        add(comboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 190, 90, 20));

        tb_CTHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane4.setViewportView(tb_CTHoaDon);

        tb_TKKhach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã tài khoản", "Mã khách hàng", "Tên đăng nhập", "Mật khẩu"
            }
        ));
        jScrollPane5.setViewportView(tb_TKKhach);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Tài khoản");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Đơn hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
            .addComponent(jScrollPane5)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, 570, 310));
    }// </editor-fold>//GEN-END:initComponents

    private void tf_KhIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_KhIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_KhIDActionPerformed

    private void rb_NamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_NamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_NamActionPerformed

    private void rb_NuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_NuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_NuActionPerformed

    private void bt_RefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_RefreshMouseClicked
        // TODO add your handling code here:
        tf_DiaChi.setText("");
        tf_Email.setText("");
        tf_KhID.setText("");
        tf_KhName.setText("");
        tf_SDT.setText("");
        buttonGroup1.clearSelection();
        sp_Tuoi.setValue(" ");
        DefaultTableModel model = (DefaultTableModel) tb_Show.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_bt_RefreshMouseClicked

    private void bt_ShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ShowActionPerformed
        // TODO add your handling code here:
        comboBox.setVisible(!comboBox.isVisible());
    }//GEN-LAST:event_bt_ShowActionPerformed

    private void comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxActionPerformed
        // TODO add your handling code here:
        tf_DiaChi.setText("");
        tf_Email.setText("");
        tf_KhID.setText("");
        tf_KhName.setText("");
        tf_SDT.setText("");
        buttonGroup1.clearSelection();
        sp_Tuoi.setValue(" ");
        String selected = (String) comboBox.getSelectedItem();
        if (selected == null) return;
        
        sortData sort = new sortData();
        ResultSet rs;
        System.out.println("Giá trị được chọn: '" + selected + "'");

        switch (selected) {
            case "Mã khách":
                try {
                    rs = sort.sortData("khachhang", "Ma_KH", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_Show.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowKhachHang = {rs.getString("Ma_KH"),
                                                 rs.getString("Ten_KH"),
                                                 rs.getString("GioiTinh"),
                                                 rs.getString("Tuoi"), 
                                                 rs.getString("SDT"), 
                                                 rs.getString("DiaChi"), 
                                                 rs.getString("Email")
                        };
                        model.addRow(rowKhachHang);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Tên":
                try {
                    rs = sort.sortData("khachhang", "Ten_KH", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_Show.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowKhachHang = {rs.getString("Ma_KH"),
                                                 rs.getString("Ten_KH"),
                                                 rs.getString("GioiTinh"),
                                                 rs.getString("Tuoi"), 
                                                 rs.getString("SDT"), 
                                                 rs.getString("DiaChi"), 
                                                 rs.getString("Email")
                        };
                        model.addRow(rowKhachHang);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Giới tính":
                try {
                    rs = sort.sortData("khachhang", "GioiTinh", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_Show.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowKhachHang = {rs.getString("Ma_KH"),
                                                 rs.getString("Ten_KH"),
                                                 rs.getString("GioiTinh"),
                                                 rs.getString("Tuoi"), 
                                                 rs.getString("SDT"), 
                                                 rs.getString("DiaChi"), 
                                                 rs.getString("Email")
                        };
                        model.addRow(rowKhachHang);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Tuổi":
                try {
                    rs = sort.sortData("khachhang", "Tuoi", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_Show.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowKhachHang = {rs.getString("Ma_KH"),
                                                 rs.getString("Ten_KH"),
                                                 rs.getString("GioiTinh"),
                                                 rs.getString("Tuoi"), 
                                                 rs.getString("SDT"), 
                                                 rs.getString("DiaChi"), 
                                                 rs.getString("Email")
                        };
                        model.addRow(rowKhachHang);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            case "SDT":
                try {
                    rs = sort.sortData("khachhang", "SDT", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_Show.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowKhachHang = {rs.getString("Ma_KH"),
                                                 rs.getString("Ten_KH"),
                                                 rs.getString("GioiTinh"),
                                                 rs.getString("Tuoi"), 
                                                 rs.getString("SDT"), 
                                                 rs.getString("DiaChi"), 
                                                 rs.getString("Email")
                        };
                        model.addRow(rowKhachHang);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            case "Địa chỉ":
                try {
                    rs = sort.sortData("khachhang", "DiaChi", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_Show.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowKhachHang = {rs.getString("Ma_KH"),
                                                 rs.getString("Ten_KH"),
                                                 rs.getString("GioiTinh"),
                                                 rs.getString("Tuoi"), 
                                                 rs.getString("SDT"), 
                                                 rs.getString("DiaChi"), 
                                                 rs.getString("Email")
                        };
                        model.addRow(rowKhachHang);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            case "Email":
                try {
                    rs = sort.sortData("khachhang", "Email", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_Show.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowKhachHang = {rs.getString("Ma_KH"),
                                                 rs.getString("Ten_KH"),
                                                 rs.getString("GioiTinh"),
                                                 rs.getString("Tuoi"), 
                                                 rs.getString("SDT"), 
                                                 rs.getString("DiaChi"), 
                                                 rs.getString("Email")
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
    }//GEN-LAST:event_comboBoxActionPerformed

    private void bt_FindMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_FindMouseClicked
        // TODO add your handling code here:
        String tuoi = sp_Tuoi.getValue().toString().trim();
        String gioitinh = "";
        if(rb_Nam.isSelected()){
           gioitinh = "Nam";
        }else if(rb_Nu.isSelected()){
            gioitinh = "Nữ";
        }else{
            gioitinh = "";
        }
        String[] columns = {"Ma_KH", "Ten_KH", "GioiTinh", "Tuoi", "SDT", "DiaChi", "Email"};
        Object[] values = {
            tf_KhID.getText(),
            tf_KhName.getText(),
            gioitinh,
            tuoi,
            tf_SDT.getText(),
            tf_DiaChi.getText(),
            tf_Email.getText()
        };
        searchData search  = new searchData();
        
        try {
            ResultSet rs = search.search("khachhang", columns, values);
            
            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "Không có kết quả tương ứng");
            }else{
                rs.beforeFirst(); 
            
                DefaultTableModel model = (DefaultTableModel) tb_Show.getModel();
                model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowKhachHang = {rs.getString("Ma_KH"),
                                                 rs.getString("Ten_KH"),
                                                 rs.getString("GioiTinh"),
                                                 rs.getString("Tuoi"), 
                                                 rs.getString("SDT"), 
                                                 rs.getString("DiaChi"), 
                                                 rs.getString("Email")
                        };
                        model.addRow(rowKhachHang);
                    }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_bt_FindMouseClicked

    private void bt_AddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_AddMouseClicked
        // TODO add your handling code here:
        if((tf_KhID.getText()).equals("")){
            JOptionPane.showMessageDialog(null, "vui lòng nhập mã khách hàng");
        }
        
        addData add =  new addData();
        getData get = new getData();
        String tuoi = sp_Tuoi.getValue().toString().trim();
        String gioitinh = "";
        if(rb_Nam.isSelected()){
           gioitinh = "Nam";
        }else if(rb_Nu.isSelected()){
            gioitinh = "Nữ";
        }else{
            gioitinh = "";
        }
        
        String[] columns = {"Ma_KH", "Ten_KH", "GioiTinh", "Tuoi", "SDT", "DiaChi", "Email"};
        Object[] values = {
            tf_KhID.getText(),
            tf_KhName.getText(),
            gioitinh,
            tuoi,
            tf_SDT.getText(),
            tf_DiaChi.getText(),
            tf_Email.getText()
        };
        
        try {
        add.addData("khachhang", columns, values);
        JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
        
        List<String[]> dataList = get.getData("khachhang", new String[]{"Ma_KH", "Ten_KH", "GioiTinh", "Tuoi", "SDT", "DiaChi", "Email"});
        DefaultTableModel model = (DefaultTableModel) tb_Show.getModel();
            model.setRowCount(0); 

            for (String[] row : dataList) {
                 model.addRow(row);
            }
        } catch (SQLException e) {
            String message = e.getMessage().toLowerCase();
            if (message.contains("duplicate") || message.contains("primary key")) {
                JOptionPane.showMessageDialog(null, "Trùng mã đơn hàng");
            } 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_AddMouseClicked

    private void bt_DeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_DeleteMouseClicked
        // TODO add your handling code here:
        String maKH = tf_KhID.getText().trim();
    if (maKH.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập mã khách hàng cần xóa.");
        return;
    }

    try {
        searchData search = new searchData();

        //Kiểm tra khách hàng có tồn tại
        String[] khColumns = {"Ma_KH"};
        Object[] khValues = {maKH};
        ResultSet rsKH = search.search("khachhang", khColumns, khValues);

        if (!rsKH.next()) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với mã: " + maKH);
            return;
        }


        //Kiểm tra hóa đơn bán hàng liên quan
        ResultSet rsHDBH = search.search("hoadonbanhang", khColumns, khValues);
        List<String> maHoaDons = new ArrayList<>();
        while (rsHDBH.next()) {
            maHoaDons.add(rsHDBH.getString("Ma_HDBH"));
        }
        //ki?m tra tài kho?n khách hàng
        ResultSet rsTKKH = search.search("tk_khachhang", khColumns, khValues);
        List<String> maTK_Khachhang = new ArrayList<>();
        while (rsTKKH.next()) {
            maTK_Khachhang.add(rsTKKH.getString("Ma_TK"));
        }
        //Xác nhận nếu có liên kết
        int confirm = JOptionPane.YES_OPTION;
        if ( !maHoaDons.isEmpty() || !maTK_Khachhang.isEmpty()) {
            StringBuilder msg = new StringBuilder("Khách hàng đang có:");

            if (!maHoaDons.isEmpty()) {
                msg.append("\n - Hóa đơn bán hàng: ").append(String.join(", ", maHoaDons));
            }
            if (!maTK_Khachhang.isEmpty()) {
                msg.append("\n - Tài khoản: ").append(String.join(", ", maTK_Khachhang));
            }

            msg.append("\nBạn có chắc chắn muốn xóa tất cả các dữ liệu này không?");
            confirm = JOptionPane.showConfirmDialog(null, msg.toString(), "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        }

        if (confirm == JOptionPane.YES_OPTION) {
            Connection conn = new connectDatabase().getConnection();
            
            //Xóa chi ti?t hóa don
            if (!maHoaDons.isEmpty()) {
                PreparedStatement psDelCTHDBH = conn.prepareStatement("DELETE FROM chitiethoadonbanhang WHERE Ma_HDBH = ?");
                for (String maHD : maHoaDons) {
                    psDelCTHDBH.setString(1, maHD);
                    psDelCTHDBH.executeUpdate();
                }
            }
            //Xóa hóa đơn bán hàng
            if (!maHoaDons.isEmpty()) {
                PreparedStatement psDelHDBH = conn.prepareStatement("DELETE FROM hoadonbanhang WHERE Ma_KH = ?");
                psDelHDBH.setString(1, maKH);
                psDelHDBH.executeUpdate();
            }
            
            //Xóa tk
            if (!maTK_Khachhang.isEmpty()) {
                PreparedStatement psDelDH = conn.prepareStatement("DELETE FROM tk_khachhang WHERE Ma_KH = ?");
                psDelDH.setString(1, maKH);
                psDelDH.executeUpdate();
            }
            //Xóa khách hàng
            PreparedStatement psDelKH = conn.prepareStatement("DELETE FROM khachhang WHERE Ma_KH = ?");
            psDelKH.setString(1, maKH);
            int rows = psDelKH.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Đã xóa khách hàng và toàn bộ dữ liệu liên quan.");
                tf_DiaChi.setText("");
                tf_Email.setText("");
                tf_KhID.setText("");
                tf_KhName.setText("");
                tf_SDT.setText("");
                buttonGroup1.clearSelection();
                sp_Tuoi.setValue(" ");
                DefaultTableModel model = (DefaultTableModel) tb_Show.getModel();
                model.setRowCount(0);
            } else {
                JOptionPane.showMessageDialog(null, "Không thể xóa khách hàng.");
            }
            getData get = new getData();
            List<String[]> dataList = get.getData("khachhang", new String[]{"Ma_KH", "Ten_KH", "GioiTinh", "Tuoi", "SDT", "DiaChi", "Email"});
            DefaultTableModel model = (DefaultTableModel) tb_Show.getModel(); 
            for (String[] row : dataList) {
                 model.addRow(row);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi xóa dữ liệu.");
    }
    }//GEN-LAST:event_bt_DeleteMouseClicked

    private void bt_UpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_UpdateMouseClicked
        // TODO add your handling code here:
        if (selectedMaKH == null || selectedMaKH.equals("")) {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần sửa từ bảng.");
        return;
        }
        int confirm = JOptionPane.showConfirmDialog(null,
        "Bạn có chắc chắn muốn sửa khách hàng có mã: " + selectedMaKH + "?",
        "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            
            String tuoi = sp_Tuoi.getValue().toString().trim();
        String gioitinh = "";
        if(rb_Nam.isSelected()){
           gioitinh = "Nam";
        }else if(rb_Nu.isSelected()){
            gioitinh = "Nữ";
        }else{
            gioitinh = "";
        }
        
        String[] columns = {"Ma_KH", "Ten_KH", "GioiTinh", "Tuoi", "SDT", "DiaChi", "Email"};
        Object[] values = {
            tf_KhID.getText(),
            tf_KhName.getText(),
            gioitinh,
            tuoi,
            tf_SDT.getText(),
            tf_DiaChi.getText(),
            tf_Email.getText()
        };
        
        updateData updater = new updateData();
        getData get = new getData();
        
        try {
            updater.updateData("khachhang", "Ma_KH", selectedMaKH, columns, values);
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
            
            List<String[]> dataList = get.getData("khachhang", new String[]{"Ma_KH", "Ten_KH", "GioiTinh", "Tuoi", "SDT", "DiaChi", "Email"});
            DefaultTableModel model = (DefaultTableModel) tb_Show.getModel();
            model.setRowCount(0); 

            for (String[] row : dataList) {
                 model.addRow(row);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật: " + e.getMessage());
        }
        }
    }//GEN-LAST:event_bt_UpdateMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Add;
    private javax.swing.JButton bt_Delete;
    private javax.swing.JButton bt_Find;
    private javax.swing.JButton bt_Refresh;
    private javax.swing.JButton bt_Show;
    private javax.swing.JButton bt_Update;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JComboBox<String> comboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JRadioButton rb_Nam;
    private javax.swing.JRadioButton rb_Nu;
    private javax.swing.JSpinner sp_Tuoi;
    private javax.swing.JTable tb_CTHoaDon;
    private javax.swing.JTable tb_Show;
    private javax.swing.JTable tb_TKKhach;
    private javax.swing.JTextField tf_DiaChi;
    private javax.swing.JTextField tf_Email;
    private javax.swing.JTextField tf_KhID;
    private javax.swing.JTextField tf_KhName;
    private javax.swing.JTextField tf_SDT;
    // End of variables declaration//GEN-END:variables
}
