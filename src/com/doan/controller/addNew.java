/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.controller;

import com.doan.dao.HoaDonDAO;
import com.doan.dao.NCCDAO;
import com.doan.dao.NhanVienDAO;
import com.doan.dao.PhieuNhapDAO;
import com.doan.dao.SanPhamDAO;
import com.doan.dao.TaiKhoanDAO;
import com.doan.model.HoaDon;
import com.doan.model.NCC;
import com.doan.model.NhanVien;
import com.doan.model.PhieuNhap;
import com.doan.model.SanPham;
import com.doan.model.TaiKhoan;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Asus
 */
public class addNew {
    public static addNew getInstance(){
        return new addNew();
    }

    public void addNewItem(SanPham sp){
        new SanPhamDAO().getInstance().insert(sp);
    }

    public void addNewBill(String maHD,NhanVien nv, LocalDateTime ngayLap, List<SanPham> sp, double tongTien){
        HoaDon bill = new HoaDon(maHD,nv,ngayLap,sp,tongTien);
        new HoaDonDAO().getInstance().insert(bill);
    }

    public void addNewGrn(String maHD,NCC ncc,NhanVien nv,Date NgayNhap, List<SanPham> sp, double tongTien){
        PhieuNhap grn = new PhieuNhap(maHD,ncc,nv,NgayNhap,sp,tongTien);
        new PhieuNhapDAO().getInstance().insert(grn);
    }

    public void addNewSupplier(String maNcc, String tenNcc, String diaChi, String sdt, String email){
        NCC ncc = new NCC(maNcc,tenNcc,diaChi,sdt,email);
        new NCCDAO().getInstance().insert(ncc);
    }

    public void addNewEmployee(String maNV, String tenNV, int tuoiNV, boolean gioiTinh, String diaChi, String sdt, double luong){
        NhanVien nv = new NhanVien(maNV,tenNV,tuoiNV,gioiTinh,diaChi,sdt,luong);
        new NhanVienDAO().getInstance().insert(nv);
    }

    public void addNewAccount(String maTK, String tenTK, String username, String password, String role, boolean status, String email, NhanVien nv){
        TaiKhoan tk = new TaiKhoan(maTK,tenTK,username,password,role,status,email,nv);
        new TaiKhoanDAO().getInstance().insert(tk);
    }
}
