/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.dao;

import com.doan.database.JDBCUtil;
import com.doan.model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class NhanVienDAO implements DaoInterface<NhanVien> {
    public static NhanVienDAO getInstance() {
        return new NhanVienDAO();
    }

    @Override
    public int insert(NhanVien t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "INSERT INTO NhanVien (MaNV, TenNV, Tuoi, GioiTinh, DiaChi, SDT, Luong) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaNV());
            ps.setString(2, t.getTenNV());
            ps.setInt(3, t.getTuoi());
            ps.setBoolean(4, t.getGioiTinh());
            ps.setString(5, t.getDiaChi());
            ps.setString(6, t.getSDT());
            ps.setDouble(7, t.getLuong());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();    
        }
        return ketQua;
    }

    @Override
    public int update(NhanVien t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "UPDATE NhanVien SET TenNV = ?, Tuoi = ?, GioiTinh = ?, DiaChi = ?, SDT = ?, Luong = ? WHERE MaNV = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getTenNV());
            ps.setInt(2, t.getTuoi());
            ps.setBoolean(3, t.getGioiTinh());
            ps.setString(4, t.getDiaChi());
            ps.setString(5, t.getSDT());
            ps.setDouble(6, t.getLuong());
            ps.setString(7, t.getMaNV());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(NhanVien t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "DELETE FROM NhanVien WHERE MaNV = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaNV());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<NhanVien> selectAll() {
        ArrayList<NhanVien> list = new ArrayList<>();
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT * FROM NhanVien;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("MaNV");
                String tenNV = rs.getString("TenNV");
                int tuoi = rs.getInt("Tuoi");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String sDT = rs.getString("SDT");
                double luong = rs.getDouble("Luong"); 
                boolean isDelete = rs.getBoolean("is_delete"); 
                NhanVien nv = new NhanVien(maNV, tenNV, tuoi, gioiTinh, diaChi, sDT, luong);
                nv.setIs_delete(isDelete);              
                list.add(nv);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public NhanVien selectById(String id) {
        NhanVien nv = null;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT * FROM NhanVien WHERE MaNV = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("MaNV");
                String tenNV = rs.getString("TenNV");
                int tuoi = rs.getInt("Tuoi");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String sDT = rs.getString("SDT");
                double luong = rs.getDouble("Luong");     
                boolean isDelete = rs.getBoolean("is_delete");           
                nv = new NhanVien(maNV, tenNV, tuoi, gioiTinh, diaChi, sDT, luong);
                nv.setIs_delete(isDelete);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return nv;
    }
    
    public ArrayList<NhanVien> selectByName(String name) {
        ArrayList<NhanVien> list = new ArrayList<>();
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT * FROM NhanVien WHERE TenNV LIKE ? COLLATE utf8mb4_general_ci;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("MaNV");
                NhanVien vien = selectById(maNV);
                list.add(vien);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }

    public int softdelete(NhanVien t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "UPDATE NhanVien SET is_delete = 1 WHERE MaNV = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaNV());
            ketQua = ps.executeUpdate();
            sql = "UPDATE TaiKhoan SET status = 0 WHERE MaNV = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaNV());
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
}

