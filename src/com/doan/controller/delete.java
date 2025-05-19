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

/**
 *
 * @author Asus
 */
public class delete {
    public static delete getInstance() {
        return new delete();
    }

    public int deleteItemById(String id){
        SanPham sp = new SanPhamDAO().getInstance().selectById(id);
        return new SanPhamDAO().getInstance().softdelete(sp);
    }

    public int deleteBillById(String id){
        HoaDon hd = new HoaDonDAO().getInstance().selectById(id);
        return new HoaDonDAO().getInstance().delete(hd);
    }

    public int deleteSupplierById(String id){
        NCC ncc = new NCCDAO().getInstance().selectById(id);
        return new NCCDAO().getInstance().softdelete(ncc);
    }

    public int deleteGrnById(String id){
        PhieuNhap pn = new PhieuNhapDAO().getInstance().selectById(id);
        return new PhieuNhapDAO().getInstance().delete(pn);
    }

    public int deleteEmployeeById(String id){
        NhanVien nv = new NhanVienDAO().getInstance().selectById(id);
        return new NhanVienDAO().getInstance().softdelete(nv);
    }

    public int deleteAccountById(String id){
        TaiKhoan tk = new TaiKhoanDAO().getInstance().selectById(id);
        return new TaiKhoanDAO().getInstance().delete(tk);
    }
}
