/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import java.util.ArrayList;
import DAO.QuanLyBenhNhan;
import DoiTuong.BenhNhan;
import java.awt.Color;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class BenhNhanNgoaiiTru extends javax.swing.JFrame {

    private final String userName;
    private final String passWord;
    private final String ND;
    private QuanLyBenhNhan qlbn;

    /**
     * Creates new form BenhNhanNgoaiiTru
     */
    public BenhNhanNgoaiiTru(String userName, String passWord, String ND) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.userName = userName;
        this.passWord = passWord;
        this.ketNoi();
        this.khoiTaoUI();
        this.hienThi();
        this.ND = ND;
        this.lblNguoiDung.setText(ND);
    }

    public void ketNoi() {
        try {
            this.qlbn = new QuanLyBenhNhan(userName, passWord);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Quyền hạn của bạn không đủ để sử dụng chức năng này!");
        }
    }

    public void close() {
        try {
            this.qlbn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi đóng kết nối!");
        }
    }

    public void khoiTaoUI() {
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        this.txtMaBN.setText("");
        this.txtMaBN.setEnabled(true);
        this.txtHoTen.setText("");
        this.cbbNgaySinh.removeAllItems();
        this.cbbNgayKham.removeAllItems();
        for (Integer i = 1; i < 30; i++) {
            if (i < 10) {
                this.cbbNgaySinh.addItem("0" + i.toString());
                this.cbbNgayKham.addItem("0" + i.toString());
            } else {
                this.cbbNgaySinh.addItem(i.toString());
                this.cbbNgayKham.addItem(i.toString());
            }
        }
        this.rdoNam.setSelected(true);
        this.txtDiaChi.setText("");
        this.txtChanDoan.setText("");
        this.cbbNamKham.removeAllItems();
        this.cbbNamSinh.removeAllItems();
        for (Integer i = y; i >= 1900; i--) {
            this.cbbNamSinh.addItem(i.toString());
            this.cbbNamKham.addItem(i.toString());
        }
    }

    public BenhNhan docForm() {
        String maBN = this.txtMaBN.getText();
        if (maBN.length() == 0) {
            JOptionPane.showMessageDialog(this, "Mã bệnh nhân không được để trống!");
            this.txtMaBN.setBackground(Color.yellow);
            this.txtMaBN.requestFocus();
            return null;
        } else if (maBN.length() > 10) {
            JOptionPane.showMessageDialog(this, "Mã bệnh nhân không được quá 10 kí tự!");
            this.txtMaBN.setBackground(Color.yellow);
            this.txtMaBN.requestFocus();
            return null;
        } else {
            this.txtMaBN.setBackground(Color.white);
        }

        String hoTen = this.txtHoTen.getText();
        if (hoTen.length() == 0) {
            JOptionPane.showMessageDialog(this, "Họ tên không được để trống!");
            this.txtHoTen.setBackground(Color.yellow);
            this.txtHoTen.requestFocus();
            return null;
        } else {
            this.txtHoTen.setBackground(Color.white);
        }
        String ht = hoTen.trim().toLowerCase();
        String[] ht1 = ht.split("");
        for (String x : ht1) {
            if(x.equals("\\s+")){
                
            }else if (!x.matches("\\D")||x.equals(".")||x.equals(",")||x.equals("@")||x.equals("!")||x.equals("#")||x.equals("$")||x.equals("%")||x.equals("^")||x.equals("&")||x.equals("*")||x.equals(";")||x.equals(":")) {
                JOptionPane.showMessageDialog(this, "Họ tên không được chữa ký tự số hoặc kí tự đặc biệt!");
                this.txtHoTen.setBackground(Color.yellow);
                this.txtHoTen.requestFocus();
                return null;
            } else {
                this.txtHoTen.setBackground(Color.white);
            }
        }
        String ngaySinh = this.cbbNgaySinh.getSelectedItem() + "-" + this.cbbThangSinh.getSelectedItem() + "-" + this.cbbNamSinh.getSelectedItem();

        String gioiTinh = this.rdoNam.isSelected() == true ? "Nam" : "Nữ";

        String diaChi = this.txtDiaChi.getText();
        if (diaChi.length() == 0) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!");
            this.txtDiaChi.setBackground(Color.yellow);
            this.txtDiaChi.requestFocus();
            return null;
        } else {
            this.txtDiaChi.setBackground(Color.white);
        }

        String chuanDoan = this.txtChanDoan.getText();
        if (chuanDoan.length() == 0) {
            JOptionPane.showMessageDialog(this, "Chẩn đoán không được để trống!");
            this.txtChanDoan.setBackground(Color.yellow);
            this.txtChanDoan.requestFocus();
            return null;
        } else {
            this.txtChanDoan.setBackground(Color.white);
        }

        String ngayKham = this.cbbNgayKham.getSelectedItem() + "-" + this.cbbThangKham.getSelectedItem() + "-" + this.cbbNamKham.getSelectedItem();

        BenhNhan bn = new BenhNhan(maBN, hoTen, ngaySinh, gioiTinh, diaChi, chuanDoan, false, ngayKham, null, null, null, "BS");

        return bn;
    }

    public void hienThi() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblDanhSach.getModel();
        dtm.setRowCount(0);
        try {
            ArrayList<BenhNhan> list = this.qlbn.xuatDanhSach();
            for (BenhNhan x : list) {
                if (x.getLoaiBN() == false) {
                    Object[] rowData = new Object[]{
                        x.getMaBN(),
                        x.getHoTen(),
                        x.getNgaySinh(),
                        x.getGioiTinh(),
                        x.getDiaChi(),
                        x.getChanDoan(),
                        x.getNgayKham()
                    };
                    dtm.addRow(rowData);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Quyền hạn của bạn không đủ để sử dụng chức năng này!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrGioiTinh = new javax.swing.ButtonGroup();
        pnlTong = new javax.swing.JPanel();
        pnlChucNang = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        lblNguoiDung = new javax.swing.JLabel();
        btnBNNoiTru = new javax.swing.JButton();
        btnBNNgoaiTru = new javax.swing.JButton();
        btnThanhToanVienPhi = new javax.swing.JButton();
        btnTroLai = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        lblMaBN = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtMaBN = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        lblDiaChi = new javax.swing.JLabel();
        lblChanDoan = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtChanDoan = new javax.swing.JTextField();
        lblNgayKham = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        btnTimKiem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        cbbNgaySinh = new javax.swing.JComboBox<>();
        cbbThangSinh = new javax.swing.JComboBox<>();
        cbbNamSinh = new javax.swing.JComboBox<>();
        lblThangSinh = new javax.swing.JLabel();
        lblNamSinh = new javax.swing.JLabel();
        cbbNgayKham = new javax.swing.JComboBox<>();
        lblThangKham = new javax.swing.JLabel();
        cbbThangKham = new javax.swing.JComboBox<>();
        lblNamKham = new javax.swing.JLabel();
        cbbNamKham = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bệnh nhân ngoại trú");

        pnlTong.setBackground(new java.awt.Color(204, 255, 255));
        pnlTong.setMinimumSize(new java.awt.Dimension(1125, 692));
        pnlTong.setPreferredSize(new java.awt.Dimension(1125, 692));

        pnlChucNang.setBackground(new java.awt.Color(51, 153, 204));
        pnlChucNang.setMinimumSize(new java.awt.Dimension(170, 692));
        pnlChucNang.setPreferredSize(new java.awt.Dimension(170, 692));

        lblAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TaiNguyen/icon người dùng.JPG"))); // NOI18N

        lblNguoiDung.setBackground(new java.awt.Color(255, 255, 255));
        lblNguoiDung.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblNguoiDung.setForeground(new java.awt.Color(255, 255, 255));
        lblNguoiDung.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNguoiDung.setText("Người dùng");

        btnBNNoiTru.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBNNoiTru.setText("Bệnh nhân nội trú");
        btnBNNoiTru.setPreferredSize(new java.awt.Dimension(150, 30));
        btnBNNoiTru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBNNoiTruActionPerformed(evt);
            }
        });

        btnBNNgoaiTru.setBackground(new java.awt.Color(204, 255, 255));
        btnBNNgoaiTru.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBNNgoaiTru.setText("Bệnh nhân ngoại trú");
        btnBNNgoaiTru.setPreferredSize(new java.awt.Dimension(150, 30));

        btnThanhToanVienPhi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThanhToanVienPhi.setText("Thanh toán viện phí");
        btnThanhToanVienPhi.setPreferredSize(new java.awt.Dimension(150, 30));
        btnThanhToanVienPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanVienPhiActionPerformed(evt);
            }
        });

        btnTroLai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTroLai.setText("Về màn hình chính");
        btnTroLai.setPreferredSize(new java.awt.Dimension(150, 30));
        btnTroLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTroLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlChucNangLayout = new javax.swing.GroupLayout(pnlChucNang);
        pnlChucNang.setLayout(pnlChucNangLayout);
        pnlChucNangLayout.setHorizontalGroup(
            pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChucNangLayout.createSequentialGroup()
                .addGroup(pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChucNangLayout.createSequentialGroup()
                        .addGroup(pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlChucNangLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(lblAnh))
                            .addGroup(pnlChucNangLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(lblNguoiDung)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlChucNangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBNNoiTru, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(btnBNNgoaiTru, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(btnThanhToanVienPhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTroLai, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlChucNangLayout.setVerticalGroup(
            pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChucNangLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(lblAnh)
                .addGap(31, 31, 31)
                .addComponent(lblNguoiDung)
                .addGap(48, 48, 48)
                .addComponent(btnBNNoiTru, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnBNNgoaiTru, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnThanhToanVienPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnTroLai, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(908, 692));

        lblTieuDe.setFont(new java.awt.Font("Tahoma", 1, 32)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(204, 102, 102));
        lblTieuDe.setText("BỆNH NHÂN NGOẠI TRÚ");

        lblMaBN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMaBN.setText("Mã bệnh nhân:");

        lblHoTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblHoTen.setText("Họ và tên:");

        lblNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNgaySinh.setText("Ngày sinh:");

        lblGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGioiTinh.setText("Giới tính:");

        bgrGioiTinh.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        bgrGioiTinh.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoNu.setText("Nữ");

        txtMaBN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaBN.setMinimumSize(new java.awt.Dimension(300, 23));
        txtMaBN.setPreferredSize(new java.awt.Dimension(300, 30));
        txtMaBN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMaBNMouseClicked(evt);
            }
        });

        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtHoTen.setPreferredSize(new java.awt.Dimension(300, 30));
        txtHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenActionPerformed(evt);
            }
        });

        lblDiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDiaChi.setText("Địa chỉ:");

        lblChanDoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblChanDoan.setText("Chuẩn đoán:");

        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDiaChi.setPreferredSize(new java.awt.Dimension(300, 30));

        txtChanDoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtChanDoan.setPreferredSize(new java.awt.Dimension(300, 30));

        lblNgayKham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNgayKham.setText("Ngày khám:");

        tblDanhSach.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã bệnh nhân", "Họ và tên", "Ngày sinh", "Giới tính", "Địa chỉ", "Chẩn đoán", "Ngày khám"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        cbbNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbNgaySinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));

        cbbThangSinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbThangSinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbbThangSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThangSinhActionPerformed(evt);
            }
        });

        cbbNamSinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbNamSinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021" }));

        lblThangSinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThangSinh.setText("Tháng:");

        lblNamSinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNamSinh.setText("Năm:");

        cbbNgayKham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbNgayKham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));

        lblThangKham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThangKham.setText("Tháng:");

        cbbThangKham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbThangKham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        lblNamKham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNamKham.setText("Năm:");

        cbbNamKham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbNamKham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(lblTieuDe))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGioiTinh)
                                    .addComponent(lblMaBN)
                                    .addComponent(lblHoTen)
                                    .addComponent(lblNgaySinh))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaBN, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rdoNam)
                                            .addComponent(cbbNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblThangSinh)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(cbbThangSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblNamSinh)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbbNamSinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(rdoNu)
                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblDiaChi)
                                        .addGap(39, 39, 39)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(lblNgayKham)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(cbbNgayKham, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblThangKham)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbbThangKham, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblNamKham)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbbNamKham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(lblChanDoan)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtChanDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnThem)
                                        .addGap(72, 72, 72)
                                        .addComponent(btnSua)
                                        .addGap(73, 73, 73)
                                        .addComponent(btnXoa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnTimKiem))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTieuDe)
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaBN)
                    .addComponent(txtMaBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiaChi)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHoTen)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChanDoan)
                    .addComponent(txtChanDoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgaySinh)
                    .addComponent(lblNgayKham)
                    .addComponent(cbbNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbThangSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThangSinh)
                    .addComponent(lblNamSinh)
                    .addComponent(cbbNgayKham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThangKham)
                    .addComponent(cbbThangKham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNamKham)
                    .addComponent(cbbNamKham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGioiTinh)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem)
                    .addComponent(btnXoa)
                    .addComponent(btnSua)
                    .addComponent(btnThem))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout pnlTongLayout = new javax.swing.GroupLayout(pnlTong);
        pnlTong.setLayout(pnlTongLayout);
        pnlTongLayout.setHorizontalGroup(
            pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongLayout.createSequentialGroup()
                .addComponent(pnlChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTongLayout.setVerticalGroup(
            pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongLayout.createSequentialGroup()
                .addGroup(pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed

    private void btnBNNoiTruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBNNoiTruActionPerformed
        int ok = JOptionPane.showConfirmDialog(this, "Bạn muốn đến mục bệnh nhân nội trú !", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            BenhNhanNoiTru nt = new BenhNhanNoiTru(userName, passWord, ND);
            nt.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnBNNoiTruActionPerformed

    private void btnThanhToanVienPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanVienPhiActionPerformed
        int ok = JOptionPane.showConfirmDialog(this, "Bạn muốn đến mục thanh toán viện phí !", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            ThanhToanVienPhi tt = new ThanhToanVienPhi(userName, passWord, ND);
            tt.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnThanhToanVienPhiActionPerformed

    private void btnTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroLaiActionPerformed
        int ok = JOptionPane.showConfirmDialog(this, "Bạn muốn quay trở lại màn hình chính !", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            TrangChu tc = new TrangChu(userName, passWord, ND);
            tc.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnTroLaiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (userName != "BS") {
            JOptionPane.showMessageDialog(this, "Quyền hạn của bạn không để để thực hiện chức năng!");
            return;
        }
        BenhNhan bn = this.docForm();
        if (bn == null) {
            return;
        }
        try {
            ArrayList<BenhNhan> list = this.qlbn.xuatDanhSach();
            if (list.size() != 0) {
                for (BenhNhan x : list) {
                    if (x.getMaBN().equalsIgnoreCase(bn.getMaBN())) {
                        JOptionPane.showMessageDialog(this, "Mã bệnh nhân đã tồn tại. Vui lòng kiểm tra lại!");
                        this.txtMaBN.setBackground(Color.yellow);
                        this.txtMaBN.requestFocus();
                        return;
                    }
                }
                this.txtMaBN.setBackground(Color.white);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối!");
        }
        try {
            this.qlbn.them(bn);
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            this.hienThi();
            this.khoiTaoUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm thất bại!");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (userName != "BS") {
            JOptionPane.showMessageDialog(this, "Quyền hạn của bạn không để để thực hiện chức năng!");
            return;
        }
        BenhNhan bn = this.docForm();
        if (bn == null) {
            return;
        }
        try {
            int ok = JOptionPane.showConfirmDialog(this, "Bạn xác nhận muốn sửa thông tin bệnh nhân có mã: " + bn.getMaBN(), "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
            if (ok == JOptionPane.YES_OPTION) {
                this.qlbn.sua(bn);
                JOptionPane.showMessageDialog(this, "Sửa thành công!");
                this.hienThi();
                this.khoiTaoUI();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sửa thất bại!");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (userName != "BS") {
            JOptionPane.showMessageDialog(this, "Quyền hạn của bạn không để để thực hiện chức năng!");
            return;
        }
        String maBN = JOptionPane.showInputDialog(this, "Nhập mã bệnh nhân muốn xóa:");
        if (maBN == null) {
            return;
        }
        try {
            ArrayList<BenhNhan> list = this.qlbn.xuatDanhSach();
            boolean ok = false;
            for (BenhNhan x : list) {
                if (x.getMaBN().equalsIgnoreCase(maBN) && x.getLoaiBN() == false) {
                    ok = true;
                    break;
                }
            }
            if (ok == false) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy bệnh nhân có mã: " + maBN);
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối!");
        }
        try {
            int ok = JOptionPane.showConfirmDialog(this, "Bạn xác nhận muốn sửa thông tin bệnh nhân có mã: " + maBN, "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
            if (ok == JOptionPane.YES_OPTION) {
                this.qlbn.xoa(maBN);
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                this.hienThi();
                this.khoiTaoUI();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        if (userName != "BS") {
            JOptionPane.showMessageDialog(this, "Quyền hạn của bạn không để để thực hiện chức năng!");
            return;
        }
        String maBN = JOptionPane.showInputDialog(this, "Nhập mã bệnh nhân cần tìm:");
        if (maBN == null) {
            return;
        }
        try {
            BenhNhan bn = this.qlbn.timKiem(maBN, false);
            if (bn == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy bệnh nhân có mã: " + maBN);
                return;
            }
            JOptionPane.showMessageDialog(this, "Tìm thấy bệnh nhân có mã: " + maBN);
            this.txtMaBN.setText(bn.getMaBN());
            this.txtMaBN.setEnabled(false);
            this.txtHoTen.setText(bn.getHoTen());
            this.txtDiaChi.setText(bn.getDiaChi());
            this.txtChanDoan.setText(bn.getChanDoan());
            String[] ngaySinh = bn.getNgaySinh().split("-");
            this.cbbNgaySinh.setSelectedItem(ngaySinh[0]);
            this.cbbThangSinh.setSelectedItem(ngaySinh[1]);
            this.cbbNamSinh.setSelectedItem(ngaySinh[2]);
            if (bn.getGioiTinh().equalsIgnoreCase("Nam")) {
                this.rdoNam.setSelected(true);
            } else {
                this.rdoNu.setSelected(true);
            }
            String[] ngayKham = bn.getNgayKham().split("-");
            this.cbbNgayKham.setSelectedItem(ngayKham[0]);
            this.cbbThangKham.setSelectedItem(ngayKham[1]);
            this.cbbNamKham.setSelectedItem(ngayKham[2]);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối!");
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void cbbThangSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThangSinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbThangSinhActionPerformed

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked
        int viTri = this.tblDanhSach.getSelectedRow();
        if (viTri == -1) {
            return;
        }
        String mbn = (String) this.tblDanhSach.getValueAt(viTri, 0);
        try {
            BenhNhan bn;
            for (BenhNhan x : this.qlbn.xuatDanhSach()) {
                if (x.getMaBN().equalsIgnoreCase(mbn)) {
                    bn = x;
                    this.txtMaBN.setText(bn.getMaBN());
                    this.txtMaBN.setEnabled(false);
                    this.txtHoTen.setText(bn.getHoTen());
                    this.txtDiaChi.setText(bn.getDiaChi());
                    this.txtChanDoan.setText(bn.getChanDoan());
                    String[] ngaySinh = bn.getNgaySinh().split("-");
                    this.cbbNgaySinh.setSelectedItem(ngaySinh[0]);
                    this.cbbThangSinh.setSelectedItem(ngaySinh[1]);
                    this.cbbNamSinh.setSelectedItem(ngaySinh[2]);
                    if (bn.getGioiTinh().equalsIgnoreCase("Nam")) {
                        this.rdoNam.setSelected(true);
                    } else {
                        this.rdoNu.setSelected(true);
                    }
                    String[] ngayKham = bn.getNgayKham().split("-");
                    this.cbbNgayKham.setSelectedItem(ngayKham[0]);
                    this.cbbThangKham.setSelectedItem(ngayKham[1]);
                    this.cbbNamKham.setSelectedItem(ngayKham[2]);
                    break;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối!");
        }
    }//GEN-LAST:event_tblDanhSachMouseClicked

    private void txtMaBNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaBNMouseClicked
        if(this.txtMaBN.isEnabled()==false){
            int ok = JOptionPane.showConfirmDialog(this, "Chỉ thay đổi mã khi thêm mới bệnh nhân.\nBạn muốn thêm mới?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (ok == JOptionPane.YES_OPTION) {
                this.txtMaBN.setEnabled(true);
            }
        }
    }//GEN-LAST:event_txtMaBNMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrGioiTinh;
    private javax.swing.JButton btnBNNgoaiTru;
    private javax.swing.JButton btnBNNoiTru;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThanhToanVienPhi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTroLai;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbNamKham;
    private javax.swing.JComboBox<String> cbbNamSinh;
    private javax.swing.JComboBox<String> cbbNgayKham;
    private javax.swing.JComboBox<String> cbbNgaySinh;
    private javax.swing.JComboBox<String> cbbThangKham;
    private javax.swing.JComboBox<String> cbbThangSinh;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblChanDoan;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMaBN;
    private javax.swing.JLabel lblNamKham;
    private javax.swing.JLabel lblNamSinh;
    private javax.swing.JLabel lblNgayKham;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblNguoiDung;
    private javax.swing.JLabel lblThangKham;
    private javax.swing.JLabel lblThangSinh;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JPanel pnlChucNang;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txtChanDoan;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaBN;
    // End of variables declaration//GEN-END:variables
}
