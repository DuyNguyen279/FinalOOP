/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class HoaDon {
    private String MaHD;
    private NhanVien NVLap;
    private LocalDateTime NgayLap;
    private List<SanPham> SP;
    private double TongTien;
    
    public HoaDon(String maHD, NhanVien nVLap, LocalDateTime ngayLap, List<SanPham> sP, double tongTien) {
        MaHD = maHD;
        NVLap = nVLap;
        NgayLap = ngayLap;
        SP = sP;
        TongTien = tongTien;
    }

    public HoaDon() {
        SP = new ArrayList<SanPham>();
        NVLap = new NhanVien();
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public NhanVien getNVLap() {
        return NVLap;
    }

    public void setNVLap(NhanVien nVLap) {
        NVLap = nVLap;
    }

    public LocalDateTime getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(LocalDateTime ngayLap) {
        NgayLap = ngayLap;
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
