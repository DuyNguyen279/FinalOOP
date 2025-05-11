/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.controller;

import com.doan.dao.NCCDAO;
import com.doan.dao.NhanVienDAO;
import com.doan.dao.SanPhamDAO;
import com.doan.dao.TaiKhoanDAO;
import com.doan.model.NCC;
import com.doan.model.NhanVien;
import com.doan.model.SanPham;
import com.doan.model.TaiKhoan;
import java.sql.Date;

/**
 *
 * @author Asus
 */
public class edit {
    public static edit getInstance(){
        return new edit();
    }

    public int editItem(String id, String name, double price, double importPrice, int soLuong, Date nsx, Date hsd, String loai) {
        SanPham sp = new SanPham(id, name, price, importPrice, soLuong, nsx, hsd, loai);
        return new SanPhamDAO().getInstance().update(sp);
    }

    public int editSupplier(String id, String name, String address, String sdt, String email){
        NCC ncc = new NCC(id, name, address, sdt, email);
        return new NCCDAO().getInstance().update(ncc);
    }

    public int editEmployee(String id, String name, int age, boolean gender,String address, String phone, double salary, String email){
        NhanVien nv = new NhanVien(id, name, age, gender, address, phone, salary);
        new NhanVienDAO().getInstance().update(nv);
        TaiKhoan tk = new TaiKhoanDAO().getInstance().selectByMaNV(id);
        tk.setEmail(email);
        return new TaiKhoanDAO().getInstance().update(tk);
    }

    public int changePassword (String id, String newPassword){
        TaiKhoan tk = new TaiKhoanDAO().getInstance().selectById(id);
        tk.setPassword(newPassword);
        return new TaiKhoanDAO().getInstance().update(tk);
    }

    public int changeStatus (String id){
        TaiKhoan tk = new TaiKhoanDAO().getInstance().selectById(id);
        tk.setStatus(!tk.getStatus());
        return new TaiKhoanDAO().getInstance().update(tk);
    }

    public int changeRole (String id){
        TaiKhoan tk = new TaiKhoanDAO().getInstance().selectById(id);
        if (tk.getRole().equals("Employee")) 
            tk.setRole("Managet");
        else tk.setRole("Employee");
        return new TaiKhoanDAO().getInstance().update(tk);
    }
}
