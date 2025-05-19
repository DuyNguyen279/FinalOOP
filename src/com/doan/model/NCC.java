/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.model;

/**
 *
 * @author Asus
 */
public class NCC {
    private String MaNCC;
    private String TenNCC;
    private String DiaChi;
    private String SDT;
    private String Email;
    private boolean is_delete = false;

    public NCC(String maNCC, String tenNCC, String diaChi, String sDT, String email) {
        MaNCC = maNCC;
        TenNCC = tenNCC;
        DiaChi = diaChi;
        SDT = sDT;
        Email = email;
    }

    public NCC() {
    }

    public String getMaNCC() {
        return MaNCC;
    }
    public void setMaNCC(String maNCC) {
        MaNCC = maNCC;
    }
    public String getTenNCC() {
        return TenNCC;
    }
    public void setTenNCC(String tenNCC) {
        TenNCC = tenNCC;
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
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }
    
}