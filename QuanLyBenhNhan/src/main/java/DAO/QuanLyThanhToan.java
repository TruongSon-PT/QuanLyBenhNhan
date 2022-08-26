/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DoiTuong.ThanhToan;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class QuanLyThanhToan implements QLTT_Interface{
    private final ArrayList<ThanhToan> danhSach;
    private final Connection con;
    public QuanLyThanhToan(String userName, String passWord) throws Exception{
        this.danhSach = new ArrayList<>();
        con = DriverManager.getConnection("jdbc:sqlserver://localhost;database=QLBN;", userName, passWord);
    }
    public void close() throws Exception{
        con.close();
    }
    @Override
    public ArrayList<ThanhToan> xuatDanhSach() throws Exception {
        String sql = "select * from thanhtoan";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        this.danhSach.clear();
        while(rs.next()){
            ThanhToan tt = new ThanhToan();
            tt.setMaTT(rs.getString("MATT"));
            tt.setMaBN(rs.getString("MABN"));
            tt.setVienPhi(Long.parseLong(rs.getString("VIENPHI")));
            tt.setTrangThai(rs.getBoolean("TRANGTHAI")?"Đã thanh toán":"Chưa thanh toán");
            tt.setChucVu(rs.getString("CHUCVU"));
            this.danhSach.add(tt);
        }
        return this.danhSach;
    }

    @Override
    public boolean them(ThanhToan tt) throws Exception {
        String sql = "insert into thanhtoan values(?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, tt.getMaTT());
        pst.setString(2, tt.getMaBN());
        pst.setString(3, tt.getVienPhi().toString());
        pst.setBoolean(4, tt.getTrangThai().equalsIgnoreCase("Đã thanh toán"));
        pst.setString(5, tt.getChucVu());
        return pst.executeUpdate()>0;
    }

    @Override
    public boolean sua(ThanhToan tt) throws Exception {
        String sql = "update thanhtoan set mabn=?, vienphi=?, trangthai=?, chucvu=? where matt=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, tt.getMaBN());
        pst.setString(2, tt.getVienPhi().toString());
        pst.setBoolean(3, tt.getTrangThai().equalsIgnoreCase("Đã thanh toán"));
        pst.setString(4, tt.getChucVu());
        pst.setString(5, tt.getMaTT());
        return pst.executeUpdate()>0;
    }

    @Override
    public boolean xoa(String maTT) throws Exception {
        String sql = "delete from thanhtoan where matt=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, maTT);
        return pst.executeUpdate()>0;
    }

    @Override
    public ThanhToan timKiem(String maTT) throws Exception {
        String sql = "select * from thanhtoan where matt=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, maTT);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            ThanhToan tt = new ThanhToan();
            tt.setMaTT(rs.getString("MATT"));
            tt.setMaBN(rs.getString("MABN"));
            tt.setVienPhi(Long.parseLong(rs.getString("VIENPHI")));
            tt.setTrangThai(rs.getString("TRANGTHAI"));
            tt.setChucVu(rs.getString("CHUCVU"));
            return tt;
        }
        return null;
    }
    
}
