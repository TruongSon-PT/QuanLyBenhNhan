package DAO;

import DoiTuong.NguoiDung;
import java.sql.*;
import java.util.ArrayList;

public class QuanLyNguoiDung {
    public Connection con;
    public NguoiDung dangNhap(String tdn) throws Exception {
        con = DriverManager.getConnection("jdbc:sqlserver://localhost;database=QLBN;", "ND", "123");
        String sql = "select * from nguoidung where username=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, tdn);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            NguoiDung nd = new NguoiDung();
            nd.setUserName(rs.getString("USERNAME"));
            nd.setPass(rs.getString("PASS"));
            nd.setChucVu(rs.getString("CHUCVU"));
            con.close();
            return nd;
        }
        con.close();
        return null;
    }
    public ArrayList<NguoiDung> xuatDanhSach() throws Exception{
        con = DriverManager.getConnection("jdbc:sqlserver://localhost;database=QLBN;", "ND", "123");
        String sql = "select * from nguoidung";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        ArrayList<NguoiDung> list = new ArrayList<>();
        while (rs.next()) {
            NguoiDung nd = new NguoiDung();
            nd.setUserName(rs.getString("USERNAME"));
            nd.setPass(rs.getString("PASS"));
            nd.setChucVu(rs.getString("CHUCVU"));
            list.add(nd);
        }
        con.close();
        return list;
    }
    public boolean them(NguoiDung nd) throws Exception{
        con = DriverManager.getConnection("jdbc:sqlserver://localhost;database=QLBN;", "ND", "123");
        String sql = "insert into nguoidung values(?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, nd.getUserName());
        pst.setString(2, nd.getPass());
        pst.setString(3, nd.getChucVu());
        return pst.executeUpdate()>0;
    }
    public boolean xoa(String tdn) throws Exception{
        con = DriverManager.getConnection("jdbc:sqlserver://localhost;database=QLBN;", "ND", "123");
        String sql = "delete from nguoidung where username=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, tdn);
        return pst.executeUpdate()>0;
    }
}
