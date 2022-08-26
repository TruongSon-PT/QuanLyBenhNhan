/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DoiTuong.ThanhToan;
import java.util.ArrayList;
/**
 *
 * @author Administrator
 */
public interface QLTT_Interface {
    public ArrayList<ThanhToan> xuatDanhSach() throws Exception;
    public boolean them(ThanhToan tt) throws Exception;
    public boolean sua(ThanhToan tt) throws Exception;
    public boolean xoa(String maTT) throws Exception;
    public ThanhToan timKiem(String maTT) throws Exception;
}
