/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;
import Model.*;
import ConnectDatabase.connectDatabase;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.*;

/**
 *
 * @author ADMIN
 */
public class qlyNhanVienPanel extends javax.swing.JPanel {
    private String selectedMaNV = null,
                   selectedTenNv = null,
                   selectedChucVu = null,
                   selectedSDT = null,
                   selectedEmail = null,
                   selectedMaLuong = null,
                   selectedThang = null,
                   selectedNam = null,
                   selectedLuongCoBan = null,
                   selectedPhuCap = null,
                   selectedThuong = null,
                   selectedKhauTru = null,
                   selectedGhiChu = null,
                   selectedMaTK = null,
                   selectedUserName = null,
                   selectedPassword = null;
    
    /**
     * Creates new form qlyNhanVienPanel
     */
    public qlyNhanVienPanel() {
        initComponents();
        setupTableNVClickEvent();
        setupTableLuonglickEvent();
        setupTableTKlickEvent();
        try {
           loadTableNV();
           loadMaNVIntoComboBoxes();
        } catch (Exception e) {
        }
        
    }
    private void loadTableNV() throws SQLException, ClassNotFoundException{
        getData get = new getData();
        List<String[]> dataList = get.getData("nhanvien", new String[]{"Ma_NV", "Ten_NV", "ChucVu", "SDT", "Email"});
            DefaultTableModel model = (DefaultTableModel) tb_NhanVien.getModel();
            model.setRowCount(0); 

            for (String[] row : dataList) {
                 model.addRow(row);
            }
    }
    private void loadMaNVIntoComboBoxes() throws ClassNotFoundException {
        cbb_mnv.removeAllItems();
        cbb_MaNV.removeAllItems();
        getData get =  new getData();
        String[] columns = {"Ma_NV"};
        try {
            List<String[]> dataList = get.getData("nhanvien", columns);
            for (String[] strings : dataList) {
                 cbb_mnv.addItem(strings[0]);
                 cbb_MaNV.addItem(strings[0]);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void setupTableNVClickEvent() {
        tb_NhanVien.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tb_NhanVien.getSelectedRow();
                if (selectedRow != -1) {
                    selectedMaNV = tb_NhanVien.getValueAt(selectedRow, 0).toString(); 
                    selectedTenNv = tb_NhanVien.getValueAt(selectedRow, 1).toString();
                    selectedChucVu = tb_NhanVien.getValueAt(selectedRow, 2).toString();
                    selectedSDT = tb_NhanVien.getValueAt(selectedRow, 3).toString();
                    selectedEmail = tb_NhanVien.getValueAt(selectedRow, 4).toString();
                    tf_maNV.setEditable(false);
                    
                    tf_maNV.setText(selectedMaNV);
                    tf_tenNV.setText(selectedTenNv);
                    tf_ChucVu.setText(selectedChucVu);
                    tf_SDT.setText(selectedSDT);
                    tf_Email.setText(selectedEmail);
                    
                    String[] columnsNV = {"Ma_NV"};
                    Object[] valuesLuong = {selectedMaNV},
                             valuesTK = {selectedMaNV};
                    searchData search  = new searchData();
        
                    ResultSet rsLuong, rsTK;
                    try {
                        rsLuong = search.search("bangluong", columnsNV, valuesLuong);
                        rsTK = search.search("tk_nhanvien", columnsNV, valuesTK);
                        DefaultTableModel modelLuong = (DefaultTableModel) tb_bangluong.getModel();
                        DefaultTableModel modelTK = (DefaultTableModel) tb_taikhoan.getModel();
                        modelLuong.setRowCount(0);
                        modelTK.setRowCount(0);
                            while (rsLuong.next()) {
                                Object[] rowBangLuong = {rsLuong.getString("Ma_Luong"),
                                                 rsLuong.getString("Ma_NV"),
                                                 rsLuong.getString("Thang"),
                                                 rsLuong.getString("Nam"), 
                                                 rsLuong.getString("LuongCoBan"), 
                                                 rsLuong.getString("PhuCap"),
                                                 rsLuong.getString("Thuong"), 
                                                 rsLuong.getString("KhauTru"), 
                                                 rsLuong.getString("Ghichu")
                                        };
                                modelLuong.addRow(rowBangLuong);  
                            }
                            while (rsTK.next()) {
                                Object[] rowTK = {rsTK.getString("Ma_TK"),
                                                 rsTK.getString("Ma_NV"),
                                                 rsTK.getString("TenDangNhap"), 
                                                 rsTK.getString("MatKhau") 
                                        };
                                modelTK.addRow(rowTK);  
                            }
                        
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(qlyNhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(qlyNhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
           

                }   
            }
        });        
    }
    private void setupTableLuonglickEvent() {
        tb_bangluong.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tb_bangluong.getSelectedRow();
                if (selectedRow != -1) {
                    selectedMaLuong = tb_bangluong.getValueAt(selectedRow, 0).toString(); 
                    selectedMaNV = tb_bangluong.getValueAt(selectedRow, 1).toString();
                    selectedThang = tb_bangluong.getValueAt(selectedRow, 2).toString();
                    selectedNam = tb_bangluong.getValueAt(selectedRow, 3).toString();
                    selectedLuongCoBan = tb_bangluong .getValueAt(selectedRow, 4).toString();
                    selectedPhuCap = tb_bangluong.getValueAt(selectedRow, 5).toString();
                    selectedThuong = tb_bangluong.getValueAt(selectedRow, 6).toString();
                    selectedKhauTru = tb_bangluong.getValueAt(selectedRow,7).toString();
                    selectedGhiChu = tb_bangluong .getValueAt(selectedRow, 8).toString();
                    tf_maLuong.setEditable(false);
                    cbb_mnv.setEditable(false);
                    
                    cbb_mnv.setSelectedItem(selectedMaNV);
                    tf_maLuong.setText(selectedMaLuong);
                    sp_thang.setValue(Integer.parseInt(selectedThang));
                    sp_Nam.setValue(Integer.parseInt(selectedThang));
                    tf_luongcoban.setText(selectedLuongCoBan);
                    tf_thuong.setText(selectedThuong);
                    tf_ghichu.setText(selectedGhiChu);
                    tf_phucap.setText(selectedPhuCap);
                    tf_khautru.setText(selectedKhauTru);
                }
            }
        });        
    }
    private void setupTableTKlickEvent() {
        tb_taikhoan.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tb_taikhoan.getSelectedRow();
                if (selectedRow != -1) {
                    selectedMaTK = tb_taikhoan.getValueAt(selectedRow, 0).toString(); 
                    selectedUserName = tb_taikhoan.getValueAt(selectedRow, 2).toString();
                    selectedPassword = tb_taikhoan.getValueAt(selectedRow, 3).toString();
                    tf_maTK.setEditable(false);
                    cbb_MaNV.setEditable(false);
                    
                    
                    tf_maTK.setText(selectedMaTK);
                    tf_username.setText(selectedUserName);
                    tf_password.setText(selectedPassword);
                }
            }
        });        
    }
    private void refresh(){
        try {
            loadTableNV();
        } catch (Exception e) {
        }
        tf_ChucVu.setText("");
        tf_Email.setText("");
        tf_SDT.setText("");
        tf_ghichu.setText("");
        tf_khautru.setText("");
        tf_luongcoban.setText("");
        tf_maLuong.setText("");
        tf_maNV.setText("");
        tf_maTK.setText("");
        tf_password.setText("");
        tf_phucap.setText("");
        tf_tenNV.setText("");
        tf_thuong.setText("");
        tf_username.setText("");
        sp_Nam.setValue(2023);
        sp_thang.setValue(1);
        cbb_MaNV.setEditable(true);
        cbb_mnv.setEditable(true);
        
        DefaultTableModel modelLuong = (DefaultTableModel) tb_bangluong.getModel();
        DefaultTableModel modelTK = (DefaultTableModel) tb_taikhoan.getModel();
        modelLuong.setRowCount(0);
        modelTK.setRowCount(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_NhanVien = new javax.swing.JTable();
        bt_find = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tf_maNV = new javax.swing.JTextField();
        tf_tenNV = new javax.swing.JTextField();
        tf_ChucVu = new javax.swing.JTextField();
        tf_SDT = new javax.swing.JTextField();
        tf_Email = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        bt_updateNV = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        bt_refresh = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_taikhoan = new javax.swing.JTable();
        tf_maTK = new javax.swing.JTextField();
        tf_username = new javax.swing.JTextField();
        tf_password = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        bt_updateTK = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        bt_addTK = new javax.swing.JButton();
        cbb_MaNV = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_bangluong = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tf_maLuong = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tf_ghichu = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tf_luongcoban = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tf_phucap = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tf_thuong = new javax.swing.JTextField();
        tf_khautru = new javax.swing.JTextField();
        bt_suaLuong = new javax.swing.JButton();
        bt_xoaLuong = new javax.swing.JButton();
        sp_thang = new javax.swing.JSpinner();
        sp_Nam = new javax.swing.JSpinner();
        bt_addLuong = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cbb_mnv = new javax.swing.JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        tb_NhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên Nhân Viên", "Chức vụ", "SDT", "Email"
            }
        ));
        jScrollPane1.setViewportView(tb_NhanVien);

        bt_find.setBackground(new java.awt.Color(255, 204, 204));
        bt_find.setText("Tìm kiếm");
        bt_find.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_findMouseClicked(evt);
            }
        });

        jLabel2.setText("Mã nhân viên :");

        jLabel3.setText("Tên nhân viên:");

        jLabel4.setText("Chức vụ:");

        jLabel5.setText("SDT:");

        jLabel6.setText("Email:");

        tf_tenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_tenNVActionPerformed(evt);
            }
        });

        tf_SDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_SDTActionPerformed(evt);
            }
        });

        jButton2.setText("Thêm");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        bt_updateNV.setText("Sửa");
        bt_updateNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_updateNVMouseClicked(evt);
            }
        });

        jButton4.setText("Xóa");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        bt_refresh.setText("Làm mới");
        bt_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_refreshMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(tf_maNV, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_tenNV)
                            .addComponent(tf_ChucVu)
                            .addComponent(tf_SDT)
                            .addComponent(tf_Email))))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_find, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_updateNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_refresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_maNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_find))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tf_tenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_updateNV))
                    .addComponent(jLabel4))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4)))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tf_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_refresh))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        tb_taikhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã tài khoản", "Mã nhân viên", "Tên đăng nhập", "Mật khẩu"
            }
        ));
        jScrollPane4.setViewportView(tb_taikhoan);

        tf_maTK.setText(" ");

        tf_username.setText(" ");

        tf_password.setText(" ");

        jLabel16.setText("Mã tài khoản");

        jLabel18.setText("Tên đăng nhập");

        jLabel19.setText("Mật khẩu");

        bt_updateTK.setText("Sửa");
        bt_updateTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_updateTKMouseClicked(evt);
            }
        });

        jButton8.setText("Xóa");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });

        bt_addTK.setText("Thêm");
        bt_addTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_addTKMouseClicked(evt);
            }
        });

        jLabel17.setText("MNV:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel17))
                        .addGap(103, 103, 103)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbb_MaNV, 0, 100, Short.MAX_VALUE)
                            .addComponent(tf_maTK)
                            .addComponent(tf_username)
                            .addComponent(tf_password))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bt_addTK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_updateTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(71, 71, 71))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(tf_maTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel16))
                                    .addGap(28, 28, 28))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton8)))
                            .addComponent(bt_updateTK))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(tf_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(bt_addTK)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbb_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addContainerGap(176, Short.MAX_VALUE))))
        );

        tb_bangluong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã lương", "Mã nhân viên", "Tháng", "Năm", "Lương cơ bản", "Phụ cấp", "Thưởng", "Khấu trừ", "Ghi chú"
            }
        ));
        tb_bangluong.setRowHeight(50);
        jScrollPane2.setViewportView(tb_bangluong);

        jLabel7.setText("Mã lương");

        tf_maLuong.setText(" ");

        jLabel9.setText("Tháng");

        jLabel10.setText("Năm");

        jLabel11.setText("Ghi chú");

        tf_ghichu.setText(" ");

        jLabel12.setText("Lương cơ bản");

        tf_luongcoban.setText(" ");

        jLabel13.setText("Phụ cấp ");

        tf_phucap.setText(" ");

        jLabel14.setText("Thưởng");

        jLabel15.setText("Khấu trừ");

        tf_thuong.setText(" ");

        tf_khautru.setText(" ");

        bt_suaLuong.setText("Sửa");
        bt_suaLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_suaLuongMouseClicked(evt);
            }
        });

        bt_xoaLuong.setText("Xóa");
        bt_xoaLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_xoaLuongMouseClicked(evt);
            }
        });

        sp_thang.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));

        sp_Nam.setModel(new javax.swing.SpinnerNumberModel(2023, 2023, 2100, 1));

        bt_addLuong.setText("Thêm");
        bt_addLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_addLuongMouseClicked(evt);
            }
        });

        jLabel8.setText("MNV");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(39, 39, 39)
                        .addComponent(tf_maLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sp_thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(49, 49, 49)
                        .addComponent(tf_ghichu)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sp_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbb_mnv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(72, 72, 72)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_luongcoban)
                    .addComponent(tf_phucap, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_thuong, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(tf_khautru))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_suaLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_xoaLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_addLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(71, 71, 71))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tf_maLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(tf_luongcoban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(tf_thuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_suaLuong)
                    .addComponent(sp_thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sp_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_xoaLuong)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(tf_ghichu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(tf_phucap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(tf_khautru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cbb_mnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_addLuong)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(478, 478, 478)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tf_tenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_tenNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_tenNVActionPerformed

    private void tf_SDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_SDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_SDTActionPerformed

    private void bt_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_refreshMouseClicked
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_bt_refreshMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        tf_maNV.setEditable(true);
        if((tf_maNV.getText()).equals("")){
            JOptionPane.showMessageDialog(null, "vui lòng nhập mã nv");
        }
        
        addData add =  new addData();
        getData get = new getData();
        String[] columns = {"Ma_Nv", "Ten_NV", "ChucVu", "SDT", "Email"};
        Object[] values = {
            tf_maNV.getText().trim(),
            tf_tenNV.getText(),
            tf_ChucVu.getText(),
            tf_SDT.getText(),
            tf_Email.getText()
        };
        
        try {
        add.addData("nhanvien", columns, values);
        JOptionPane.showMessageDialog(null, "Thêm nv thành công");
        
            loadTableNV();
        } catch (SQLException e) {
            String message = e.getMessage().toLowerCase();
            if (message.contains("duplicate") || message.contains("primary key")) {
                JOptionPane.showMessageDialog(null, "Trùng mã nv");
            } 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton2MouseClicked

    private void bt_findMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_findMouseClicked
        // TODO add your handling code here:
        String[] columns = {"Ma_Nv", "Ten_NV", "ChucVu", "SDT", "Email"};
         Object[] values = {
            tf_maNV.getText().trim(),
            tf_tenNV.getText(),
            tf_ChucVu.getText(),
            tf_SDT.getText(),
            tf_Email.getText()
        };
        searchData search  = new searchData();
        
        try {
            ResultSet rs = search.search("nhanvien", columns, values);
            ResultSet rsLuong = search.search("bangluong", columns, values);
            ResultSet rsTK = search.search("tk_nhanvien", columns, values);
            
            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "Không có kết quả tương ứng");
            }else{
                rs.beforeFirst(); 
            
                DefaultTableModel model = (DefaultTableModel) tb_NhanVien.getModel();
                model.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowNhanVien = {rs.getString("Ma_NV"),
                                                 rs.getString("Ten_NV"),
                                                 rs.getString("ChucVu"),
                                                 rs.getString("SDT"), 
                                                 rs.getString("Email")
                        };
                        model.addRow(rowNhanVien);
                    }
            }
            DefaultTableModel modelLuong = (DefaultTableModel) tb_bangluong.getModel();
            DefaultTableModel modelTK = (DefaultTableModel) tb_taikhoan.getModel();
            modelLuong.setRowCount(0);
            modelTK.setRowCount(0);
                            while (rsLuong.next()) {
                                Object[] rowBangLuong = {rsLuong.getString("Ma_Luong"),
                                                 rsLuong.getString("Ma_NV"),
                                                 rsLuong.getString("Thang"),
                                                 rsLuong.getString("Nam"), 
                                                 rsLuong.getString("LuongCoBan"), 
                                                 rsLuong.getString("PhuCap"),
                                                 rsLuong.getString("Thuong"), 
                                                 rsLuong.getString("KhauTru"), 
                                                 rsLuong.getString("Ghichu")
                                        };
                                modelLuong.addRow(rowBangLuong);  
                            }
                            while (rsTK.next()) {
                                Object[] rowTK = {rsTK.getString("Ma_TK"),
                                                 rsTK.getString("Ma_NV"),
                                                 rsTK.getString("TenDangNhap"), 
                                                 rsTK.getString("MatKhau") 
                                        };
                                modelTK.addRow(rowTK);  
                            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_bt_findMouseClicked

    private void bt_updateNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_updateNVMouseClicked
        // TODO add your handling code here:
        if (selectedMaNV == null || selectedMaNV.equals("")) {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng cần sửa từ bảng.");
        return;
        }
        int confirm = JOptionPane.showConfirmDialog(null,
        "Bạn có chắc chắn muốn sửa nv hàng có mã: " + selectedMaNV + "?",
        "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
        
        String[] columns = {"Ma_Nv", "Ten_NV", "ChucVu", "SDT", "Email"};
         Object[] values = {
            tf_maNV.getText().trim(),
            tf_tenNV.getText(),
            tf_ChucVu.getText(),
            tf_SDT.getText(),
            tf_Email.getText()
        };
        
        updateData updater = new updateData();
        getData get = new getData();
        
        try {
            updater.updateData("nhanvien", "Ma_NV", selectedMaNV, columns, values);
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
            
            refresh();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật: " + e.getMessage());
        }
        }
    }//GEN-LAST:event_bt_updateNVMouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        String maNV = tf_maNV.getText().trim();
if (maNV.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã nhân viên cần xóa.");
    return;
}

try {
    searchData search = new searchData();

    // Kiểm tra nhân viên có tồn tại không
    String[] columns = {"Ma_NV"};
    Object[] values = {maNV};
    ResultSet rsNV = search.search("nhanvien", columns, values);

    if (!rsNV.next()) {
        JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên với mã: " + maNV);
        return;
    }

    // Lấy các dòng liên quan trong bảng lương và tài khoản
    ResultSet rsLuong = search.search("bangluong", columns, values);
    List<String> maLuongList = new ArrayList<>();
    while (rsLuong.next()) {
        maLuongList.add(rsLuong.getString("Ma_Luong"));
    }

    ResultSet rsTK = search.search("tk_nhanvien", columns, values);
    List<String> maTKList = new ArrayList<>();
    while (rsTK.next()) {
        maTKList.add(rsTK.getString("Ma_TK"));
    }

    // Nếu có liên kết thì hỏi xác nhận
    int confirm = JOptionPane.YES_OPTION;
    if (!maLuongList.isEmpty() || !maTKList.isEmpty()) {
        StringBuilder msg = new StringBuilder("Nhân viên đang có liên kết với:\n");
        if (!maLuongList.isEmpty()) {
            msg.append(" - Bảng lương: ").append(String.join(", ", maLuongList)).append("\n");
        }
        if (!maTKList.isEmpty()) {
            msg.append(" - Tài khoản: ").append(String.join(", ", maTKList)).append("\n");
        }
        msg.append("Bạn có chắc chắn muốn xóa toàn bộ dữ liệu này không?");
        confirm = JOptionPane.showConfirmDialog(null, msg.toString(), "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
    }

    if (confirm == JOptionPane.YES_OPTION) {
        Connection conn = new connectDatabase().getConnection();
        try {
            // 1. Xóa bảng lương
            PreparedStatement psXoaTrungGian = conn.prepareStatement("DELETE FROM nhanvien_bangluong WHERE Ma_Luong = ?");
            for (String maLuong : maLuongList) {
                psXoaTrungGian.setString(1, maLuong);
                psXoaTrungGian.executeUpdate();
            }
            psXoaTrungGian.close();
            if (!maLuongList.isEmpty()) {
                PreparedStatement psDelLuong = conn.prepareStatement(
                    "DELETE FROM bangluong WHERE Ma_Luong = ?"
                );
                for (String maLuong : maLuongList) {
                    psDelLuong.setString(1, maLuong);
                    psDelLuong.executeUpdate();
                }
                psDelLuong.close();
            }

            // 2. Xóa tài khoản nhân viên
            if (!maTKList.isEmpty()) {
                PreparedStatement psDelTK = conn.prepareStatement(
                    "DELETE FROM tk_nhanvien WHERE Ma_TK = ?"
                );
                for (String maTK : maTKList) {
                    psDelTK.setString(1, maTK);
                    psDelTK.executeUpdate();
                }
                psDelTK.close();
            }
            
            PreparedStatement psXoaChienLuoc = conn.prepareStatement("DELETE FROM nhanvien_chienluoctruyenthong WHERE Ma_NV = ?");
            psXoaChienLuoc.setString(1, maNV);
            psXoaChienLuoc.executeUpdate();
            psXoaChienLuoc.close();
            // 3. Xóa nhân viên
            PreparedStatement psDelNV = conn.prepareStatement("DELETE FROM nhanvien WHERE Ma_NV = ?");
            psDelNV.setString(1, maNV);
            int rows = psDelNV.executeUpdate();
            psDelNV.close();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Đã xóa nhân viên và toàn bộ dữ liệu liên quan.");
                refresh();
            } else {
                JOptionPane.showMessageDialog(null, "Không thể xóa nhân viên.");
            }
        } finally {
            if (conn != null) conn.close();
        }
    }
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Lỗi khi xóa dữ liệu: " + e.getMessage());
}

    }//GEN-LAST:event_jButton4MouseClicked

    private void bt_suaLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_suaLuongMouseClicked
        // TODO add your handling code here:
        if (selectedMaLuong == null || selectedMaLuong.equals("")) {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng cần sửa từ bảng.");
        return;
        }
        int confirm = JOptionPane.showConfirmDialog(null,
        "Bạn có chắc chắn muốn sửa luong hàng có mã: " + selectedMaLuong + "?",
        "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
        
            String thang = sp_thang.getValue().toString().trim();
            String nam = sp_Nam.getValue().toString().trim();
            float luongcoban = Float.parseFloat(tf_luongcoban.getText().trim()),
                  phucap = Float.parseFloat(tf_phucap.getText().trim()),
                  thuong = Float.parseFloat(tf_thuong.getText().trim()),
                  khautru = Float.parseFloat(tf_khautru.getText().trim());
        String[] columns = {"Ma_Luong", "Ma_NV", "Thang", "Nam", "LuongCoBan","PhuCap", "Thuong", "KhauTru", "GhiChu"};
         Object[] values = {
            tf_maLuong.getText(),
            cbb_mnv.getSelectedItem(),
            thang,
            nam,
            luongcoban,
            phucap,
            thuong,
            khautru,
            tf_ghichu.getText()
        };
        
        updateData updater = new updateData();
        getData get = new getData();
        
        try {
            updater.updateData("bangluong", "Ma_NV", selectedMaNV, columns, values);
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
            
          refresh();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật: " + e.getMessage());
        }
        }
    }//GEN-LAST:event_bt_suaLuongMouseClicked

    private void bt_updateTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_updateTKMouseClicked
        // TODO add your handling code here:
        if (selectedMaTK == null || selectedMaTK.equals("")) {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng cần sửa từ bảng.");
        return;
        }
        int confirm = JOptionPane.showConfirmDialog(null,
        "Bạn có chắc chắn muốn sửa TK hàng có mã: " + selectedMaTK + "?",
        "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
        
            
        String[] columns = {"Ma_TK", "Ma_NV", "TenDangNhap", "MatKhau"};
         Object[] values = {
            tf_maTK.getText(),
             cbb_MaNV.getSelectedItem(),
             tf_username.getText(),
             tf_password.getText()
        };
        
        updateData updater = new updateData();
        getData get = new getData();
        
        try {
            updater.updateData("tk_nhanvien", "Ma_NV", selectedMaNV, columns, values);
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
            
          refresh();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật: " + e.getMessage());
        }
        }
    }//GEN-LAST:event_bt_updateTKMouseClicked

    private void bt_xoaLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_xoaLuongMouseClicked
        // TODO add your handling code here:
        String thang = sp_thang.getValue().toString().trim();
            String nam = sp_Nam.getValue().toString().trim();
            float luongcoban = Float.parseFloat(tf_luongcoban.getText().trim()),
                  phucap = Float.parseFloat(tf_phucap.getText().trim()),
                  thuong = Float.parseFloat(tf_thuong.getText().trim()),
                  khautru = Float.parseFloat(tf_khautru.getText().trim());
        String[] columns = {"Ma_Luong", "Ma_NV", "Thang", "Nam", "LuongCoBan","PhuCap", "Thuong", "KhauTru", "GhiChu"};
         Object[] values = {
            tf_maLuong.getText(),
            cbb_mnv.getSelectedItem(),
            thang,
            nam,
            luongcoban,
            phucap,
            thuong,
            khautru,
            tf_ghichu.getText()
        };

        deleteData del = new deleteData();
        getData get = new getData();
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int deletedRows = del.delete("bangluong", columns, values);
                if (deletedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Đã xóa dữ liệu");
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp để xóa.");
                }
                refresh();
               
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi xóa dữ liệu.");
            }
        }
        
    }//GEN-LAST:event_bt_xoaLuongMouseClicked

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        // TODO add your handling code here:
        String[] columns = {"Ma_TK", "Ma_NV", "TenDangNhap", "MatKhau"};
         Object[] values = {
            tf_maTK.getText(),
             cbb_MaNV.getSelectedItem(),
             tf_username.getText(),
             tf_password.getText()
        };

        deleteData del = new deleteData();
        getData get = new getData();
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int deletedRows = del.delete("tk_nhanvien", columns, values);
                if (deletedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Đã xóa dữ liệu");
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp để xóa.");
                }
                refresh();
               
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi xóa dữ liệu.");
            }
        }
    }//GEN-LAST:event_jButton8MouseClicked

    private void bt_addLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_addLuongMouseClicked
        // TODO add your handling code here:
        tf_maLuong.setEditable(true);
        if((tf_maLuong.getText()).equals("")){
            JOptionPane.showMessageDialog(null, "vui lòng nhập mã luong");
        }
        
        addData add =  new addData();
        getData get = new getData();
        String thang = sp_thang.getValue().toString().trim();
            String nam = sp_Nam.getValue().toString().trim();
            float luongcoban = Float.parseFloat(tf_luongcoban.getText().trim()),
                  phucap = Float.parseFloat(tf_phucap.getText().trim()),
                  thuong = Float.parseFloat(tf_thuong.getText().trim()),
                  khautru = Float.parseFloat(tf_khautru.getText().trim());
        String[] columns = {"Ma_Luong", "Ma_NV", "Thang", "Nam", "LuongCoBan","PhuCap", "Thuong", "KhauTru", "GhiChu"};
         Object[] values = {
            tf_maLuong.getText(),
            cbb_mnv.getSelectedItem(),
            thang,
            nam,
            luongcoban,
            phucap,
            thuong,
            khautru,
            tf_ghichu.getText()
        };
        
        try {
        add.addData("bangluong", columns, values);
        JOptionPane.showMessageDialog(null, "Thêm b?ng luong thành công");
        
            refresh();
        } catch (SQLException e) {
            String message = e.getMessage().toLowerCase();
            if (message.contains("duplicate") || message.contains("primary key")) {
                JOptionPane.showMessageDialog(null, "Trùng mã luong");
            } 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_addLuongMouseClicked

    private void bt_addTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_addTKMouseClicked
        // TODO add your handling code here:
        tf_maTK.setEditable(true);
        if((tf_maLuong.getText()).equals("")){
            JOptionPane.showMessageDialog(null, "vui lòng nhập mã tk");
        }
        
        String[] columns = {"Ma_TK", "Ma_NV", "TenDangNhap", "MatKhau"};
         Object[] values = {
            tf_maTK.getText(),
             cbb_MaNV.getSelectedItem(),
             tf_username.getText(),
             tf_password.getText()
        };
         addData add = new addData();
        
        try {
        add.addData("tk_nhanvien", columns, values);
        JOptionPane.showMessageDialog(null, "Thêm tk thành công");
        
            refresh();
        } catch (SQLException e) {
            String message = e.getMessage().toLowerCase();
            if (message.contains("duplicate") || message.contains("primary key")) {
                JOptionPane.showMessageDialog(null, "Trùng mã tk");
            } 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_addTKMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addLuong;
    private javax.swing.JButton bt_addTK;
    private javax.swing.JButton bt_find;
    private javax.swing.JButton bt_refresh;
    private javax.swing.JButton bt_suaLuong;
    private javax.swing.JButton bt_updateNV;
    private javax.swing.JButton bt_updateTK;
    private javax.swing.JButton bt_xoaLuong;
    private javax.swing.JComboBox<String> cbb_MaNV;
    private javax.swing.JComboBox<String> cbb_mnv;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JSpinner sp_Nam;
    private javax.swing.JSpinner sp_thang;
    private javax.swing.JTable tb_NhanVien;
    private javax.swing.JTable tb_bangluong;
    private javax.swing.JTable tb_taikhoan;
    private javax.swing.JTextField tf_ChucVu;
    private javax.swing.JTextField tf_Email;
    private javax.swing.JTextField tf_SDT;
    private javax.swing.JTextField tf_ghichu;
    private javax.swing.JTextField tf_khautru;
    private javax.swing.JTextField tf_luongcoban;
    private javax.swing.JTextField tf_maLuong;
    private javax.swing.JTextField tf_maNV;
    private javax.swing.JTextField tf_maTK;
    private javax.swing.JTextField tf_password;
    private javax.swing.JTextField tf_phucap;
    private javax.swing.JTextField tf_tenNV;
    private javax.swing.JTextField tf_thuong;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
