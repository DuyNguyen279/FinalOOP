/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.dao;

import com.doan.database.JDBCUtil;
import com.doan.model.NCC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class NCCDAO implements DaoInterface<NCC>{
    public static NCCDAO getInstance() {
        return new NCCDAO();
    }

    @Override
    public int insert(NCC t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "INSERT INTO nhacungcap (MaNCC, TenNCC, DiaChi, SDT, Email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaNCC());
            ps.setString(2, t.getTenNCC());
            ps.setString(3, t.getDiaChi());
            ps.setString(4, t.getSDT());
            ps.setString (5, t.getEmail());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(NCC t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "UPDATE nhacungcap SET TenNCC = ?, DiaChi = ?, SDT = ?, Email = ? WHERE MaNCC = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getTenNCC());
            ps.setString(2, t.getDiaChi());
            ps.setString(3, t.getSDT());
            ps.setString(4, t.getEmail());
            ps.setString(5, t.getMaNCC());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(NCC t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "DELETE FROM nhacungcap WHERE MaNCC = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaNCC());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<NCC> selectAll() {
        ArrayList<NCC> list = new ArrayList<>();
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT * FROM nhacungcap;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNCC = rs.getString("MaNCC");
                String tenNCC = rs.getString("TenNCC");
                String diaChi = rs.getString("DiaChi");
                String sDT = rs.getString("SDT");
                String email = rs.getString("Email");
                boolean isDelete = rs.getBoolean("is_delete");
                NCC ncc = new NCC(maNCC, tenNCC, diaChi, sDT, email);
                ncc.setIs_delete(isDelete);
                list.add(ncc);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }    
        return list;
    }

    @Override
    public NCC selectById(String id) {
        NCC ncc = null;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT * FROM nhacungcap WHERE MaNCC = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNCC = rs.getString("MaNCC");
                String tenNCC = rs.getString("TenNCC");
                String diaChi = rs.getString("DiaChi");
                String sDT = rs.getString("SDT");
                String email = rs.getString("Email");
                boolean isDelete = rs.getBoolean("is_delete");
                ncc = new NCC(maNCC, tenNCC, diaChi, sDT, email);
                ncc.setIs_delete(isDelete);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ncc;
    }
    
    public NCC selectByTenNCC(String tenNCC) {
        NCC ncc = null;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT * FROM nhacungcap WHERE TenNCC = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenNCC);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNCC = rs.getString("MaNCC");
                String tenNCC1 = rs.getString("TenNCC");
                String diaChi = rs.getString("DiaChi");
                String sDT = rs.getString("SDT");
                String email = rs.getString("Email");
                boolean isDelete = rs.getBoolean("is_delete");
                ncc = new NCC(maNCC, tenNCC1, diaChi, sDT, email);
                ncc.setIs_delete(isDelete);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ncc;
    }

    public ArrayList<NCC> selectByName(String name) {
        ArrayList<NCC> list = new ArrayList<>();
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT * FROM nhacungcap WHERE TenNCC LIKE ? COLLATE utf8mb4_general_ci;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNCC = rs.getString("MaNCC");
                NCC ncc = new NCCDAO().getInstance().selectById(maNCC);
                list.add(ncc);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }    
        return list;
    }

    public NCC selectByNumberPhone(String numberPhone) {
        NCC ncc = null;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT * FROM nhacungcap WHERE SDT = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, numberPhone);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNCC = rs.getString("MaNCC");
                String tenNCC = rs.getString("TenNCC");
                String diaChi = rs.getString("DiaChi");
                String sDT = rs.getString("SDT");
                String email = rs.getString("Email");
                boolean isDelete = rs.getBoolean("is_delete");
                ncc = new NCC(maNCC, tenNCC, diaChi, sDT, email);
                ncc.setIs_delete(isDelete);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ncc;
    }

    public NCC selectByEmailNcc(String email){
        NCC ncc = null;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT * FROM nhacungcap WHERE Email LIKE ? COLLATE utf8mb4_general_ci;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+email+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNCC = rs.getString("MaNCC");
                String tenNCC = rs.getString("TenNCC");
                String diaChi = rs.getString("DiaChi");
                String sDT = rs.getString("SDT");
                String email1 = rs.getString("Email");
                boolean isDelete = rs.getBoolean("is_delete");
                ncc = new NCC(maNCC, tenNCC, diaChi, sDT, email1);
                ncc.setIs_delete(isDelete);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ncc;
    }

    public int softdelete(NCC t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "UPDATE nhacungcap SET is_delete = 1 WHERE MaNCC = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaNCC());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
}
