/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoiTuong;

/**
 *
 * @author Administrator
 */
public class BenhNhan {
    private String maBN;
    private String hoTen;
    private String ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private String chanDoan;
    private Boolean loaiBN;
    private String ngayKham;
    private String ngayNhapVien;
    private String tinhTrang;
    private String ngayRaVien;
    private String chucVu;

    public BenhNhan() {
    }

    public BenhNhan(String maBN, String hoTen, String ngaySinh, String gioiTinh, String diaChi, String chanDoan, Boolean loaiBN, String ngayKham, String ngayNhapVien, String tinhTrang, String ngayRaVien, String chucVu) {
        this.maBN = maBN;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.chanDoan = chanDoan;
        this.loaiBN = loaiBN;
        this.ngayKham = ngayKham;
        this.ngayNhapVien = ngayNhapVien;
        this.tinhTrang = tinhTrang;
        this.ngayRaVien = ngayRaVien;
        this.chucVu = chucVu;
    }

    public String getMaBN() {
        return maBN;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getChanDoan() {
        return chanDoan;
    }

    public void setChanDoan(String chanDoan) {
        this.chanDoan = chanDoan;
    }

    public Boolean getLoaiBN() {
        return loaiBN;
    }

    public void setLoaiBN(Boolean loaiBN) {
        this.loaiBN = loaiBN;
    }

    public String getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(String ngayKham) {
        this.ngayKham = ngayKham;
    }

    public String getNgayNhapVien() {
        return ngayNhapVien;
    }

    public void setNgayNhapVien(String ngayNhapVien) {
        this.ngayNhapVien = ngayNhapVien;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getNgayRaVien() {
        return ngayRaVien;
    }

    public void setNgayRaVien(String ngayRaVien) {
        this.ngayRaVien = ngayRaVien;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
}
