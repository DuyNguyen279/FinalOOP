/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.model;

/**
 *
 * @author Asus
 */
public class NhanVien {
    private String MaNV;
    private String TenNV;
    private int Tuoi;
    private boolean GioiTinh;
    private String DiaChi;
    private String SDT;
    private double Luong;
    

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, int tuoi, boolean gioiTinh, String diaChi, String sDT, double luong) {
        MaNV = maNV;
        TenNV = tenNV;
        Tuoi = tuoi;
        GioiTinh = gioiTinh;
        DiaChi = diaChi;
        SDT = sDT;
        Luong = luong;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }

    public int getTuoi() {
        return Tuoi;
    }

    public void setTuoi(int tuoi) {
        Tuoi = tuoi;
    }

    public boolean getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String sDT) {
        SDT = sDT;
    }

    public double getLuong() {
        return Luong;
    }

    public void setLuong(double luong) {
        Luong = luong;
    }
}
