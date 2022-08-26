/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DoiTuong.BenhNhan;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class QuanLyBenhNhan implements QLBN_Interface {

    private final ArrayList<BenhNhan> danhSach;
    private final Connection con;

    public QuanLyBenhNhan(String userName, String passWord) throws Exception {
        this.danhSach = new ArrayList<>();
        con = DriverManager.getConnection("jdbc:sqlserver://localhost;database=QLBN;", userName, passWord);
    }

    public void close() throws Exception {
        con.close();
    }

    @Override
    public ArrayList<BenhNhan> xuatDanhSach() throws Exception {
        String sql = "select * from benhnhan";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        this.danhSach.clear();
        while (rs.next()) {
            String maBN = rs.getString("MABN");

            String hoTen = rs.getString("HOTEN");

            String ns = rs.getDate("NGAYSINH").toString();
            String[] a = ns.split("-");
            String ngaySinh = a[2] + "-" + a[1] + "-" + a[0];

            String gioiTinh = rs.getBoolean("GIOITINH") == true ? "Nam" : "Nữ";

            String diaChi = rs.getString("DIACHI");

            String chanDoan = rs.getString("CHANDOAN");

            boolean loaiBN = rs.getBoolean("LOAIBN");

            String nk = rs.getString("NGAYKHAM");
            String ngayKham;
            if (nk == null) {
                ngayKham = nk;
            } else {
                String[] b = nk.split("-");
                ngayKham = b[2] + "-" + b[1] + "-" + b[0];
            }

            String nnv = rs.getString("NGAYNHAPVIEN");
            String ngayNhapVien;
            if (nnv == null) {
                ngayNhapVien = nnv;
            } else {
                String[] c = nnv.split("-");
                ngayNhapVien = c[2] + "-" + c[1] + "-" + c[0];
            }

            String tingTrang = rs.getBoolean("TINHTRANG")==true ? "Đang điều trị" : "Ra viện";

            String nrv = rs.getString("NGAYRAVIEN");
            String ngayRaVien;
            if (nrv == null) {
                ngayRaVien = nrv;
            } else {
                String[] d = nrv.split("-");
                ngayRaVien = d[2] + "-" + d[1] + "-" + d[0];
            }

            String chucVu = rs.getString("CHUCVU");

            BenhNhan bn = new BenhNhan(maBN, hoTen, ngaySinh, gioiTinh, diaChi, chanDoan, loaiBN, ngayKham, ngayNhapVien, tingTrang, ngayRaVien, chucVu);
            this.danhSach.add(bn);
        }
        return this.danhSach;
    }

    @Override
    public boolean them(BenhNhan bn) throws Exception {
        String sql = "insert into benhnhan values(?,?,?,?,?,?,?,?,?,?,?,'BS')";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, bn.getMaBN());

        pst.setString(2, bn.getHoTen());

        String ns = bn.getNgaySinh();
        String[] a = ns.split("-");
        String ngaySinh = a[2] + "-" + a[1] + "-" + a[0];
        pst.setString(3, ngaySinh);

        pst.setBoolean(4, bn.getGioiTinh().equalsIgnoreCase("Nam"));

        pst.setString(5, bn.getDiaChi());

        pst.setString(6, bn.getChanDoan());

        pst.setBoolean(7, bn.getLoaiBN());

        String nk = bn.getNgayKham();
        if (nk == null) {
            pst.setString(8, null);
        } else {
            String[] b = nk.split("-");
            String ngayKham = b[2] + "-" + b[1] + "-" + b[0];
            pst.setString(8, ngayKham);
        }

        String nnv = bn.getNgayNhapVien();
        if (nnv == null) {
            pst.setString(9, null);
        } else {
            String[] c = nnv.split("-");
            String ngayNhapVien = c[2] + "-" + c[1] + "-" + c[0];
            pst.setString(9, ngayNhapVien);
        }
        if (bn.getTinhTrang() == null) {
            pst.setString(10, null);
        } else {
            if(bn.getTinhTrang().equalsIgnoreCase("Đang điều trị")){
                pst.setBoolean(10, true);
            }else pst.setBoolean(10, false);
        }

        String nrv = bn.getNgayRaVien();
        if (nrv == null) {
            pst.setString(11, null);
        } else {
            String[] d = nrv.split("-");
            String ngayRaVien = d[2] + "-" + d[1] + "-" + d[0];
            pst.setString(11, ngayRaVien);
        }

        return pst.executeUpdate() > 0;
    }

    @Override
    public boolean sua(BenhNhan bn) throws Exception {
        String sql = "update benhnhan set hoten=?, ngaysinh=?, gioitinh=?, diachi=?, chandoan=?, loaibn=?, ngaykham=?, ngaynhapvien=?, tinhtrang=?, ngayravien=?, chucvu=? where mabn=?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, bn.getHoTen());

        String ns = bn.getNgaySinh();
        String[] a = ns.split("-");
        String ngaySinh = a[2] + "-" + a[1] + "-" + a[0];
        pst.setString(2, ngaySinh);

        pst.setBoolean(3, bn.getGioiTinh().equalsIgnoreCase("Nam"));

        pst.setString(4, bn.getDiaChi());

        pst.setString(5, bn.getChanDoan());

        pst.setBoolean(6, bn.getLoaiBN());

        String nk = bn.getNgayKham();
        if (nk == null) {
            pst.setString(7, nk);
        } else {
            String[] b = nk.split("-");
            String ngayKham = b[2] + "-" + b[1] + "-" + b[0];
            pst.setString(7, ngayKham);
        }

        String nnv = bn.getNgayNhapVien();
        if (nnv == null) {
            pst.setString(8, nnv);
        } else {
            String[] c = nnv.split("-");
            String ngayNhapVien = c[2] + "-" + c[1] + "-" + c[0];
            pst.setString(8, ngayNhapVien);
        }
        if (bn.getTinhTrang() == null) {
            pst.setString(9, null);
        } else {
            if(bn.getTinhTrang().equalsIgnoreCase("Đang điều trị")){
                pst.setBoolean(9, true);
            }else pst.setBoolean(9, false);
        }

        String nrv = bn.getNgayRaVien();
        if (nrv == null) {
            pst.setString(10, nrv);
        } else {
            String[] d = nrv.split("-");
            String ngayRaVien = d[2] + "-" + d[1] + "-" + d[0];
            pst.setString(10, ngayRaVien);
        }

        pst.setString(11, bn.getChucVu());

        pst.setString(12, bn.getMaBN());

        return pst.executeUpdate() > 0;
    }

    @Override
    public boolean xoa(String maBN) throws Exception {
        String sql = "delete from benhnhan where mabn=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, maBN);
        return pst.executeUpdate() > 0;
    }

    @Override
    public BenhNhan timKiem(String maBN, boolean loaiBN) throws Exception {
        String sql = "select * from benhnhan where mabn=? and loaibn=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, maBN);
        pst.setBoolean(2, loaiBN);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            BenhNhan bn = new BenhNhan();

            bn.setMaBN(rs.getString("MABN"));

            bn.setHoTen(rs.getString("HOTEN"));

            String ns = rs.getString("NGAYSINH");
            String[] a = ns.split("-");
            String ngaySinh = a[2] + "-" + a[1] + "-" + a[0];
            bn.setNgaySinh(ngaySinh);

            bn.setGioiTinh(rs.getBoolean("GIOITINH") == true ? "Nam" : "Nữ");

            bn.setDiaChi(rs.getString("DIACHI"));

            bn.setChanDoan(rs.getString("CHANDOAN"));

            bn.setLoaiBN(rs.getBoolean("LOAIBN"));

            String nk = rs.getString("NGAYKHAM");
            if (nk == null) {
                bn.setNgayKham(nk);
            } else {
                String[] b = nk.split("-");
                String ngayKham = b[2] + "-" + b[1] + "-" + b[0];
                bn.setNgayKham(ngayKham);
            }

            String nnv = rs.getString("NGAYNHAPVIEN");
            if (nnv == null) {
                bn.setNgayNhapVien(nnv);
            } else {
                String[] c = nnv.split("-");
                String ngayNhapVien = c[2] + "-" + c[1] + "-" + c[0];
                bn.setNgayNhapVien(ngayNhapVien);
            }

            bn.setTinhTrang(rs.getBoolean("TINHTRANG") == true ? "Đang điều trị" : "Ra viện");

            String nrv = rs.getString("NGAYRAVIEN");
            if (nrv == null) {
                bn.setNgayRaVien(nrv);
            } else {
                String[] d = nrv.split("-");
                String ngayRaVien = d[2] + "-" + d[1] + "-" + d[0];
                bn.setNgayRaVien(ngayRaVien);
            }

            bn.setChucVu(rs.getString("CHUCVU"));

            return bn;
        }
        return null;
    }
}
