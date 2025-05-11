/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.model;

/**
 *
 * @author Asus
 */
public class TaiKhoan {
    private String MaTK;
    private String TenTK;
    private String username;
    private String password;
    private String role;
    private boolean status;
    private String email;
    private NhanVien nhanVien;

    public TaiKhoan(String maTK, String tenTK, String username, String password, String role, boolean status, String email, NhanVien nhanVien) {
        MaTK = maTK;
        TenTK = tenTK;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.email = email;
        this.nhanVien = nhanVien;
    }

    public TaiKhoan() {
        this.nhanVien = new NhanVien();
    }

    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String maTK) {
        MaTK = maTK;
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String tenTK) {
        TenTK = tenTK;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
}
