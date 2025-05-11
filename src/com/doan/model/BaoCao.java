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
public class BaoCao {
    private String MaBaoCao;
    private NhanVien NVLap;
    private Date NgayLap;
    private List<SanPham> SanPhamHuHong;
    private List<SanPham> SanPhamXuHuong;
    private double TongDoanhThu;

    public BaoCao(String maBaoCao, NhanVien nVLap, Date ngayLap, List<SanPham> sanPhamHuHong,
            List<SanPham> sanPhamXuHuong, double tongTien) {
        MaBaoCao = maBaoCao;
        NVLap = nVLap;
        NgayLap = ngayLap;
        SanPhamHuHong = sanPhamHuHong;
        SanPhamXuHuong = sanPhamXuHuong;
        TongDoanhThu = tongTien;
    }

    public BaoCao() {
        SanPhamHuHong = new ArrayList<SanPham>();
        SanPhamXuHuong = new ArrayList<SanPham>();
    }

    public String getMaBaoCao() {
        return MaBaoCao;
    }

    public void setMaBaoCao(String maBaoCao) {
        MaBaoCao = maBaoCao;
    }

    public NhanVien getNVLap() {
        return NVLap;
    }

    public void setNVLap(NhanVien nVLap) {
        NVLap = nVLap;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date ngayLap) {
        NgayLap = ngayLap;
    }

    public List<SanPham> getSanPhamHuHong() {
        return SanPhamHuHong;
    }

    public void setSanPhamHuHong(List<SanPham> sanPhamHuHong) {
        SanPhamHuHong = sanPhamHuHong;
    }

    public List<SanPham> getSanPhamXuHuong() {
        return SanPhamXuHuong;
    }

    public void setSanPhamXuHuong(List<SanPham> sanPhamXuHuong) {
        SanPhamXuHuong = sanPhamXuHuong;
    }

    public double getTongDoanhThu() {
        return TongDoanhThu;
    }

    public void setTongDoanhThu(double tongTien) {
        TongDoanhThu = tongTien;
    }

    
    
}
