/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.controller;

import com.doan.dao.BaoCaoDAO;
import com.doan.dao.HoaDonDAO;
import com.doan.dao.NhanVienDAO;
import com.doan.dao.SanPhamDAO;
import com.doan.database.JDBCUtil;
import com.doan.model.BaoCao;
import com.doan.model.SanPham;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Ctr_BaoCao {
    public Ctr_BaoCao getInstance() {
        return new Ctr_BaoCao();
    }

    public List<SanPham> getSanPhamDaBan() {
        List<SanPham> list = new ArrayList<SanPham>();

        List<String> BillInMonth = new ArrayList<String>();
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT MaHD FROM hoadon WHERE MONTH(NgayLap) = MONTH(CURRENT_DATE()) AND YEAR(NgayLap) = YEAR(CURRENT_DATE())";
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                BillInMonth.add(rs.getString("MaHD"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for (String bill : BillInMonth) {
                String sql = "SELECT MaSP, SUM(SoLuongBan) AS SoLuong FROM hangxuat WHERE MaHD = ? GROUP BY MaSP";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, bill);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    SanPham sp = new SanPhamDAO().selectById(rs.getString("MaSP"));
                    sp.setSoLuong(rs.getInt("SoLuong"));
                    list.add(sp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<SanPham> getSanPhamDaBanInMonthYear(int month, int year) {
        List<SanPham> list = new ArrayList<SanPham>();

        List<String> BillInMonth = new ArrayList<String>();
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT MaHD FROM hoadon WHERE MONTH(NgayLap) = ? AND YEAR(NgayLap) = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BillInMonth.add(rs.getString("MaHD"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for (String bill : BillInMonth) {
                String sql = "SELECT MaSP, SUM(SoLuongBan) AS SoLuong FROM hangxuat WHERE MaHD = ? GROUP BY MaSP";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, bill);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    SanPham sp = new SanPhamDAO().selectById(rs.getString("MaSP"));
                    sp.setSoLuong(rs.getInt("SoLuong"));
                    list.add(sp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public BaoCao searchReport(int month, int year){
        BaoCao bc = null;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT MaBaoCao FROM baocao WHERE MONTH(NgayLap) = ? AND YEAR(NgayLap) = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bc = new BaoCaoDAO().getInstance().selectById(rs.getString("MaBaoCao"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return bc;
    }

    public BaoCao getReportInMonth(){
        //TODO
        BaoCao baoCao = null;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT MaBaoCao FROM baocao WHERE MONTH(NgayLap) = MONTH(CURRENT_DATE()) AND YEAR(NgayLap) = YEAR(CURRENT_DATE())";
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                baoCao = new BaoCaoDAO().getInstance().selectById(rs.getString("MaBaoCao"));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (baoCao == null) {
            baoCao = new BaoCao();
            baoCao.setMaBaoCao(new generateNewId().generateNewIdReport());
            baoCao.setNVLap(new NhanVienDAO().getInstance().selectById("NV0"));
            baoCao.setNgayLap(Date.valueOf(LocalDate.now()));
            baoCao.setTongDoanhThu(0);
            baoCao.setSanPhamHuHong(new ArrayList<>());
            baoCao.setSanPhamXuHuong(new ArrayList<>());

            List<SanPham> list = getSanPhamDaBan();
            for (SanPham x : list) {
                if (x.getSoLuong() > 10){
                    baoCao.getSanPhamXuHuong().add(x);
                }
                baoCao.setTongDoanhThu(baoCao.getTongDoanhThu() + x.getGiaNY() * x.getSoLuong());
            }

            new BaoCaoDAO().getInstance().insert(baoCao);
        }

        return baoCao;
    }

    public void addSanPhamHuHong(String maBaocao, String masp, int soluong, double thiethai){
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "INSERT INTO sphuhong (MaBaoCao, MaSP, SoLuong, ThietHai) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maBaocao);
            ps.setString(2, masp);
            ps.setInt(3, soluong);
            ps.setDouble(4, thiethai);
            ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SanPham sp = new SanPhamDAO().selectById(masp);
        sp.setSoLuong(sp.getSoLuong() - soluong);
        SanPhamDAO.getInstance().update(sp);
    }

    public void addSanPhamHot(String maBaocao, String masp, int soluong) {
        Connection conn = JDBCUtil.getJDBCConnection();
        BaoCao bc = new BaoCaoDAO().getInstance().selectById(maBaocao);
        for (SanPham x : bc.getSanPhamXuHuong()) {
            if (x.getMaSP().equals(masp)) {
                String sql = "UPDATE spbanchay SET SoLuongBan = ? WHERE MaBaoCao = ? AND MaSP = ?";
                try {
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, soluong);
                    ps.setString(2, maBaocao);
                    ps.setString(3, masp);
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JDBCUtil.closeConnection(conn);
                return;
            }
        }

        try {
            String sql = "INSERT INTO spbanchay (MaBaoCao, MaSP, SoLuongBan) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,maBaocao);
            ps.setString(2, masp);
            ps.setInt(3, soluong);
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
        JDBCUtil.closeConnection(conn);

        return;
    }
}

