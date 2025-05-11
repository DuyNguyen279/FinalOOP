/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.model;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class SanPham {
    private String MaSP;
    private String TenSP;
    private double GiaNY;
    private double GiaNhap;
    private int SoLuong;
    private Date NSX;
    private Date HSD;
    private String Loai;

    public SanPham(String maSP, String tenSP, double giaNY, double giaNhap, int soLuong, Date nSX, Date hSD, String loai) {
        MaSP = maSP;
        TenSP = tenSP;
        GiaNY = giaNY;
        GiaNhap = giaNhap;
        SoLuong = soLuong;
        NSX = nSX;
        HSD = hSD;
        Loai = loai;
    }

    public SanPham() {
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public double getGiaNY() {
        return GiaNY;
    }

    public void setGiaNY(double giaNY) {
        GiaNY = giaNY;
    }

    public double getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        GiaNhap = giaNhap;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public Date getNSX() {
        return NSX;
    }

    public void setNSX(Date nSX) {
        NSX = nSX;
    }

    public Date getHSD() {
        return HSD;
    }

    public void setHSD(Date hSD) {
        HSD = hSD;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }
}
