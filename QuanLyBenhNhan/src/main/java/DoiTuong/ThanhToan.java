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
public class ThanhToan {
    private String maTT;
    private String maBN;
    private Long vienPhi;
    private String trangThai;
    private String chucVu;

    public ThanhToan() {
    }

    public ThanhToan(String maTT, String maBN, Long vienPhi, String trangThai, String chucVu) {
        this.maTT = maTT;
        this.maBN = maBN;
        this.vienPhi = vienPhi;
        this.trangThai = trangThai;
        this.chucVu = chucVu;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getMaBN() {
        return maBN;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public Long getVienPhi() {
        return vienPhi;
    }

    public void setVienPhi(Long vienPhi) {
        this.vienPhi = vienPhi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
}
