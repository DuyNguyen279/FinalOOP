/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.dao;

import com.doan.database.JDBCUtil;
import com.doan.model.NCC;
import com.doan.model.NhanVien;
import com.doan.model.PhieuNhap;
import com.doan.model.SanPham;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class PhieuNhapDAO implements DaoInterface<PhieuNhap> {
    public static PhieuNhapDAO getInstance(){
        return new PhieuNhapDAO();
    }

    @Override
    public int insert(PhieuNhap t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try{
            String sql = "INSERT INTO PhieuNhap (MaPhieuNhap, NgayNhap, TongTien, MaNV, MaNCC) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaPhieuNhap());
            ps.setDate(2, t.getNgayNhap());
            ps.setDouble(3, t.getTongTien());
            ps.setString(4, t.getNv().getMaNV());
            ps.setString(5, t.getNcc().getMaNCC());
            ketQua = ps.executeUpdate();
            List<SanPham> list = t.getSP();
            for(int i=0; i<list.size(); i++){
                sql = "INSERT INTO HangNhap(MaPhieuNhap, MaSP, SoLuong, TongTien) VALUES (?, ?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, t.getMaPhieuNhap());
                ps.setString(2, list.get(i).getMaSP());
                ps.setInt(3, list.get(i).getSoLuong());
                ps.setDouble(4, list.get(i).getSoLuong() * list.get(i).getGiaNhap());
                ketQua = ps.executeUpdate();
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int update(PhieuNhap t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(PhieuNhap t) {
        int ketQua = 0;
        Connection conn = JDBCUtil.getJDBCConnection();
        try{
            String sql = "DELETE FROM PhieuNhap WHERE MaPhieuNhap =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaPhieuNhap());
            ketQua = ps.executeUpdate();
            sql = "DELETE FROM HangNhap WHERE MaPhieuNhap =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMaPhieuNhap());
            ketQua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<PhieuNhap> selectAll() {
        ArrayList<PhieuNhap> list = new ArrayList<>();
        Connection conn = JDBCUtil.getJDBCConnection();
        try{
            String sql = "SELECT pn.MaPhieuNhap, pn.NgayNhap, pn.TongTien, ncc.MaNCC, nv.MaNV, sp.MaSP, sp.TenSP, sp.GiaNhap, hn.SoLuong, hn.TongTien AS TongTienSP FROM phieunhap pn INNER JOIN nhacungcap ncc ON pn.MaNCC = ncc.MaNCC INNER JOIN nhanvien nv ON pn.MaNV = nv.MaNV INNER JOIN hangnhap hn ON pn.MaPhieuNhap = hn.MaPhieuNhap INNER JOIN sanpham sp ON hn.MaSP = sp.MaSP ORDER BY pn.MaPhieuNhap;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            String currentMaPN = "";
            PhieuNhap phieuNhap = null;

        while (rs.next()) {
            String maPN = rs.getString("MaPhieuNhap");

            // Nếu là mã phiếu nhập mới -> tạo mới
            if (!maPN.equals(currentMaPN)) {
                currentMaPN = maPN;

                // Tạo nhà cung cấp
                NCC ncc = new NCCDAO().getInstance().selectById(rs.getString("MaNCC"));

                // Tạo nhân viên
                NhanVien nv = new NhanVienDAO().getInstance().selectById(rs.getString("MaNV")); 

                // Tạo phiếu nhập
                phieuNhap = new PhieuNhap();
                phieuNhap.setMaPhieuNhap(maPN);
                phieuNhap.setNcc(ncc);
                phieuNhap.setNv(nv);
                phieuNhap.setNgayNhap(rs.getDate("NgayNhap"));
                phieuNhap.setTongTien(rs.getDouble("TongTien"));
                phieuNhap.setSP(new ArrayList<>());  // Khởi tạo danh sách sản phẩm

                list.add(phieuNhap);
            }

            // Thêm sản phẩm vào phiếu nhập hiện tại
            SanPham sp = new SanPhamDAO().getInstance().selectById(rs.getString("MaSP"));
            sp.setSoLuong(rs.getInt("SoLuong"));
            sp.setGiaNhap(rs.getDouble("GiaNhap"));

            phieuNhap.getSP().add(sp);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public PhieuNhap selectById(String id) {
        PhieuNhap phieuNhap = null;
        Connection conn = JDBCUtil.getJDBCConnection();
        try{
            String sql = "SELECT pn.MaPhieuNhap, pn.NgayNhap, pn.TongTien, ncc.MaNCC, nv.MaNV, sp.MaSP, sp.TenSP, sp.GiaNhap, hn.SoLuong, hn.TongTien AS TongTienSP FROM phieunhap pn INNER JOIN nhacungcap ncc ON pn.MaNCC = ncc.MaNCC INNER JOIN nhanvien nv ON pn.MaNV = nv.MaNV INNER JOIN hangnhap hn ON pn.MaPhieuNhap = hn.MaPhieuNhap INNER JOIN sanpham sp ON hn.MaSP = sp.MaSP WHERE pn.MaPhieuNhap =? ORDER BY pn.MaPhieuNhap;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (phieuNhap == null) {
                    NCC ncc = new NCCDAO().getInstance().selectById(rs.getString("MaNCC"));
                    NhanVien nv = new NhanVienDAO().getInstance().selectById(rs.getString("MaNV"));
                    phieuNhap = new PhieuNhap();
                    phieuNhap.setMaPhieuNhap(id);
                    phieuNhap.setNcc(ncc);
                    phieuNhap.setNv(nv);
                    phieuNhap.setNgayNhap(rs.getDate("NgayNhap"));
                    phieuNhap.setTongTien(rs.getDouble("TongTien"));
                    phieuNhap.setSP(new ArrayList<>());  
                }

                SanPham sp = new SanPhamDAO().getInstance().selectById(rs.getString("MaSP"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGiaNhap(rs.getDouble("GiaNhap"));

                phieuNhap.getSP().add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phieuNhap;
    }

    public ArrayList<PhieuNhap> selectByDate(Date fromDate, Date toDate) {
        ArrayList<PhieuNhap> list = new ArrayList<>();
        Connection conn = JDBCUtil.getJDBCConnection();
        try{
            String sql = "SELECT * FROM PhieuNhap WHERE NgayNhap BETWEEN ? AND ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, fromDate);
            ps.setDate(2, toDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                String grnId = rs.getString("MaPhieuNhap");
                PhieuNhap phieuNhap = new PhieuNhapDAO().getInstance().selectById(grnId);
                list.add(phieuNhap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
