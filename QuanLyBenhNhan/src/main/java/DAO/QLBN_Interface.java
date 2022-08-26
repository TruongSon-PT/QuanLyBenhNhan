/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DoiTuong.BenhNhan;
import java.util.ArrayList;
/**
 *
 * @author Administrator
 */
public interface QLBN_Interface {
    public ArrayList<BenhNhan> xuatDanhSach() throws Exception;
    public boolean them(BenhNhan bn) throws Exception;
    public boolean sua(BenhNhan bn) throws Exception;
    public boolean xoa(String maBN) throws Exception;
    public BenhNhan timKiem(String maBN, boolean loaiBN) throws Exception;
}
