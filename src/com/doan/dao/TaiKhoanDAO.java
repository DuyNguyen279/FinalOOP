/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.dao;

import com.doan.database.JDBCUtil;
import com.doan.model.NhanVien;
import com.doan.model.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class TaiKhoanDAO implements DaoInterface<TaiKhoan> {
    public static TaiKhoanDAO getInstance() {
        return new TaiKhoanDAO();
    }

    @Override
    public int insert(TaiKhoan t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "INSERT INTO TaiKhoan (MaTK, TenTK, username, password, role, status, email, MaNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaTK());
            ps.setString(2, t.getTenTK());
            ps.setString(3, t.getUsername());
            ps.setString(4, t.getPassword());
            ps.setString(5, t.getRole());
            ps.setBoolean(6, t.getStatus());
            ps.setString(7, t.getEmail());
            ps.setString(8, t.getNhanVien().getMaNV());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(TaiKhoan t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "UPDATE TaiKhoan SET TenTK = ?, username = ?, password = ?, role = ?, status = ?, email = ?, MaNV = ? WHERE MaTK = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getTenTK());
            ps.setString(2, t.getUsername());
            ps.setString(3, t.getPassword());
            ps.setString(4, t.getRole());
            ps.setBoolean(5, t.getStatus());
            ps.setString(6, t.getEmail());
            ps.setString(7, t.getNhanVien().getMaNV());
            ps.setString(8, t.getMaTK());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int delete(TaiKhoan t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "DELETE FROM TaiKhoan WHERE MaTK = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaTK());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<TaiKhoan> selectAll() {
        ArrayList<TaiKhoan> list = new ArrayList<>();
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT tk.MaTK, tk.TenTK, tk.username, tk.password, tk.role, tk.status, tk.email, nv.MaNV, nv.TenNV, nv.Tuoi,nv.GioiTinh, nv.DiaChi, nv.SDT, nv.Luong FROM  TaiKhoan tk INNER JOIN  NhanVien nv ON tk.MaNV = nv.MaNV;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maTK = rs.getString("MaTK");
                String tenTK = rs.getString("TenTK");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                boolean status = rs.getBoolean("status");
                String email = rs.getString("email");
                String maNV = rs.getString("MaNV");
                String tenNV = rs.getString("TenNV");
                int tuoi = rs.getInt("Tuoi");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String sDT = rs.getString("SDT");
                double luong = rs.getDouble("Luong");
                list.add(new TaiKhoan(maTK, tenTK, username, password, role, status, email, new NhanVien(maNV, tenNV, tuoi, gioiTinh, diaChi, sDT, luong)));
            }    
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TaiKhoan selectById(String id) {
        TaiKhoan tk = null;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT tk.MaTK, tk.TenTK, tk.username, tk.password, tk.role, tk.status, tk.email, nv.MaNV, nv.TenNV, nv.Tuoi, nv.GioiTinh, nv.DiaChi, nv.SDT, nv.Luong FROM  TaiKhoan tk INNER JOIN  NhanVien nv ON tk.MaNV = nv.MaNV WHERE tk.MaTK =?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maTK = rs.getString("MaTK");
                String tenTK = rs.getString("TenTK");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                boolean status = rs.getBoolean("status");
                String email = rs.getString("email");
                String maNV = rs.getString("MaNV");
                String tenNV = rs.getString("TenNV");
                int tuoi = rs.getInt("Tuoi");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String sDT = rs.getString("SDT");
                double luong = rs.getDouble("Luong");
                tk = new TaiKhoan(maTK, tenTK, username, password, role, status, email, new NhanVien(maNV, tenNV, tuoi, gioiTinh, diaChi, sDT, luong));
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return tk;
    }

    public TaiKhoan selectByUsername(String username) {
        TaiKhoan tk = null;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT tk.MaTK, tk.TenTK, tk.username, tk.password, tk.role, tk.status, tk.email, nv.MaNV, nv.TenNV, nv.Tuoi, nv.GioiTinh, nv.DiaChi, nv.SDT, nv.Luong FROM  TaiKhoan tk INNER JOIN  NhanVien nv ON tk.MaNV = nv.MaNV WHERE tk.username =?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maTK = rs.getString("MaTK");
                String tenTK = rs.getString("TenTK");
                String password = rs.getString("password");
                String role = rs.getString("role");
                boolean status = rs.getBoolean("status");
                String email = rs.getString("email");
                String maNV = rs.getString("MaNV");
                String tenNV = rs.getString("TenNV");
                int tuoi = rs.getInt("Tuoi");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String sDT = rs.getString("SDT");
                double luong = rs.getDouble("Luong");
                tk = new TaiKhoan(maTK, tenTK, username, password, role, status, email, new NhanVien(maNV, tenNV, tuoi, gioiTinh, diaChi, sDT, luong));
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }    
        return tk;
    }
    
    public TaiKhoan selectByMaNV(String id){
        TaiKhoan tk = null;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT * FROM TaiKhoan WHERE MaNV = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maTK = rs.getString("MaTK");
                String tenTK = rs.getString("TenTK");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                boolean status = rs.getBoolean("status");
                String email = rs.getString("email");
                tk = new TaiKhoan(maTK, tenTK, username, password, role, status, email, new NhanVienDAO().selectById(id));
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return tk;
    }
}
