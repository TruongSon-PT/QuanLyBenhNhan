/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import DAO.QuanLyThanhToan;
import DoiTuong.ThanhToan;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class ThanhToanVienPhi extends javax.swing.JFrame {

    private QuanLyThanhToan qltt;
    private final String userName;
    private final String passWord;
    private final String ND;

    /**
     * Creates new form ThanhToanVienPhi
     */
    public ThanhToanVienPhi(String userName, String passWord, String ND) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.khoiTaoUI();
        this.userName = userName;
        this.passWord = passWord;
        this.ketNoi();
        this.hienThi();
        this.ND = ND;
        this.lblUserName.setText(ND);
    }

    public void ketNoi() {
        try {
            this.qltt = new QuanLyThanhToan(userName, passWord);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Quyền hạn của bạn không đủ để sử dụng chức năng này!");
        }
    }

    public void khoiTaoUI() {
        this.txtMaThanhToan.setText("");
        this.txtMaThanhToan.setEnabled(true);
        this.txtMaBN.setText("");
        this.txtVienPhi.setText("");
        this.rdoDaThanhToan.setSelected(true);
    }

    public void hienThi() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblDanhSach.getModel();
        dtm.setRowCount(0);
        try {
            ArrayList<ThanhToan> list = this.qltt.xuatDanhSach();
            for (ThanhToan x : list) {
                Object[] rowData = new Object[]{
                    x.getMaTT(),
                    x.getMaBN(),
                    x.getVienPhi(),
                    x.getTrangThai()
                };
                dtm.addRow(rowData);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Quyền hạn của bạn không đủ để sử dụng chức năng này!");
        }
    }

    public void hienThiCT() {
        int viTri = this.tblDanhSach.getSelectedRow();
        if (viTri == -1) {
            return;
        }
        try {
            ThanhToan tt = this.qltt.xuatDanhSach().get(viTri);
            this.txtMaThanhToan.setText(tt.getMaTT());
            this.txtMaThanhToan.setEnabled(false);
            this.txtMaBN.setText(tt.getMaBN());
            this.txtVienPhi.setText(tt.getVienPhi().toString());
            if (tt.getTrangThai().equalsIgnoreCase("Đã thanh toán")) {
                this.rdoDaThanhToan.setSelected(true);
            } else {
                this.rdoChuaThanhToan.setSelected(true);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối dữ liệu!");
        }
    }

    public ThanhToan docForm() {
        String maTT = this.txtMaThanhToan.getText();
        if (maTT.length() == 0) {
            JOptionPane.showMessageDialog(this, "Mã thanh toán không được để trống!");
            this.txtMaThanhToan.requestFocus();
            this.txtMaThanhToan.setBackground(Color.yellow);
            return null;
        } else if (maTT.length() > 10) {
            JOptionPane.showMessageDialog(this, "Mã thanh toán không được quá 10 ký tự!");
            this.txtMaThanhToan.requestFocus();
            this.txtMaThanhToan.setBackground(Color.yellow);
            return null;
        } else {
            this.txtMaThanhToan.setBackground(Color.white);
        }
        String maBN = this.txtMaBN.getText();
        if (maBN.length() == 0) {
            JOptionPane.showMessageDialog(this, "Mã bệnh nhân không được để trống!");
            this.txtMaBN.requestFocus();
            this.txtMaBN.setBackground(Color.yellow);
            return null;
        } else if (maBN.length() > 10) {
            JOptionPane.showMessageDialog(this, "Mã bệnh nhân không được quá 10 ký tự!");
            this.txtMaThanhToan.requestFocus();
            this.txtMaThanhToan.setBackground(Color.yellow);
            return null;
        } else {
            this.txtMaBN.setBackground(Color.white);
        }
        if (this.txtVienPhi.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Viện phí không được để trống!");
            this.txtVienPhi.requestFocus();
            this.txtVienPhi.setBackground(Color.yellow);
            return null;
        } else {
            this.txtVienPhi.setBackground(Color.white);
        }
        Long vienPhi;
        try {
            vienPhi = Long.parseLong(this.txtVienPhi.getText());
            if (vienPhi <= 0) {
                JOptionPane.showMessageDialog(this, "Viện phí phải là số nguyên lớn hơn 0!");
                this.txtVienPhi.requestFocus();
                this.txtVienPhi.setBackground(Color.yellow);
                return null;
            } else {
                this.txtVienPhi.setBackground(Color.white);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Viện phí phải là số nguyên lớn hơn 0!");
            this.txtVienPhi.requestFocus();
            this.txtVienPhi.setBackground(Color.yellow);
            return null;
        }
        String trangThai = this.rdoDaThanhToan.isSelected() == true ? "Đã thanh toán" : "Chưa thanh toán";
        ThanhToan tt = new ThanhToan(maTT, maBN, vienPhi, trangThai, "YT");
        return tt;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrTrangThai = new javax.swing.ButtonGroup();
        pnlTong = new javax.swing.JPanel();
        pnlChucNang = new javax.swing.JPanel();
        lblAnhUser = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        btnBenhNhanNoiTru = new javax.swing.JButton();
        btnBenhNhanNgoaiTru = new javax.swing.JButton();
        btnThanhToanVienPhi = new javax.swing.JButton();
        btnQuayLai = new javax.swing.JButton();
        pnlLanViec = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        lblMaThanhToan = new javax.swing.JLabel();
        lblMaBN = new javax.swing.JLabel();
        lblLieuLuong = new javax.swing.JLabel();
        lblVienPhi = new javax.swing.JLabel();
        txtMaThanhToan = new javax.swing.JTextField();
        txtMaBN = new javax.swing.JTextField();
        txtVienPhi = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        btnTimKiem = new javax.swing.JToggleButton();
        btnXoa = new javax.swing.JToggleButton();
        btnSua = new javax.swing.JToggleButton();
        btnThem = new javax.swing.JToggleButton();
        rdoDaThanhToan = new javax.swing.JRadioButton();
        rdoChuaThanhToan = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlTong.setBackground(new java.awt.Color(204, 255, 255));
        pnlTong.setPreferredSize(new java.awt.Dimension(1125, 692));

        pnlChucNang.setBackground(new java.awt.Color(51, 153, 204));
        pnlChucNang.setPreferredSize(new java.awt.Dimension(170, 692));

        lblAnhUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TaiNguyen/icon người dùng.JPG"))); // NOI18N

        lblUserName.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblUserName.setForeground(new java.awt.Color(255, 255, 255));
        lblUserName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserName.setText("Người dùng");

        btnBenhNhanNoiTru.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBenhNhanNoiTru.setText("Bệnh nhân nội trú");
        btnBenhNhanNoiTru.setPreferredSize(new java.awt.Dimension(163, 39));
        btnBenhNhanNoiTru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBenhNhanNoiTruActionPerformed(evt);
            }
        });

        btnBenhNhanNgoaiTru.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBenhNhanNgoaiTru.setText("Bệnh nhân ngoại trú");
        btnBenhNhanNgoaiTru.setPreferredSize(new java.awt.Dimension(163, 39));
        btnBenhNhanNgoaiTru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBenhNhanNgoaiTruActionPerformed(evt);
            }
        });

        btnThanhToanVienPhi.setBackground(new java.awt.Color(204, 255, 255));
        btnThanhToanVienPhi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThanhToanVienPhi.setText("Thanh toán viện phí");
        btnThanhToanVienPhi.setPreferredSize(new java.awt.Dimension(163, 39));

        btnQuayLai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnQuayLai.setText("Về màn hình chính");
        btnQuayLai.setPreferredSize(new java.awt.Dimension(163, 39));
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlChucNangLayout = new javax.swing.GroupLayout(pnlChucNang);
        pnlChucNang.setLayout(pnlChucNangLayout);
        pnlChucNangLayout.setHorizontalGroup(
            pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChucNangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnBenhNhanNoiTru, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(btnBenhNhanNgoaiTru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThanhToanVienPhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQuayLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
            .addGroup(pnlChucNangLayout.createSequentialGroup()
                .addGroup(pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChucNangLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lblUserName))
                    .addGroup(pnlChucNangLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(lblAnhUser)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlChucNangLayout.setVerticalGroup(
            pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChucNangLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(lblAnhUser)
                .addGap(32, 32, 32)
                .addComponent(lblUserName)
                .addGap(42, 42, 42)
                .addComponent(btnBenhNhanNoiTru, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnBenhNhanNgoaiTru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnThanhToanVienPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );

        pnlLanViec.setBackground(new java.awt.Color(204, 255, 255));
        pnlLanViec.setPreferredSize(new java.awt.Dimension(908, 692));

        lblTieuDe.setFont(new java.awt.Font("Tahoma", 1, 32)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(204, 102, 102));
        lblTieuDe.setText("THANH TOÁN VIỆN PHÍ");

        lblMaThanhToan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMaThanhToan.setText("Mã thanh toán:");

        lblMaBN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMaBN.setText("Mã bệnh nhân:");

        lblLieuLuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblLieuLuong.setText("Trạng thái:");

        lblVienPhi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblVienPhi.setText("Viện phí:");

        txtMaThanhToan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaThanhToan.setPreferredSize(new java.awt.Dimension(300, 30));
        txtMaThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMaThanhToanMouseClicked(evt);
            }
        });

        txtMaBN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaBN.setPreferredSize(new java.awt.Dimension(300, 30));

        txtVienPhi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtVienPhi.setPreferredSize(new java.awt.Dimension(300, 30));

        tblDanhSach.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã thanh toán", "Mã bệnh nhân", "Viện phí", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSach);

        btnTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TaiNguyen/search-icon_24px.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TaiNguyen/Button-Close-icon_24px.png"))); // NOI18N
        btnXoa.setText("Xóa bệnh nhân");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TaiNguyen/Update24.png"))); // NOI18N
        btnSua.setText("Sửa bệnh nhân");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TaiNguyen/Add_24PX.png"))); // NOI18N
        btnThem.setText("Thêm bệnh nhân");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        rdoDaThanhToan.setBackground(new java.awt.Color(204, 255, 255));
        bgrTrangThai.add(rdoDaThanhToan);
        rdoDaThanhToan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoDaThanhToan.setSelected(true);
        rdoDaThanhToan.setText("Đã thanh toán");

        rdoChuaThanhToan.setBackground(new java.awt.Color(204, 255, 255));
        bgrTrangThai.add(rdoChuaThanhToan);
        rdoChuaThanhToan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoChuaThanhToan.setText("Chưa thanh toán");

        javax.swing.GroupLayout pnlLanViecLayout = new javax.swing.GroupLayout(pnlLanViec);
        pnlLanViec.setLayout(pnlLanViecLayout);
        pnlLanViecLayout.setHorizontalGroup(
            pnlLanViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLanViecLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTieuDe)
                .addGap(251, 251, 251))
            .addGroup(pnlLanViecLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlLanViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlLanViecLayout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(74, 74, 74)
                        .addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa)
                        .addGap(76, 76, 76)
                        .addComponent(btnTimKiem))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlLanViecLayout.createSequentialGroup()
                        .addGroup(pnlLanViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlLanViecLayout.createSequentialGroup()
                                .addComponent(lblMaThanhToan)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlLanViecLayout.createSequentialGroup()
                                .addComponent(lblMaBN)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(pnlLanViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLieuLuong)
                            .addComponent(lblVienPhi))
                        .addGap(42, 42, 42)
                        .addGroup(pnlLanViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVienPhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlLanViecLayout.createSequentialGroup()
                                .addComponent(rdoDaThanhToan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdoChuaThanhToan)))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        pnlLanViecLayout.setVerticalGroup(
            pnlLanViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLanViecLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTieuDe)
                .addGap(47, 47, 47)
                .addGroup(pnlLanViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaThanhToan)
                    .addComponent(txtMaThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVienPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVienPhi))
                .addGap(44, 44, 44)
                .addGroup(pnlLanViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaBN)
                    .addComponent(txtMaBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLieuLuong)
                    .addComponent(rdoDaThanhToan)
                    .addComponent(rdoChuaThanhToan))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(pnlLanViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem)
                    .addComponent(btnXoa)
                    .addComponent(btnSua)
                    .addComponent(btnThem))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout pnlTongLayout = new javax.swing.GroupLayout(pnlTong);
        pnlTong.setLayout(pnlTongLayout);
        pnlTongLayout.setHorizontalGroup(
            pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongLayout.createSequentialGroup()
                .addComponent(pnlChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(pnlLanViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTongLayout.setVerticalGroup(
            pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongLayout.createSequentialGroup()
                .addGroup(pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlLanViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        int ok = JOptionPane.showConfirmDialog(this, "Bạn muốn quay trở lại màn hình chính !", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            TrangChu tc = new TrangChu(userName, passWord, ND);
            tc.setVisible(true);
            try {
                this.qltt.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi đóng kết nối!");
            }
            this.dispose();
        }
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    private void btnBenhNhanNoiTruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBenhNhanNoiTruActionPerformed
        int ok = JOptionPane.showConfirmDialog(this, "Bạn muốn đến mục bệnh nhân nội trú !", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            BenhNhanNoiTru nt = new BenhNhanNoiTru(userName, passWord, ND);
            nt.setVisible(true);
            try {
                this.qltt.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi đóng kết nối!");
            }
            this.dispose();
        }
    }//GEN-LAST:event_btnBenhNhanNoiTruActionPerformed

    private void btnBenhNhanNgoaiTruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBenhNhanNgoaiTruActionPerformed
        int ok = JOptionPane.showConfirmDialog(this, "Bạn muốn đến mục bệnh nhân ngoại trú !", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            BenhNhanNgoaiiTru nt = new BenhNhanNgoaiiTru(userName, passWord, ND);
            nt.setVisible(true);
            try {
                this.qltt.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi đóng kết nối!");
            }
            this.dispose();
        }
    }//GEN-LAST:event_btnBenhNhanNgoaiTruActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (userName != "YT") {
            JOptionPane.showMessageDialog(this, "Quyền hạn của bạn không để để thực hiện chức năng!");
            return;
        }
        ThanhToan tt = this.docForm();
        if (tt == null) {
            return;
        }
        try {
            ArrayList<ThanhToan> list = this.qltt.xuatDanhSach();
            if (list.size() != 0) {
                for (ThanhToan x : list) {
                    if (x.getMaTT().equalsIgnoreCase(tt.getMaTT())) {
                        JOptionPane.showMessageDialog(this, "Mã thanh toán đã tồn tại. Vui lòng kiểm tra lại!");
                        this.txtMaThanhToan.requestFocus();
                        this.txtMaThanhToan.setBackground(Color.yellow);
                        return;
                    } else {
                        this.txtMaThanhToan.setBackground(Color.white);
                    }
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối dữ liệu!");
            return;
        }
        try {
            this.qltt.them(tt);
            JOptionPane.showMessageDialog(this, "Thêm thành công !");
            this.khoiTaoUI();
            this.hienThi();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Mã bệnh nhân không tồn tại. Vui lòng kiểm tra lại!");
            this.txtMaBN.setBackground(Color.yellow);
            this.txtMaBN.requestFocus();
            return;
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (userName != "YT") {
            JOptionPane.showMessageDialog(this, "Quyền hạn của bạn không để để thực hiện chức năng!");
            return;
        }
        ThanhToan tt = this.docForm();
        if (tt == null) {
            return;
        }
        try {
            this.qltt.sua(tt);
            JOptionPane.showMessageDialog(this, "Sửa thành công !");
            this.khoiTaoUI();
            this.hienThi();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mã bệnh nhân không tồn tại. Vui lòng kiểm tra lại!");
            this.txtMaBN.requestFocus();
            this.txtMaBN.setBackground(Color.yellow);
            return;
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (userName != "YT") {
            JOptionPane.showMessageDialog(this, "Quyền hạn của bạn không để để thực hiện chức năng!");
            return;
        }
        String maTT = JOptionPane.showInputDialog(this, "Nhập mã thanh toán cần xóa!");
        if (maTT == null) {
            return;
        }
        boolean check = false;
        try {
            ArrayList<ThanhToan> list = this.qltt.xuatDanhSach();
            if (list.size() != 0) {
                for (ThanhToan x : list) {
                    if (x.getMaTT().equalsIgnoreCase(maTT)) {
                        check=true;
                        break;
                    }
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối dữ liệu!");
            return;
        }
        if(check==false){
            JOptionPane.showMessageDialog(this, "Không tìm thấy mã thanh toán: " + maTT);
            return;
        }
        try {
            int ok = JOptionPane.showConfirmDialog(this, "Xác nhận xóa mã thanh toán: " + maTT, "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (ok == JOptionPane.YES_OPTION) {
                this.qltt.xoa(maTT);
                JOptionPane.showMessageDialog(this, "Xóa thành công mã thanh toán: " + maTT);
                this.hienThi();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy mã thanh toán: " + maTT);
            return;
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        if (userName != "YT") {
            JOptionPane.showMessageDialog(this, "Quyền hạn của bạn không để để thực hiện chức năng!");
            return;
        }
        String maTT = JOptionPane.showInputDialog(this, "Nhập mã thanh toán cần tìm!");
        if (maTT == null) {
            return;
        }
        try {
            ThanhToan tt = this.qltt.timKiem(maTT);
            if (tt == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã thanh toán: " + maTT);
            } else {
                JOptionPane.showMessageDialog(this, "Tìm thấy mã thanh toán: " + maTT);
                this.txtMaThanhToan.setText(tt.getMaTT());
                this.txtMaThanhToan.setEnabled(false);
                this.txtMaBN.setText(tt.getMaBN());
                this.txtVienPhi.setText(tt.getVienPhi().toString());
                if (tt.getTrangThai().equalsIgnoreCase("Đã thanh toán")) {
                    this.rdoDaThanhToan.setSelected(true);
                } else {
                    this.rdoChuaThanhToan.setSelected(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối dữ liệu!");
            return;
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked
        this.hienThiCT();
    }//GEN-LAST:event_tblDanhSachMouseClicked

    private void txtMaThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaThanhToanMouseClicked
        if (this.txtMaThanhToan.isEnabled() == false) {
            int ok = JOptionPane.showConfirmDialog(this, "Chỉ thay đổi mã khi thêm mới thanh toán.\nBạn muốn thêm mới?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (ok == JOptionPane.YES_OPTION) {
                this.txtMaThanhToan.setEnabled(true);
            }
        }
    }//GEN-LAST:event_txtMaThanhToanMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrTrangThai;
    private javax.swing.JButton btnBenhNhanNgoaiTru;
    private javax.swing.JButton btnBenhNhanNoiTru;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JToggleButton btnSua;
    private javax.swing.JButton btnThanhToanVienPhi;
    private javax.swing.JToggleButton btnThem;
    private javax.swing.JToggleButton btnTimKiem;
    private javax.swing.JToggleButton btnXoa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnhUser;
    private javax.swing.JLabel lblLieuLuong;
    private javax.swing.JLabel lblMaBN;
    private javax.swing.JLabel lblMaThanhToan;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblVienPhi;
    private javax.swing.JPanel pnlChucNang;
    private javax.swing.JPanel pnlLanViec;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JRadioButton rdoChuaThanhToan;
    private javax.swing.JRadioButton rdoDaThanhToan;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txtMaBN;
    private javax.swing.JTextField txtMaThanhToan;
    private javax.swing.JTextField txtVienPhi;
    // End of variables declaration//GEN-END:variables
}
