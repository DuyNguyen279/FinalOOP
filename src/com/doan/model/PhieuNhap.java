/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class PhieuNhap {
    private String MaPhieuNhap;
    private NCC Ncc;
    private NhanVien Nv;
    private Date NgayNhap;
    private List<SanPham> SP;
    private double TongTien;
    
    public PhieuNhap(String maPhieuNhap, NCC ncc, NhanVien nv, Date ngayNhap, List<SanPham> sP, double tongTien) {
        MaPhieuNhap = maPhieuNhap;
        Ncc = ncc;
        Nv = nv;
        NgayNhap = ngayNhap;
        SP = sP;
        TongTien = tongTien;
    }

    public PhieuNhap() {
        Ncc = new NCC();
        Nv = new NhanVien();
        SP = new ArrayList<SanPham>();
    }

    public String getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        MaPhieuNhap = maPhieuNhap;
    }

    public NCC getNcc() {
        return Ncc;
    }

    public void setNcc(NCC ncc) {
        Ncc = ncc;
    }

    public NhanVien getNv() {
        return Nv;
    }

    public void setNv(NhanVien nv) {
        Nv = nv;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        NgayNhap = ngayNhap;
    }

    public List<SanPham> getSP() {
        return SP;
    }

    public void setSP(List<SanPham> sP) {
        SP = sP;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double tongTien) {
        TongTien = tongTien;
    }
    
    
}
