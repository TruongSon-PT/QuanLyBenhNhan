/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import DAO.QuanLyBenhNhan;
import DoiTuong.BenhNhan;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class BenhNhanNoiTru extends javax.swing.JFrame {
    private final String ND;
    private final String userName;
    private final String passWord;
    private QuanLyBenhNhan qlbn;

    /**
     * Creates new form BenhNhanNoiTru
     */
    public BenhNhanNoiTru(String userName, String passWord, String ND) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.tblDanhSach.setBackground(Color.white);
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
        this.rdoNam.setSelected(true);
        this.txtDiaChi.setText("");
        this.txtChanDoan.setText("");
        this.rdoDangDieuTri.setSelected(true);
        this.cbbNgaySinh.removeAllItems();
        this.cbbNgayVaoVien.removeAllItems();
        this.cbbNgayRaVien.removeAllItems();
        for (Integer i = 1; i < 30; i++) {
            if (i < 10) {
                this.cbbNgaySinh.addItem("0" + i.toString());
                this.cbbNgayVaoVien.addItem("0" + i.toString());
                this.cbbNgayRaVien.addItem("0" + i.toString());
            } else {
                this.cbbNgaySinh.addItem(i.toString());
                this.cbbNgayVaoVien.addItem(i.toString());
                this.cbbNgayRaVien.addItem(i.toString());
            }
        }
        this.cbbNamSinh.removeAllItems();
        this.cbbNamVaoVien.removeAllItems();
        this.cbbNamRaVien.removeAllItems();
        for (Integer i = y; i >= 1900; i--) {
            this.cbbNamSinh.addItem(i.toString());
            this.cbbNamVaoVien.addItem(i.toString());
            this.cbbNamRaVien.addItem(i.toString());
        }
        this.cbbNgayRaVien.setEnabled(false);
        this.cbbThangRaVien.setEnabled(false);
        this.cbbNamRaVien.setEnabled(false);
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
        String ngaySinh = this.cbbNgaySinh.getSelectedItem().toString() + "-" + this.cbbThangSinh.getSelectedItem().toString() + "-" + this.cbbNamSinh.getSelectedItem().toString();
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
        String ngayNhapVien = this.cbbNgayVaoVien.getSelectedItem().toString() + "-" + this.cbbThangVaoVien.getSelectedItem().toString() + "-" + this.cbbNamVaoVien.getSelectedItem().toString();
        String tinhTrang;
        String ngayRaVien;
        if (this.rdoDangDieuTri.isSelected() == true) {
            tinhTrang = "Đang điền trị";
            ngayRaVien = null;
            BenhNhan bn = new BenhNhan(maBN, hoTen, ngaySinh, gioiTinh, diaChi, chuanDoan, true, null, ngayNhapVien, tinhTrang, ngayRaVien, "BS");
            return bn;
        }
        if (this.rdoRaVien.isSelected() == true) {
            tinhTrang = "Ra viện";
            ngayRaVien = this.cbbNgayRaVien.getSelectedItem().toString() + "-" + this.cbbThangRaVien.getSelectedItem().toString() + "-" + this.cbbNamRaVien.getSelectedItem();
            BenhNhan bn = new BenhNhan(maBN, hoTen, ngaySinh, gioiTinh, diaChi, chuanDoan, true, null, ngayNhapVien, tinhTrang, ngayRaVien, "BS");
            return bn;
        }
        return null;
    }

    public void hienThi() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblDanhSach.getModel();
        dtm.setRowCount(0);
        try {
            ArrayList<BenhNhan> list = this.qlbn.xuatDanhSach();
            for (BenhNhan x : list) {
                if (x.getLoaiBN() == true) {
                    Object[] rowData = new Object[]{
                        x.getMaBN(),
                        x.getHoTen(),
                        x.getNgaySinh(),
                        x.getGioiTinh(),
                        x.getDiaChi(),
                        x.getChanDoan(),
                        x.getNgayRaVien() == null ? "Đang điều trị" : "Ra viện",
                        x.getNgayNhapVien(),
                        x.getNgayRaVien()
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
        bgrTinhTrang = new javax.swing.ButtonGroup();
        pnlTong = new javax.swing.JPanel();
        pnlChucNang = new javax.swing.JPanel();
        lblNguoiDung = new javax.swing.JLabel();
        lblAnhUser = new javax.swing.JLabel();
        btnBNNgoaiTru = new javax.swing.JButton();
        btnThanhToanVienPhi = new javax.swing.JButton();
        btnTroLai = new javax.swing.JButton();
        btnBNNoiTru = new javax.swing.JButton();
        pnlLamViec = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        lblMaBN = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblTinhTrang = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        txtMaBN = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtDiaChi = new javax.swing.JTextField();
        rdoDangDieuTri = new javax.swing.JRadioButton();
        rdoRaVien = new javax.swing.JRadioButton();
        lblChanDoan = new javax.swing.JLabel();
        lblNgayNhapVien = new javax.swing.JLabel();
        lblNgayRaVien = new javax.swing.JLabel();
        txtChanDoan = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        cbbNgaySinh = new javax.swing.JComboBox<>();
        lblThangSinh = new javax.swing.JLabel();
        cbbThangSinh = new javax.swing.JComboBox<>();
        lblNamSinh = new javax.swing.JLabel();
        cbbNamSinh = new javax.swing.JComboBox<>();
        cbbNgayVaoVien = new javax.swing.JComboBox<>();
        lblThangSinh1 = new javax.swing.JLabel();
        cbbThangVaoVien = new javax.swing.JComboBox<>();
        lblNamSinh1 = new javax.swing.JLabel();
        cbbNamVaoVien = new javax.swing.JComboBox<>();
        cbbNgayRaVien = new javax.swing.JComboBox<>();
        lblThangSinh2 = new javax.swing.JLabel();
        cbbThangRaVien = new javax.swing.JComboBox<>();
        lblNamSinh2 = new javax.swing.JLabel();
        cbbNamRaVien = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BỆNH NHÂN NỘI TRÚ");

        pnlTong.setBackground(new java.awt.Color(204, 255, 255));
        pnlTong.setPreferredSize(new java.awt.Dimension(1125, 692));

        pnlChucNang.setBackground(new java.awt.Color(51, 153, 204));
        pnlChucNang.setPreferredSize(new java.awt.Dimension(170, 692));

        lblNguoiDung.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblNguoiDung.setForeground(new java.awt.Color(255, 255, 255));
        lblNguoiDung.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNguoiDung.setText("Người dùng");

        lblAnhUser.setBackground(new java.awt.Color(255, 255, 255));
        lblAnhUser.setForeground(new java.awt.Color(255, 255, 255));
        lblAnhUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TaiNguyen/icon người dùng.JPG"))); // NOI18N
        lblAnhUser.setPreferredSize(new java.awt.Dimension(106, 97));

        btnBNNgoaiTru.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBNNgoaiTru.setText("Bệnh nhân ngoại trú");
        btnBNNgoaiTru.setPreferredSize(new java.awt.Dimension(150, 30));
        btnBNNgoaiTru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBNNgoaiTruActionPerformed(evt);
            }
        });

        btnThanhToanVienPhi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThanhToanVienPhi.setText("Thanh toán viện phí");
        btnThanhToanVienPhi.setPreferredSize(new java.awt.Dimension(150, 30));
        btnThanhToanVienPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanVienPhiActionPerformed(evt);
            }
        });

        btnTroLai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTroLai.setText("Trở lại màn hình chính");
        btnTroLai.setPreferredSize(new java.awt.Dimension(150, 30));
        btnTroLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTroLaiActionPerformed(evt);
            }
        });

        btnBNNoiTru.setBackground(new java.awt.Color(204, 255, 255));
        btnBNNoiTru.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBNNoiTru.setText("Bệnh nhân nội trú");
        btnBNNoiTru.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout pnlChucNangLayout = new javax.swing.GroupLayout(pnlChucNang);
        pnlChucNang.setLayout(pnlChucNangLayout);
        pnlChucNangLayout.setHorizontalGroup(
            pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChucNangLayout.createSequentialGroup()
                .addGroup(pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlChucNangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnTroLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlChucNangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThanhToanVienPhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlChucNangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBNNgoaiTru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlChucNangLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lblNguoiDung)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlChucNangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBNNoiTru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
            .addGroup(pnlChucNangLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(lblAnhUser, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlChucNangLayout.setVerticalGroup(
            pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChucNangLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(lblAnhUser, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(lblNguoiDung)
                .addGap(48, 48, 48)
                .addComponent(btnBNNoiTru, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnBNNgoaiTru, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnThanhToanVienPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnTroLai, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(495, Short.MAX_VALUE))
        );

        pnlLamViec.setBackground(new java.awt.Color(204, 255, 255));
        pnlLamViec.setPreferredSize(new java.awt.Dimension(908, 692));

        lblTieuDe.setFont(new java.awt.Font("Tahoma", 1, 32)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(204, 102, 102));
        lblTieuDe.setText("BỆNH NHÂN NỘI TRÚ");

        lblMaBN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMaBN.setText("Mã bệnh nhân:");

        lblHoTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblHoTen.setText("Họ và tên:");

        lblNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNgaySinh.setText("Ngày sinh:");

        lblGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGioiTinh.setText("Giới tính:");

        lblDiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDiaChi.setText("Địa chỉ:");

        lblTinhTrang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTinhTrang.setText("Tình trạng:");

        tblDanhSach.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã bênh nhân", "Họ và tên", "Ngày sinh", "Giới tính", "Địa chỉ", "Chẩn đoán", "Tình trạng", "Ngày vào viện", "Ngày ra viện"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        txtMaBN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        bgrGioiTinh.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        bgrGioiTinh.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoNu.setText("Nữ");

        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDiaChi.setPreferredSize(new java.awt.Dimension(300, 30));

        bgrTinhTrang.add(rdoDangDieuTri);
        rdoDangDieuTri.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoDangDieuTri.setSelected(true);
        rdoDangDieuTri.setText("Đang điều trị");
        rdoDangDieuTri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoDangDieuTriMouseClicked(evt);
            }
        });
        rdoDangDieuTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDangDieuTriActionPerformed(evt);
            }
        });

        bgrTinhTrang.add(rdoRaVien);
        rdoRaVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoRaVien.setText("Ra viện");
        rdoRaVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoRaVienMouseClicked(evt);
            }
        });

        lblChanDoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblChanDoan.setText("Chuẩn đoán:");

        lblNgayNhapVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNgayNhapVien.setText("Ngày nhập viện:");

        lblNgayRaVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNgayRaVien.setText("Ngày ra viện:");

        txtChanDoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtChanDoan.setPreferredSize(new java.awt.Dimension(300, 30));

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

        lblThangSinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThangSinh.setText("Tháng:");

        cbbThangSinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbThangSinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbbThangSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThangSinhActionPerformed(evt);
            }
        });

        lblNamSinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNamSinh.setText("Năm:");

        cbbNamSinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbNamSinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021" }));

        cbbNgayVaoVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbNgayVaoVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));

        lblThangSinh1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThangSinh1.setText("Tháng:");

        cbbThangVaoVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbThangVaoVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbbThangVaoVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThangVaoVienActionPerformed(evt);
            }
        });

        lblNamSinh1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNamSinh1.setText("Năm:");

        cbbNamVaoVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbNamVaoVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021" }));

        cbbNgayRaVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbNgayRaVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));

        lblThangSinh2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThangSinh2.setText("Tháng:");

        cbbThangRaVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbThangRaVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbbThangRaVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThangRaVienActionPerformed(evt);
            }
        });

        lblNamSinh2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNamSinh2.setText("Năm:");

        cbbNamRaVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbNamRaVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021" }));

        javax.swing.GroupLayout pnlLamViecLayout = new javax.swing.GroupLayout(pnlLamViec);
        pnlLamViec.setLayout(pnlLamViecLayout);
        pnlLamViecLayout.setHorizontalGroup(
            pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLamViecLayout.createSequentialGroup()
                .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLamViecLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlLamViecLayout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(85, 85, 85)
                                .addComponent(btnSua)
                                .addGap(83, 83, 83)
                                .addComponent(btnXoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTimKiem))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlLamViecLayout.createSequentialGroup()
                                .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaBN)
                                    .addComponent(lblHoTen)
                                    .addComponent(lblTinhTrang)
                                    .addComponent(lblNgaySinh)
                                    .addComponent(lblGioiTinh))
                                .addGap(8, 8, 8)
                                .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlLamViecLayout.createSequentialGroup()
                                        .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pnlLamViecLayout.createSequentialGroup()
                                                .addComponent(cbbNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblThangSinh)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbbThangSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblNamSinh)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbbNamSinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(pnlLamViecLayout.createSequentialGroup()
                                                .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlLamViecLayout.createSequentialGroup()
                                                        .addComponent(rdoNam)
                                                        .addGap(35, 35, 35)
                                                        .addComponent(rdoNu))
                                                    .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtMaBN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addGap(40, 40, 40)
                                        .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pnlLamViecLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(pnlLamViecLayout.createSequentialGroup()
                                                        .addComponent(lblChanDoan)
                                                        .addGap(33, 33, 33)
                                                        .addComponent(txtChanDoan, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(pnlLamViecLayout.createSequentialGroup()
                                                        .addComponent(lblDiaChi)
                                                        .addGap(66, 66, 66)
                                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlLamViecLayout.createSequentialGroup()
                                                .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblNgayNhapVien)
                                                    .addComponent(lblNgayRaVien))
                                                .addGap(11, 11, 11)
                                                .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(cbbNgayRaVien, 0, 52, Short.MAX_VALUE)
                                                    .addComponent(cbbNgayVaoVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pnlLamViecLayout.createSequentialGroup()
                                                        .addComponent(lblThangSinh1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(cbbThangVaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lblNamSinh1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(cbbNamVaoVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGroup(pnlLamViecLayout.createSequentialGroup()
                                                        .addComponent(lblThangSinh2)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(cbbThangRaVien, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lblNamSinh2)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(cbbNamRaVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                                    .addGroup(pnlLamViecLayout.createSequentialGroup()
                                        .addComponent(rdoDangDieuTri)
                                        .addGap(30, 30, 30)
                                        .addComponent(rdoRaVien)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(pnlLamViecLayout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(lblTieuDe)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        pnlLamViecLayout.setVerticalGroup(
            pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLamViecLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTieuDe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMaBN)
                        .addComponent(lblDiaChi)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMaBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHoTen)
                    .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtChanDoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblChanDoan))
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLamViecLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNgayNhapVien)
                            .addComponent(cbbNgayVaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbThangVaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbNamVaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblThangSinh1)
                            .addComponent(lblNamSinh1))
                        .addGap(18, 18, 18)
                        .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNgayRaVien)
                            .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbNgayRaVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbbThangRaVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbbNamRaVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblThangSinh2)
                                .addComponent(lblNamSinh2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlLamViecLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNgaySinh)
                            .addComponent(cbbNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbThangSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblThangSinh)
                            .addComponent(lblNamSinh))
                        .addGap(18, 18, 18)
                        .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu)
                            .addComponent(lblGioiTinh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoDangDieuTri)
                            .addComponent(rdoRaVien)
                            .addComponent(lblTinhTrang))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(pnlLamViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua)
                    .addComponent(btnThem)
                    .addComponent(btnXoa)
                    .addComponent(btnTimKiem))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout pnlTongLayout = new javax.swing.GroupLayout(pnlTong);
        pnlTong.setLayout(pnlTongLayout);
        pnlTongLayout.setHorizontalGroup(
            pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongLayout.createSequentialGroup()
                .addComponent(pnlChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(pnlLamViec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        pnlTongLayout.setVerticalGroup(
            pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongLayout.createSequentialGroup()
                .addGroup(pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(pnlTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed

    private void rdoDangDieuTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDangDieuTriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoDangDieuTriActionPerformed

    private void btnBNNgoaiTruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBNNgoaiTruActionPerformed
        int ok = JOptionPane.showConfirmDialog(this, "Bạn muốn đến mục bệnh nhân ngoại trú !", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            BenhNhanNgoaiiTru nt = new BenhNhanNgoaiiTru(userName, passWord, ND);
            nt.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnBNNgoaiTruActionPerformed

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

    private void rdoDangDieuTriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoDangDieuTriMouseClicked
        this.cbbNgayRaVien.setEnabled(false);
        this.cbbThangRaVien.setEnabled(false);
        this.cbbNamRaVien.setEnabled(false);

    }//GEN-LAST:event_rdoDangDieuTriMouseClicked

    private void rdoRaVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoRaVienMouseClicked
        this.cbbNgayRaVien.setEnabled(true);
        this.cbbThangRaVien.setEnabled(true);
        this.cbbNamRaVien.setEnabled(true);
    }//GEN-LAST:event_rdoRaVienMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (!"BS".equals(userName)) {
            JOptionPane.showMessageDialog(this, "Quyền hạn của bạn không để để thực hiện chức năng!");
            return;
        }
        BenhNhan bn = this.docForm();
        if (bn == null) {
            return;
        }
        try {
            ArrayList<BenhNhan> list = this.qlbn.xuatDanhSach();
            if (!list.isEmpty()) {
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
        if (!"BS".equals(userName)) {
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
        if (!"BS".equals(userName)) {
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
                if (x.getMaBN().equalsIgnoreCase(maBN) && x.getLoaiBN() == true) {
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
            int ok = JOptionPane.showConfirmDialog(this, "Bạn xác nhận muốn xóa thông tin bệnh nhân có mã: " + maBN, "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (ok == JOptionPane.YES_OPTION) {
                this.qlbn.xoa(maBN);
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                this.hienThi();
                this.khoiTaoUI();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại!");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        if (!"BS".equals(userName)) {
            JOptionPane.showMessageDialog(this, "Quyền hạn của bạn không để để thực hiện chức năng!");
            return;
        }
        String maBN = JOptionPane.showInputDialog(this, "Nhập mã bệnh nhân cần tìm:");
        if (maBN == null) {
            return;
        }
        try {
            BenhNhan bn = this.qlbn.timKiem(maBN, true);
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
            if (bn.getNgayNhapVien() != null) {
                String[] nvv = bn.getNgayNhapVien().split("-");
                this.cbbNgayVaoVien.setSelectedItem(nvv[0]);
                this.cbbThangVaoVien.setSelectedItem(nvv[1]);
                this.cbbNamVaoVien.setSelectedItem(nvv[2]);
            }
            if (bn.getNgayRaVien() == null) {
                this.rdoDangDieuTri.setSelected(true);
                this.cbbNgayRaVien.setEnabled(false);
                this.cbbThangRaVien.setEnabled(false);
                this.cbbNamRaVien.setEnabled(false);
            } else {
                this.rdoRaVien.setSelected(true);
                if (bn.getNgayRaVien() != null) {
                    String[] nrv = bn.getNgayRaVien().split("-");
                    this.cbbNgayRaVien.setSelectedItem(nrv[0]);
                    this.cbbThangRaVien.setSelectedItem(nrv[1]);
                    this.cbbNamRaVien.setSelectedItem(nrv[2]);
                }
                this.cbbNgayRaVien.setEnabled(true);
                this.cbbThangRaVien.setEnabled(true);
                this.cbbNamRaVien.setEnabled(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối!");
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void cbbThangSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThangSinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbThangSinhActionPerformed

    private void cbbThangVaoVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThangVaoVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbThangVaoVienActionPerformed

    private void cbbThangRaVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThangRaVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbThangRaVienActionPerformed

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked
        int viTri = this.tblDanhSach.getSelectedRow();
        if (viTri == -1) {
            return;
        }
        String mbn = (String) this.tblDanhSach.getValueAt(viTri, 0);
        try {
            for (BenhNhan bn : this.qlbn.xuatDanhSach()) {
                if (bn.getMaBN().equalsIgnoreCase(mbn)) {
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
                    if (bn.getNgayNhapVien() != null) {
                        String[] nvv = bn.getNgayNhapVien().split("-");
                        this.cbbNgayVaoVien.setSelectedItem(nvv[0]);
                        this.cbbThangVaoVien.setSelectedItem(nvv[1]);
                        this.cbbNamVaoVien.setSelectedItem(nvv[2]);
                    }
                    if (bn.getNgayRaVien() == null) {
                        this.rdoDangDieuTri.setSelected(true);
                        this.cbbNgayRaVien.setEnabled(false);
                        this.cbbThangRaVien.setEnabled(false);
                        this.cbbNamRaVien.setEnabled(false);
                    } else {
                        this.rdoRaVien.setSelected(true);
                        if (bn.getNgayRaVien() != null) {
                            String[] nrv = bn.getNgayRaVien().split("-");
                            this.cbbNgayRaVien.setSelectedItem(nrv[0]);
                            this.cbbThangRaVien.setSelectedItem(nrv[1]);
                            this.cbbNamRaVien.setSelectedItem(nrv[2]);
                        }
                        this.cbbNgayRaVien.setEnabled(true);
                        this.cbbThangRaVien.setEnabled(true);
                        this.cbbNamRaVien.setEnabled(true);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối!");
            e.printStackTrace();
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
    private javax.swing.ButtonGroup bgrTinhTrang;
    private javax.swing.JButton btnBNNgoaiTru;
    private javax.swing.JButton btnBNNoiTru;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThanhToanVienPhi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTroLai;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbNamRaVien;
    private javax.swing.JComboBox<String> cbbNamSinh;
    private javax.swing.JComboBox<String> cbbNamVaoVien;
    private javax.swing.JComboBox<String> cbbNgayRaVien;
    private javax.swing.JComboBox<String> cbbNgaySinh;
    private javax.swing.JComboBox<String> cbbNgayVaoVien;
    private javax.swing.JComboBox<String> cbbThangRaVien;
    private javax.swing.JComboBox<String> cbbThangSinh;
    private javax.swing.JComboBox<String> cbbThangVaoVien;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnhUser;
    private javax.swing.JLabel lblChanDoan;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMaBN;
    private javax.swing.JLabel lblNamSinh;
    private javax.swing.JLabel lblNamSinh1;
    private javax.swing.JLabel lblNamSinh2;
    private javax.swing.JLabel lblNgayNhapVien;
    private javax.swing.JLabel lblNgayRaVien;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblNguoiDung;
    private javax.swing.JLabel lblThangSinh;
    private javax.swing.JLabel lblThangSinh1;
    private javax.swing.JLabel lblThangSinh2;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTinhTrang;
    private javax.swing.JPanel pnlChucNang;
    private javax.swing.JPanel pnlLamViec;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JRadioButton rdoDangDieuTri;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoRaVien;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txtChanDoan;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaBN;
    // End of variables declaration//GEN-END:variables
}
