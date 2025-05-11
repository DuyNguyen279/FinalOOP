/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.dao;

import com.doan.database.JDBCUtil;
import com.doan.model.HoaDon;
import com.doan.model.NhanVien;
import com.doan.model.SanPham;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class HoaDonDAO implements DaoInterface<HoaDon> {
    public static HoaDonDAO getInstance() {
        return new HoaDonDAO();
    }

    @Override
    public int insert(HoaDon t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "INSERT INTO HoaDon (MaHD, NgayLap, TongTien, MaNV) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaHD());
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(t.getNgayLap()));
            ps.setDouble(3, t.getTongTien());
            ps.setString(4, t.getNVLap().getMaNV());
            sql = "INSERT INTO HANGXUAT (MaHD, MaSP, SoLuongBan, TongTien) VALUES (?, ?, ?, ?)";
            ketQua = ps.executeUpdate();
            List<SanPham> list = t.getSP();
            for(int i = 0; i < list.size(); i++) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, t.getMaHD());
                ps.setString(2, list.get(i).getMaSP());
                ps.setInt(3, list.get(i).getSoLuong());
                ps.setDouble(4, list.get(i).getSoLuong() * list.get(i).getGiaNY());
                ketQua = ps.executeUpdate();
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(HoaDon t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(HoaDon t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "DELETE FROM HoaDon WHERE MaHD = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaHD());
            ketQua = ps.executeUpdate();
            sql = "DELETE FROM HANGXUAT WHERE MaHD =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaHD());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<HoaDon> selectAll() {
        ArrayList<HoaDon> ketQua = new ArrayList<HoaDon>();
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT hd.MaHD, hd.NgayLap, hd.TongTien AS TongTienHD, nv.MaNV, nv.TenNV, nv.Tuoi, nv.GioiTinh, nv.DiaChi, nv.SDT, nv.Luong, sp.MaSP, sp.TenSP, sp.GiaNY, sp.GiaNhap, sp.NSX, sp.Loai, cthd.SoLuongBan, cthd.TongTien AS ThanhTienSP FROM HoaDon hd JOIN NhanVien nv ON hd.MaNV = nv.MaNV JOIN HangXuat cthd ON hd.MaHD = cthd.MaHD JOIN SanPham sp ON cthd.MaSP = sp.MaSP ORDER BY hd.MaHD;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            Map<String, HoaDon> hoaDonMap = new HashMap<>();

            while (rs.next()) {
                String maHD = rs.getString("MaHD");
                Timestamp ngayLap = rs.getTimestamp("NgayLap");
                double tongTienHD = rs.getDouble("TongTienHD");

                // Thông tin nhân viên
                String maNV = rs.getString("MaNV");
                String tenNV = rs.getString("TenNV");
                int tuoi = rs.getInt("Tuoi");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String sDT = rs.getString("SDT");
                double luong = rs.getDouble("Luong");

                // Thông tin sản phẩm
                String maSP = rs.getString("MaSP");
                String tenSP = rs.getString("TenSP");
                double giaNY = rs.getDouble("GiaNY");
                double giaNhap = rs.getDouble("GiaNhap");
                Date nsx = rs.getDate("NSX");
                String loai = rs.getString("Loai");

                int soLuongBan = rs.getInt("SoLuongBan");
                double thanhTienSP = rs.getDouble("ThanhTienSP");

                // Nếu hóa đơn chưa tồn tại thì tạo mới
                HoaDon hoaDon = hoaDonMap.get(maHD);
                if (hoaDon == null) {
                    NhanVien nv = new NhanVien(maNV, tenNV, tuoi, gioiTinh, diaChi, sDT, luong);
                    hoaDon = new HoaDon(maHD, nv, ngayLap.toLocalDateTime(), new ArrayList<>(), tongTienHD);
                    hoaDon.setTongTien(tongTienHD);
                    hoaDonMap.put(maHD, hoaDon);
                }

                // Tạo sản phẩm và thêm vào list
                SanPham sp = new SanPham(maSP, tenSP, giaNY, giaNhap, soLuongBan, nsx, nsx, loai);
                hoaDon.getSP().add(sp);
            }
            // Cuối cùng lấy ra list kết quả
            List<HoaDon> danhSachHoaDon = new ArrayList<>(hoaDonMap.values());
            ketQua.addAll(danhSachHoaDon);
            JDBCUtil.closeConnection(conn);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        return ketQua;
    }

    @Override
    public HoaDon selectById(String id) {
        HoaDon ketQua = null;
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT hd.MaHD, hd.NgayLap, hd.TongTien AS TongTienHD, nv.MaNV, nv.TenNV, nv.Tuoi, nv.GioiTinh, nv.DiaChi, nv.SDT, nv.Luong, sp.MaSP, sp.TenSP, sp.GiaNY, sp.GiaNhap, sp.NSX, sp.Loai, cthd.SoLuongBan, cthd.TongTien AS ThanhTienSP FROM HoaDon hd JOIN NhanVien nv ON hd.MaNV = nv.MaNV JOIN HangXuat cthd ON hd.MaHD = cthd.MaHD JOIN SanPham sp ON cthd.MaSP = sp.MaSP WHERE hd.MaHD = ? ORDER BY hd.MaHD;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString("MaHD");
                Timestamp ngayLap = rs.getTimestamp("NgayLap");
                double tongTienHD = rs.getDouble("TongTienHD");

                // Thông tin nhân viên
                String maNV = rs.getString("MaNV");                
                String tenNV = rs.getString("TenNV");
                int tuoi = rs.getInt("Tuoi");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String sDT = rs.getString("SDT");
                double luong = rs.getDouble("Luong");                

                // Thông tin sản phẩm
                String maSP = rs.getString("MaSP");
                String tenSP = rs.getString("TenSP");
                double giaNY = rs.getDouble("GiaNY");
                double giaNhap = rs.getDouble("GiaNhap");                
                Date nsx = rs.getDate("NSX");
                String loai = rs.getString("Loai");

                int soLuongBan = rs.getInt("SoLuongBan");
                double thanhTienSP = rs.getDouble("ThanhTienSP");

                // Nếu hóa đơn chưa tồn tại thì tạo mới hoaDon   
                if (ketQua == null) {
                    NhanVien nv = new NhanVien(maNV, tenNV, tuoi, gioiTinh, diaChi, sDT, luong);
                    ketQua = new HoaDon(maHD, nv, ngayLap.toLocalDateTime(), new ArrayList<>(), tongTienHD);
                    ketQua.setTongTien(tongTienHD);
                }            
                ketQua.getSP().add(new SanPham(maSP, tenSP, giaNY, giaNhap, soLuongBan, nsx, nsx, loai));
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<HoaDon> selectByDate(Date date){
        ArrayList<HoaDon> ketQua = null;
        String txtDate = String.valueOf(date);
        Connection conn = JDBCUtil.getJDBCConnection();
        try {
            String sql = "SELECT * FROM HoaDon WHERE NgayLap LIKE ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + txtDate + "%");
            ResultSet rs = ps.executeQuery();
            ketQua = new ArrayList<>();
            while (rs.next()) {
                String maHD = rs.getString("MaHD");
                HoaDon hoaDon = new HoaDonDAO().getInstance().selectById(maHD);
                ketQua.add(hoaDon);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
        return ketQua;
    }

    
    
}
