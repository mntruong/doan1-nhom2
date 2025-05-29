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
public class qlyDonHangPanel extends javax.swing.JPanel {
    private String selectedMaHD = null,
                   selectedCTHD = null,
                   selectedNgayBan = null,
                   selectedMaKH = null,
                   selectedMaNV = null,
                   selectedMaSp = null,
                   selectedSoLuong = null,
                   selectedThanhTien = null,
                   selectedDonGia = null;
    private final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    /**
     * Creates new form qlyDonHangPanel
     */
    public qlyDonHangPanel() {
        initComponents();
        setupTableHDBHClickEvent();
        setupTableCTHDClickEvent();
        try {
            loadTableHDBH();
            loadMaHDIntoComboBoxes();
            loadMaKHIntoComboBoxes();
            loadMaNVIntoComboBoxes();
            loadMaSPIntoComboBoxes();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setupTableHDBHClickEvent() {
        tb_HDBH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tb_HDBH.getSelectedRow();
                if (selectedRow != -1) {
                    selectedMaHD = tb_HDBH.getValueAt(selectedRow, 0).toString();
                    selectedNgayBan = tb_HDBH.getValueAt(selectedRow, 1).toString();
                    selectedMaKH = tb_HDBH.getValueAt(selectedRow, 2).toString();
                    selectedMaNV = tb_HDBH.getValueAt(selectedRow, 3).toString();

                    tf_mahd.setText(selectedMaHD);
                    tf_mahd.setEditable(false);
                    cb_makh.setSelectedItem(selectedMaKH);
                    cb_manv.setSelectedItem(selectedMaNV);
                    try {
                        dc_ngayban.setDate(sdf.parse(selectedNgayBan));          
                    } catch (java.text.ParseException ex) {
                        Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    String[] columns = {"Ma_HDBH"};
                    Object[] values = {selectedMaHD};
                    searchData search = new searchData();
                    ResultSet rs;
                    
                    try {
                        rs = search.search("chitiethoadonbanhang", columns, values);
                        DefaultTableModel model = (DefaultTableModel) tb_cthdbh.getModel();
                        model.setRowCount(0);
                        
                        while (rs.next()) {
                                Object[] rowNV_CLTT = {rs.getString("Ma_HDBH"),
                                                 rs.getString("Ma_SP"),
                                                 rs.getString("SoLuong"),
                                                 rs.getString("DonGia"),
                                                 rs.getString("ThanhTien")
                                        };
                                model.addRow(rowNV_CLTT);  
                            }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        });
    }
    private void setupTableCTHDClickEvent() {
        tb_cthdbh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tb_cthdbh.getSelectedRow();
                if (selectedRow != -1) {
                    selectedCTHD = tb_cthdbh.getValueAt(selectedRow, 0).toString();
                    selectedMaSp = tb_cthdbh.getValueAt(selectedRow, 1).toString();
                    selectedSoLuong = tb_cthdbh.getValueAt(selectedRow, 2).toString();
                    selectedDonGia = tb_cthdbh.getValueAt(selectedRow, 3).toString();
                    selectedThanhTien = tb_cthdbh.getValueAt(selectedRow, 4).toString();

                    cb_macthd.setSelectedItem(selectedMaHD);
                    cb_macthd.setEnabled(false);
                    cb_maSP.setSelectedItem(selectedMaSp);
                    tf_soluong.setText(selectedSoLuong);
                    tf_dongia.setText(selectedDonGia);
                    tf_thanhtien.setText(selectedThanhTien);
                }
            }
        });
    }
    private void loadTableHDBH() throws ClassNotFoundException{
        getData get = new getData();
        try {
            List<String[]> dataList = get.getData("hoadonbanhang", new String[]{"Ma_HDBH", "NgayBan", "Ma_KH", "Ma_NV"});
            DefaultTableModel model = (DefaultTableModel) tb_HDBH.getModel();
            model.setRowCount(0); 

            for (String[] row : dataList) {
                 model.addRow(row);
            }
        } catch (Exception e) {
        }
        
    }
    private void loadMaNVIntoComboBoxes() throws ClassNotFoundException {
        cb_manv.removeAllItems();
        getData get =  new getData();
        String[] columns = {"Ma_NV"};
        cb_manv.addItem("");
        try {
            List<String[]> dataList = get.getData("nhanvien", columns);
            for (String[] strings : dataList) {
                 cb_manv.addItem(strings[0]);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void loadMaKHIntoComboBoxes() throws ClassNotFoundException {
        cb_makh.removeAllItems();
        getData get =  new getData();
        String[] columns = {"Ma_KH"};
        cb_makh.addItem("");
        try {
            List<String[]> dataList = get.getData("khachhang", columns);
            for (String[] strings : dataList) {
                 cb_makh.addItem(strings[0]);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void loadMaSPIntoComboBoxes() throws ClassNotFoundException {
        cb_maSP.removeAllItems();
        getData get =  new getData();
        String[] columns = {"Ma_SP"};
        cb_maSP.addItem("");
        try {
            List<String[]> dataList = get.getData("sanpham", columns);
            for (String[] strings : dataList) {
                 cb_maSP.addItem(strings[0]);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void loadMaHDIntoComboBoxes() throws ClassNotFoundException {
        cb_macthd.removeAllItems();
        getData get =  new getData();
        String[] columns = {"Ma_HDBH"};
        cb_macthd.addItem("");
        try {
            List<String[]> dataList = get.getData("hoadonbanhang", columns);
            for (String[] strings : dataList) {
                 cb_macthd.addItem(strings[0]);
              
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void refresh() {
        tf_mahd.setEditable(true);
        cb_macthd.setEnabled(true);
        try {
            loadMaHDIntoComboBoxes();
            loadMaKHIntoComboBoxes();
            loadMaNVIntoComboBoxes();
            loadMaSPIntoComboBoxes();
            loadTableHDBH();
        } catch (Exception e) {
           
        }
        DefaultTableModel model = (DefaultTableModel) tb_cthdbh.getModel();
        model.setRowCount(0);
        tf_soluong.setText("");
        tf_dongia.setText("");
        tf_thanhtien.setText("");
        tf_mahd.setText("");
        cb_maSP.setSelectedIndex(-1);
        cb_macthd.setSelectedIndex(-1);
        cb_makh.setSelectedIndex(-1);
        cb_manv.setSelectedIndex(-1);
        dc_ngayban.setDate(null);
        selectedMaHD = null;
        selectedNgayBan = null;
        selectedMaKH = null;
        selectedMaNV = null;
        selectedMaSp = null;
        selectedSoLuong = null;
        selectedThanhTien = null;
        selectedDonGia = null;
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bt_add_hdbh = new javax.swing.JButton();
        bt_sua_hdbh = new javax.swing.JButton();
        bt_xoa_hdbh = new javax.swing.JButton();
        bt_tim_hdbh = new javax.swing.JButton();
        cbb_sapxep_hdbh = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cb_macthd = new javax.swing.JComboBox<>();
        cb_maSP = new javax.swing.JComboBox<>();
        tf_soluong = new javax.swing.JTextField();
        tf_dongia = new javax.swing.JTextField();
        tf_thanhtien = new javax.swing.JTextField();
        bt_add_cthd = new javax.swing.JButton();
        bt_xoa_cthd = new javax.swing.JButton();
        bt_sua_cthd = new javax.swing.JButton();
        bt_tim_cthd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cb_makh = new javax.swing.JComboBox<>();
        cb_manv = new javax.swing.JComboBox<>();
        dc_ngayban = new com.toedter.calendar.JDateChooser();
        bt_refresh = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_HDBH = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_cthdbh = new javax.swing.JTable();
        tf_mahd = new javax.swing.JTextField();

        bt_add_hdbh.setText("Thêm");
        bt_add_hdbh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_add_hdbhMouseClicked(evt);
            }
        });

        bt_sua_hdbh.setText("Sửa");
        bt_sua_hdbh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_sua_hdbhMouseClicked(evt);
            }
        });

        bt_xoa_hdbh.setText("Xóa");
        bt_xoa_hdbh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_xoa_hdbhMouseClicked(evt);
            }
        });

        bt_tim_hdbh.setText("Tìm kiếm");
        bt_tim_hdbh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_tim_hdbhMouseClicked(evt);
            }
        });

        cbb_sapxep_hdbh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã hóa đơn", "Ngày bán", "Mã khách hàng", "Mã nhân viên" }));
        cbb_sapxep_hdbh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbb_sapxep_hdbhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_xoa_hdbh, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(bt_add_hdbh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_tim_hdbh, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bt_sua_hdbh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbb_sapxep_hdbh, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_add_hdbh)
                    .addComponent(bt_sua_hdbh))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_xoa_hdbh)
                    .addComponent(bt_tim_hdbh))
                .addGap(18, 18, 18)
                .addComponent(cbb_sapxep_hdbh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(195, Short.MAX_VALUE))
        );

        jLabel6.setText("Mã hóa đơn:");

        jLabel7.setText("Mã sản phầm:");

        jLabel8.setText("Số lượng:");

        jLabel9.setText("Đơn giá:");

        jLabel10.setText("Thành tiền:");

        tf_soluong.setText(" ");
        tf_soluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_soluongActionPerformed(evt);
            }
        });

        tf_dongia.setText(" ");

        tf_thanhtien.setText(" ");

        bt_add_cthd.setText("Thêm");
        bt_add_cthd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_add_cthdMouseClicked(evt);
            }
        });

        bt_xoa_cthd.setText("Xóa");
        bt_xoa_cthd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_xoa_cthdMouseClicked(evt);
            }
        });

        bt_sua_cthd.setText("Sửa");
        bt_sua_cthd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_sua_cthdMouseClicked(evt);
            }
        });

        bt_tim_cthd.setText("Tìm kiếm");
        bt_tim_cthd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_tim_cthdMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(49, 49, 49)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(33, 33, 33)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(26, 26, 26)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_macthd, 0, 140, Short.MAX_VALUE)
                    .addComponent(cb_maSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_soluong)
                    .addComponent(tf_dongia)
                    .addComponent(tf_thanhtien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_add_cthd, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(bt_xoa_cthd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_tim_cthd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_sua_cthd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cb_macthd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_sua_cthd)
                    .addComponent(bt_add_cthd))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cb_maSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_xoa_cthd)
                    .addComponent(bt_tim_cthd))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tf_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tf_dongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tf_thanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Quản lý hóa đơn đơn hàng");

        jLabel2.setText("Mã hóa đơn:");

        jLabel3.setText("Mã khách hàng:");

        jLabel4.setText("Mã nhân viên:");

        jLabel5.setText("Ngày bán:");

        bt_refresh.setText("Làm mới");
        bt_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_refreshMouseClicked(evt);
            }
        });

        tb_HDBH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn bán hàng", "Ngày bán", "Mã khách hàng", "Mã nhân viên"
            }
        ));
        jScrollPane5.setViewportView(tb_HDBH);

        jScrollPane3.setViewportView(jScrollPane5);

        tb_cthdbh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn bán hàng", "Mã sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane6.setViewportView(tb_cthdbh);

        jScrollPane4.setViewportView(jScrollPane6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(846, 846, 846)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(28, 28, 28)
                                        .addComponent(cb_manv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(49, 49, 49)
                                        .addComponent(dc_ngayban, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cb_makh, 0, 176, Short.MAX_VALUE)
                                            .addComponent(tf_mahd))))
                                .addGap(7, 7, 7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(bt_refresh)
                                .addGap(383, 383, 383)))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(517, 517, 517))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_refresh)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(tf_mahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(cb_makh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(cb_manv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(dc_ngayban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tf_soluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_soluongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_soluongActionPerformed

    private void bt_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_refreshMouseClicked
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_bt_refreshMouseClicked

    private void bt_add_hdbhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_hdbhMouseClicked
        // TODO add your handling code here:
        tf_mahd.setEditable(true);
        if((tf_mahd.getText()).equals("")){
            JOptionPane.showMessageDialog(null, "vui lòng nhập mã hoa don");
        }
        if (dc_ngayban.getDate() == null ) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn thời gian ban");
            return;
        }
        
         java.util.Date utilStartDate = dc_ngayban.getDate();
        java.sql.Date ngayban = new java.sql.Date(utilStartDate.getTime());

        
        addData add =  new addData();
        getData get = new getData();
        String[] columns = {"Ma_HDBH", "NgayBan", "Ma_KH", "Ma_Nv"};
        Object[] values = {
            tf_mahd.getText(),
            ngayban,
            cb_makh.getSelectedItem().toString(),
            cb_manv.getSelectedItem().toString() 
        };
        
        try {
        add.addData("hoadonbanhang", columns, values);
        JOptionPane.showMessageDialog(null, "Them hoa don thanh cong");
        loadTableHDBH();
        loadMaHDIntoComboBoxes();
        } catch (SQLException e) {
            String message = e.getMessage().toLowerCase();
            if (message.contains("duplicate") || message.contains("primary key")) {
                JOptionPane.showMessageDialog(null, "Trung ma hoa don");
            } 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_add_hdbhMouseClicked

    private void bt_add_cthdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_cthdMouseClicked
        // TODO add your handling code here:
        
        if (tf_soluong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhap so luong");
            return;
        }
        try {
            Float.parseFloat(tf_soluong.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "so luong phai la so hop le");
            return;
        }
        if (tf_dongia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhap don gia");
            return;
        }
        try {
            Float.parseFloat(tf_dongia.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "don gia phai la so hop le");
            return;
        }
        if (tf_thanhtien.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhap thanh tien");
            return;
        }
        try {
            Float.parseFloat(tf_thanhtien.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "thanh tien phai la so hop le");
            return;
        }
       
        float soluong = Float.parseFloat(tf_soluong.getText().trim());
        float dongia = Float.parseFloat(tf_dongia.getText().trim());
        float thanhtien = Float.parseFloat(tf_thanhtien.getText().trim());
        
        addData add =  new addData();
        getData get = new getData();
        String[] columns = {"Ma_HDBH", "Ma_SP", "SoLuong", "DonGia", "ThanhTien"};
        Object[] values = {
             cb_macthd.getSelectedItem().toString(),
             cb_maSP.getSelectedItem().toString(),
             soluong,
             dongia,
             thanhtien
        };
        
        try {
        add.addData("chitiethoadonbanhang", columns, values);
        JOptionPane.showMessageDialog(null, "Them thanh cong");
        loadTableHDBH();
        loadMaHDIntoComboBoxes();
        } catch (SQLException e) {
            String message = e.getMessage().toLowerCase();
            if (message.contains("duplicate") || message.contains("primary key")) {
                JOptionPane.showMessageDialog(null, "ma san pham da co trong hoa don");
            } 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_add_cthdMouseClicked

    private void bt_sua_hdbhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_sua_hdbhMouseClicked
        // TODO add your handling code here:
        
        int confirm = JOptionPane.showConfirmDialog(null,
        "ban co chac muon sua hoa don co ma: " + selectedMaHD + "?",
        "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            java.util.Date utilStartDate = dc_ngayban.getDate();
            java.sql.Date ngayban = new java.sql.Date(utilStartDate.getTime());
            
            
       String[] columns = {"Ma_HDBH", "NgayBan", "Ma_KH", "Ma_Nv"};
        Object[] values = {
            tf_mahd.getText(),
            ngayban,
            cb_makh.getSelectedItem().toString(),
            cb_manv.getSelectedItem().toString() 
        };
        
        updateData updater = new updateData();
        getData get = new getData();
        
        try {
            updater.updateData("hoadonbanhang", "Ma_HDBH", selectedMaHD, columns, values);
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
            
          refresh();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật: " + e.getMessage());
        }
        }                                   
    }//GEN-LAST:event_bt_sua_hdbhMouseClicked

    private void bt_sua_cthdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_sua_cthdMouseClicked
        // TODO add your handling code here:
        
        if (tf_soluong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhap so luong");
            return;
        }
        try {
            Float.parseFloat(tf_soluong.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "so luong phai la so hop le");
            return;
        }
        if (tf_dongia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhap don gia");
            return;
        }
        try {
            Float.parseFloat(tf_dongia.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "don gia phai la so hop le");
            return;
        }
        if (tf_thanhtien.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "nhap thanh tien");
            return;
        }
        try {
            Float.parseFloat(tf_thanhtien.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "thanh tien phai la so hop le");
            return;
        }
       
        float soluong = Float.parseFloat(tf_soluong.getText().trim());
        float dongia = Float.parseFloat(tf_dongia.getText().trim());
        float thanhtien = Float.parseFloat(tf_thanhtien.getText().trim());
        int confirm = JOptionPane.showConfirmDialog(null,
        "ban co chac muon sua chi tiet hoa don co ma: " + selectedCTHD + "?",
        "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            java.util.Date utilStartDate = dc_ngayban.getDate();
            java.sql.Date ngayban = new java.sql.Date(utilStartDate.getTime());
            
            
        String[] columns = {"Ma_HDBH", "Ma_SP", "SoLuong", "DonGia", "ThanhTien"};
        Object[] values = {
             cb_macthd.getSelectedItem().toString(),
             cb_maSP.getSelectedItem().toString(),
             soluong,
             dongia,
             thanhtien
        };
        
        updateData updater = new updateData();
        getData get = new getData();
        
        try {
            updater.updateData("chitiethoadonbanhang", "Ma_HDBH", selectedCTHD, columns, values);
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
            
          refresh();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật: " + e.getMessage());
        }
        }           
    }//GEN-LAST:event_bt_sua_cthdMouseClicked

    private void bt_xoa_hdbhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_xoa_hdbhMouseClicked
        // TODO add your handling code here:
        String maHD = tf_mahd.getText().trim();
if (maHD.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã hoặc chọn hóa đơn cần xóa.");
    return;
}

try {
    searchData search = new searchData();
    String[] columns = {"Ma_HDBH"};
    Object[] values = {maHD};

    // Kiểm tra hóa đơn có tồn tại không
    ResultSet rs = search.search("hoadonbanhang", columns, values);
    if (!rs.next()) {
        JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn cần xóa.");
        return;
    }

    // Kiểm tra liên kết với chi tiết hóa đơn
    ResultSet rsCT = search.search("chitiethoadonbanhang", columns, values);
    List<String> maSanPhamList = new ArrayList<>();
    while (rsCT.next()) {
        maSanPhamList.add(rsCT.getString("Ma_SP")); // hoặc cột bạn cần
    }

    int confirm = JOptionPane.YES_OPTION;
    if (!maSanPhamList.isEmpty()) {
        String msg = "Hóa đơn có liên kết với các sản phẩm: " + String.join(", ", maSanPhamList)
                   + "\nBạn có chắc chắn muốn xóa toàn bộ không?";
        confirm = JOptionPane.showConfirmDialog(null, msg, "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
    }

    if (confirm == JOptionPane.YES_OPTION) {
        Connection conn = new connectDatabase().getConnection();
        try {
            conn.setAutoCommit(false); // dùng transaction

            // Xóa chi tiết hóa đơn nếu có
            if (!maSanPhamList.isEmpty()) {
                PreparedStatement psDeleteCT = conn.prepareStatement(
                    "DELETE FROM chitiethoadonbanhang WHERE Ma_HDBH = ?"
                );
                psDeleteCT.setString(1, maHD);
                psDeleteCT.executeUpdate();
                psDeleteCT.close();
            }

            // Xóa hóa đơn
            PreparedStatement psDeleteHD = conn.prepareStatement(
                "DELETE FROM hoadonbanhang WHERE Ma_HDBH = ?"
            );
            psDeleteHD.setString(1, maHD);
            int affectedRows = psDeleteHD.executeUpdate();
            psDeleteHD.close();

            if (affectedRows > 0) {
                conn.commit(); // xác nhận xóa thành công
                JOptionPane.showMessageDialog(null, "Đã xóa hóa đơn và chi tiết liên quan.");
                refresh();
            } else {
                conn.rollback();
                JOptionPane.showMessageDialog(null, "Không thể xóa hóa đơn.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa: " + ex.getMessage());
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

} catch (SQLException | ClassNotFoundException ex) {
    JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
}

    }//GEN-LAST:event_bt_xoa_hdbhMouseClicked

    private void bt_xoa_cthdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_xoa_cthdMouseClicked
        String maHD = cb_macthd.getSelectedItem().toString();
        String maSP = cb_maSP.getSelectedItem().toString();


try {
    searchData search = new searchData();
    String[] columns = {"Ma_HDBH", "Ma_SP"};
    Object[] values = {maHD, maSP};

    // Kiểm tra xem dòng dữ liệu có tồn tại không
    ResultSet rs = search.search("chitiethoadonbanhang", columns, values);
    if (!rs.next()) {
        JOptionPane.showMessageDialog(null, "Không tìm thấy chi tiết hóa đơn với mã HĐ: " + maHD + " và mã SP: " + maSP);
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
        null,
        "Bạn có chắc chắn muốn xóa chi tiết hóa đơn với mã HĐ: " + maHD + " và mã SP: " + maSP + "?",
        "Xác nhận xóa",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {
        Connection conn = new connectDatabase().getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM chitiethoadonbanhang WHERE Ma_HDBH = ? AND Ma_SP = ?"
            );
            ps.setString(1, maHD);
            ps.setString(2, maSP);
            int rows = ps.executeUpdate();
            ps.close();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Đã xóa chi tiết hóa đơn thành công.");
                refresh(); // Làm mới bảng nếu cần
            } else {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu nào được xóa.");
            }

        } finally {
            conn.close();
        }
    }

} catch (SQLException | ClassNotFoundException e) {
    JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
}

    }//GEN-LAST:event_bt_xoa_cthdMouseClicked

    private void bt_tim_hdbhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_tim_hdbhMouseClicked
        // TODO add your handling code here:
            
            
       String[] columns = {"Ma_HDBH", "NgayBan", "Ma_KH", "Ma_Nv"};
        Object[] values = {
            tf_mahd.getText(),
            dc_ngayban.getDate() != null ? new java.sql.Date(dc_ngayban.getDate().getTime()) : null,
            cb_makh.getSelectedItem() != null ? cb_makh.getSelectedItem().toString() : "",
            cb_manv.getSelectedItem() != null ? cb_manv.getSelectedItem().toString() : ""
        };

        searchData search = new searchData();
        try {
            ResultSet rs = search.search("hoadonbanhang", columns, values);
            DefaultTableModel model = (DefaultTableModel) tb_HDBH.getModel();
            model.setRowCount(0);
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Không có kết quả tương ứng");
            } else {
                rs.beforeFirst();
                while (rs.next()) {
                    Object[] row = {
                        rs.getString("Ma_HDBH"),
                        rs.getString("NgayBan"),
                        rs.getString("Ma_KH"),
                        rs.getString("Ma_NV")
                    };
                    model.addRow(row);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + e.getMessage());
        }
    }//GEN-LAST:event_bt_tim_hdbhMouseClicked

    private void bt_tim_cthdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_tim_cthdMouseClicked
        // TODO add your handling code here:
        String[] columns = {"Ma_HDBH", "Ma_SP", "SoLuong", "DonGia", "ThanhTien"};
        Object[] values = {
             cb_macthd.getSelectedItem().toString(),
             cb_maSP.getSelectedItem().toString(),
             tf_soluong.getText().trim().isEmpty() ? null : Float.parseFloat(tf_soluong.getText().trim()),
             tf_dongia.getText().trim().isEmpty() ? null : Float.parseFloat(tf_dongia.getText().trim()),
             tf_thanhtien.getText().trim().isEmpty() ? null : Float.parseFloat(tf_thanhtien.getText().trim())
        };

        searchData search = new searchData();
        try {
            ResultSet rs = search.search("chitiethoadonbanhang", columns, values);
            DefaultTableModel model = (DefaultTableModel) tb_cthdbh.getModel();
            model.setRowCount(0);
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Không có kết quả tương ứng");
            } else {
                rs.beforeFirst();
                while (rs.next()) {
                    Object[] row = {
                        rs.getString("Ma_HDBH"),
                        rs.getString("Ma_SP"),
                        rs.getString("SoLuong"),
                        rs.getString("DonGia"),
                        rs.getString("ThanhTien")
                    };
                    model.addRow(row);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + e.getMessage());
        }
    }//GEN-LAST:event_bt_tim_cthdMouseClicked

    private void cbb_sapxep_hdbhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbb_sapxep_hdbhMouseClicked
        // TODO add your handling code here:
        tf_mahd.setText("");
        cb_makh.setSelectedIndex(-1);
        cb_manv.setSelectedIndex(-1);
        dc_ngayban.setDate(null);
        String selected = (String) cbb_sapxep_hdbh.getSelectedItem();
        if (selected == null) return;
        
        sortData sort = new sortData();
        ResultSet rs;
        System.out.println("Giá trị được chọn: '" + selected + "'");

        switch (selected) {
            case "Mã hóa đơn":
                try {
                    rs = sort.sortData("hoadonbanhang", "Ma_HDBH", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_HDBH.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] row = {rs.getString("Ma_HDBH"),
                                                 rs.getString("NgayBan"),
                                                 rs.getString("Ma_KH"),
                                                 rs.getString("Ma_NV") 
                        };
                        model.addRow(row);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            case "Ngày bán":
                try {
                    rs = sort.sortData("hoadonbanhang", "NgayBan", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_HDBH.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] row = {rs.getString("Ma_HDBH"),
                                                 rs.getString("NgayBan"),
                                                 rs.getString("Ma_KH"),
                                                 rs.getString("Ma_NV") 
                        };
                        model.addRow(row);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            case "Mã khách hàng":
                try {
                    rs = sort.sortData("hoadonbanhang", "Ma_KH", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_HDBH.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] row = {rs.getString("Ma_HDBH"),
                                                 rs.getString("NgayBan"),
                                                 rs.getString("Ma_KH"),
                                                 rs.getString("Ma_NV") 
                        };
                        model.addRow(row);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            case "Mã nhân viên":
                try {
                    rs = sort.sortData("hoadonbanhang", "Ma_NV", "ASC");
                    DefaultTableModel model = (DefaultTableModel) tb_HDBH.getModel();
                    model.setRowCount(0);
                    while (rs.next()) {
                        Object[] row = {rs.getString("Ma_HDBH"),
                                                 rs.getString("NgayBan"),
                                                 rs.getString("Ma_KH"),
                                                 rs.getString("Ma_NV") 
                        };
                        model.addRow(row);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(qlyDonHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            default:
                System.out.println("Không rõ lựa chọn: " + selected);
        }
    }//GEN-LAST:event_cbb_sapxep_hdbhMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add_cthd;
    private javax.swing.JButton bt_add_hdbh;
    private javax.swing.JButton bt_refresh;
    private javax.swing.JButton bt_sua_cthd;
    private javax.swing.JButton bt_sua_hdbh;
    private javax.swing.JButton bt_tim_cthd;
    private javax.swing.JButton bt_tim_hdbh;
    private javax.swing.JButton bt_xoa_cthd;
    private javax.swing.JButton bt_xoa_hdbh;
    private javax.swing.JComboBox<String> cb_maSP;
    private javax.swing.JComboBox<String> cb_macthd;
    private javax.swing.JComboBox<String> cb_makh;
    private javax.swing.JComboBox<String> cb_manv;
    private javax.swing.JComboBox<String> cbb_sapxep_hdbh;
    private com.toedter.calendar.JDateChooser dc_ngayban;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tb_HDBH;
    private javax.swing.JTable tb_cthdbh;
    private javax.swing.JTextField tf_dongia;
    private javax.swing.JTextField tf_mahd;
    private javax.swing.JTextField tf_soluong;
    private javax.swing.JTextField tf_thanhtien;
    // End of variables declaration//GEN-END:variables
}
