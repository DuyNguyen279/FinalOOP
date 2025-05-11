/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.dao;

import com.doan.database.JDBCUtil;
import com.doan.model.BaoCao;
import com.doan.model.NhanVien;
import com.doan.model.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class BaoCaoDAO implements DaoInterface<BaoCao> {
    public static BaoCaoDAO getInstance() {
        return new BaoCaoDAO();
    }

    @Override
    public int insert(BaoCao t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "INSERT INTO BaoCao (MaBaoCao, NgayLap, TongDoanhthu, MaNV) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaBaoCao());
            ps.setDate(2, t.getNgayLap());
            ps.setDouble(3, t.getTongDoanhThu());
            ps.setString(4, t.getNVLap().getMaNV());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(BaoCao t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(BaoCao t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "DELETE FROM BaoCao WHERE MaBaoCao =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaBaoCao());
            ketQua = ps.executeUpdate();
            sql = "DELETE FROM sphuhong WHERE MaBaoCao =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaBaoCao());
            ketQua = ps.executeUpdate();
            sql = "DELETE FROM spbanchay WHERE MaBaoCao =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaBaoCao());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<BaoCao> selectAll() {
        ArrayList<BaoCao> list = new ArrayList<>();
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT bc.MaBaoCao, bc.NgayLap, bc.TongDoanhThu, nv.MaNV, spbc.MaSP AS MaSP_BanChay, sphh.MaSP AS MaSP_HuHong, sphh.SoLuong AS SoLuongHuHong, sphh.ThietHai AS ThietHaiHuHong FROM baocao bc INNER JOIN nhanvien nv ON bc.MaNV = nv.MaNV LEFT JOIN spbanchay spbc ON bc.MaBaoCao = spbc.MaBaoCao LEFT JOIN sanpham spbc_info ON spbc.MaSP = spbc_info.MaSP LEFT JOIN sphuhong sphh ON bc.MaBaoCao = sphh.MaBaoCao LEFT JOIN sanpham sphh_info ON sphh.MaSP = sphh_info.MaSP ORDER BY bc.MaBaoCao;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            Map<String, BaoCao> baoCaoMap = new LinkedHashMap<>(); // Dùng map để gộp dữ liệu báo cáo bị lặp khi join

        while (rs.next()) {
            String maBaoCao = rs.getString("MaBaoCao");

            BaoCao baoCao = baoCaoMap.get(maBaoCao);
            if (baoCao == null) {
                baoCao = new BaoCao();
                baoCao.setMaBaoCao(maBaoCao);
                baoCao.setNgayLap(rs.getDate("NgayLap"));
                baoCao.setTongDoanhThu(rs.getDouble("TongDoanhThu"));

                NhanVien nv = new NhanVienDAO().getInstance().selectById(rs.getString("MaNV"));
                baoCao.setNVLap(nv);

                baoCao.setSanPhamXuHuong(new ArrayList<SanPham>());
                baoCao.setSanPhamHuHong(new ArrayList<SanPham>());

                baoCaoMap.put(maBaoCao, baoCao);
            }

            // Thêm sản phẩm bán chạy nếu có
            String maSPBanChay = rs.getString("MaSP_BanChay");
            if (maSPBanChay != null) {
                SanPham spBanChay = new SanPhamDAO().getInstance().selectById(maSPBanChay);
                baoCao.getSanPhamXuHuong().add(spBanChay);
            }

            // Thêm sản phẩm hư hỏng nếu có
            String maSPHuHong = rs.getString("MaSP_HuHong");
            if (maSPHuHong != null) {
                SanPham spHuHong = new SanPhamDAO().getInstance().selectById(maSPHuHong);
                spHuHong.setSoLuong(rs.getInt("SoLuongHuHong"));
                spHuHong.setGiaNY(rs.getDouble("ThietHaiHuHong")); // Giả sử dùng giá này để lưu thiệt hại
                baoCao.getSanPhamHuHong().add(spHuHong);
            }
        }

        list.addAll(baoCaoMap.values());

            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public BaoCao selectById(String id) {
        BaoCao baoCao = null;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT bc.MaBaoCao, bc.NgayLap, bc.TongDoanhThu, nv.MaNV, nv.TenNV, spbc.MaSP AS MaSP_BanChay, spbc_info.TenSP AS TenSP_BanChay,spbc.SoLuongBan, sphh.MaSP AS MaSP_HuHong, sphh_info.TenSP AS TenSP_HuHong, sphh.SoLuong AS SoLuongHuHong, sphh.ThietHai AS ThietHaiHuHong FROM baocao bc INNER JOIN nhanvien nv ON bc.MaNV = nv.MaNV LEFT JOIN spbanchay spbc ON bc.MaBaoCao = spbc.MaBaoCao LEFT JOIN sanpham spbc_info ON spbc.MaSP = spbc_info.MaSP LEFT JOIN sphuhong sphh ON bc.MaBaoCao = sphh.MaBaoCao LEFT JOIN sanpham sphh_info ON sphh.MaSP = sphh_info.MaSP WHERE bc.MaBaoCao = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (baoCao == null) {
                    baoCao = new BaoCao();
                    baoCao.setMaBaoCao(rs.getString("MaBaoCao"));
                    baoCao.setNgayLap(rs.getDate("NgayLap"));
                    baoCao.setTongDoanhThu(rs.getDouble("TongDoanhThu"));
                    NhanVien nv = new NhanVienDAO().getInstance().selectById(rs.getString("MaNV"));
                    baoCao.setNVLap(nv);
                    baoCao.setSanPhamXuHuong(new ArrayList<>());
                    baoCao.setSanPhamHuHong(new ArrayList<>());
                }

                String maSPBanChay = rs.getString("MaSP_BanChay");
                if (maSPBanChay != null) {
                    SanPham spBanChay = new SanPhamDAO().getInstance().selectById(maSPBanChay);
                    spBanChay.setSoLuong(rs.getInt("SoLuongBan"));
                    baoCao.getSanPhamXuHuong().add(spBanChay);
                }

                // Thêm sản phẩm hư hỏng nếu có
                String maSPHuHong = rs.getString("MaSP_HuHong");
                if (maSPHuHong != null) {
                    SanPham spHuHong = new SanPhamDAO().getInstance().selectById(maSPHuHong);
                    spHuHong.setSoLuong(rs.getInt("SoLuongHuHong"));
                    //spHuHong.setGiaNY(rs.getDouble("ThietHaiHuHong")); // Giả sử dùng giá này để lưu thiệt hại
                    baoCao.getSanPhamHuHong().add(spHuHong);
                }
            }
        JDBCUtil.closeConnection(conn);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return baoCao;
    }
    
}
