/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.dao;

import com.doan.database.JDBCUtil;
import com.doan.model.SanPham;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class SanPhamDAO implements DaoInterface<SanPham>{
    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }

    @Override
    public int insert(SanPham t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "INSERT INTO SanPham (MaSP, TenSP, GiaNY, GiaNhap, SoLuong, NSX, HSD, Loai) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaSP());
            ps.setString(2, t.getTenSP());
            ps.setDouble(3, t.getGiaNY());
            ps.setDouble(4, t.getGiaNhap());
            ps.setInt(5, t.getSoLuong());
            ps.setDate(6, t.getNSX());
            ps.setDate(7, t.getHSD());
            ps.setString(8, t.getLoai());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(SanPham t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "UPDATE SanPham SET TenSP = ?, GiaNY = ?, GiaNhap = ?, SoLuong = ?, NSX = ?, HSD = ?, Loai = ? WHERE MaSP = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getTenSP());
            ps.setDouble(2, t.getGiaNY());
            ps.setDouble(3, t.getGiaNhap());
            ps.setInt(4, t.getSoLuong());
            ps.setDate(5, t.getNSX());
            ps.setDate(6, t.getHSD());
            ps.setString(7, t.getLoai());
            ps.setString(8, t.getMaSP());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(SanPham t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "DELETE FROM SanPham WHERE MaSP = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaSP());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<SanPham> selectAll() {
        ArrayList<SanPham> list = new ArrayList<>();
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT * FROM SanPham;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maSP = rs.getString("MaSP");
                String tenSP = rs.getString("TenSP");
                double giaNY = rs.getDouble("GiaNY");
                double giaNhap = rs.getDouble("GiaNhap");
                int soLuong = rs.getInt("SoLuong");
                Date nsx = rs.getDate("NSX");
                Date hsd = rs.getDate("HSD");
                String loai = rs.getString("Loai");
                boolean isDelete = rs.getBoolean("is_delete");
                SanPham sp = new SanPham(maSP, tenSP, giaNY, giaNhap, soLuong, nsx, hsd, loai);
                sp.setIs_delete(isDelete);
                list.add(sp);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public SanPham selectById(String id) {
        SanPham sp = null;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT * FROM SanPham WHERE MaSP = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maSP = rs.getString("MaSP");
                String tenSP = rs.getString("TenSP");
                double giaNY = rs.getDouble("GiaNY");
                double giaNhap = rs.getDouble("GiaNhap");
                int soLuong = rs.getInt("SoLuong");
                Date nsx = rs.getDate("NSX");
                Date hsd = rs.getDate("HSD");
                String loai = rs.getString("Loai");
                boolean isDelete = rs.getBoolean("is_delete");
                sp = new SanPham(maSP, tenSP, giaNY, giaNhap, soLuong, nsx, hsd, loai);
                sp.setIs_delete(isDelete);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return sp;
    }
    
    public ArrayList<SanPham> selectByName(String key) {
        ArrayList<SanPham> list = null;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT * FROM SanPham WHERE TenSP LIKE ? COLLATE utf8mb4_general_ci;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String maSP = rs.getString("MaSP");
                String tenSP = rs.getString("TenSP");
                double giaNY = rs.getDouble("GiaNY");
                double giaNhap = rs.getDouble("GiaNhap");
                int soLuong = rs.getInt("SoLuong");
                Date nsx = rs.getDate("NSX");
                Date hsd = rs.getDate("HSD");
                String loai = rs.getString("Loai");
                boolean isDelete = rs.getBoolean("is_delete");
                SanPham sp = new SanPham(maSP, tenSP, giaNY, giaNhap, soLuong, nsx, hsd, loai);
                sp.setIs_delete(isDelete);
                list.add(sp);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public int softdelete(SanPham t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "UPDATE SanPham SET is_delete = 1 WHERE MaSP = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaSP());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
}
