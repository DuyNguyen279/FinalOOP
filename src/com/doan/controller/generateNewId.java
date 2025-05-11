/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.controller;

import com.doan.dao.BaoCaoDAO;
import com.doan.dao.HoaDonDAO;
import com.doan.dao.NCCDAO;
import com.doan.dao.NhanVienDAO;
import com.doan.dao.PhieuNhapDAO;
import com.doan.dao.SanPhamDAO;
import com.doan.dao.TaiKhoanDAO;
import com.doan.model.BaoCao;
import com.doan.model.HoaDon;
import com.doan.model.NCC;
import com.doan.model.NhanVien;
import com.doan.model.PhieuNhap;
import com.doan.model.SanPham;
import com.doan.model.TaiKhoan;
import java.util.List;

/**
 *
 * @author Asus
 */
public class generateNewId {
    public static generateNewId getInstance(){
        return new generateNewId();
    }

    public String generateNewIdItem(){
        List<SanPham> all = SanPhamDAO.getInstance().selectAll();
        int i = all.size() ;
        int check = 1;
        while (check == 1){
            i++;
            for(SanPham sp : all){
                if (sp.getMaSP().equals("SP" + i)){
                    check = 0;
                }
            }
            if (check == 0){
                check = 1;
            } else {
                return "SP" + i;
            }
        }
        return null;
    }

    public String generateNewIdBill(){
        List<HoaDon> all = new HoaDonDAO().getInstance().selectAll();
        int i = all.size() ;
        int check = 1;
        while (check == 1){
            i++;
            for(HoaDon hd : all){
                if (hd.getMaHD().equals("HD" + i)){
                    check = 0;
                }
            }
            if (check == 0){
                check = 1;
            } else {
                return "HD" + i;
            }
        }
        return null;
    }

    public String generateNewIdGrnBill(){
        List<PhieuNhap> all = new PhieuNhapDAO().getInstance().selectAll();
        int i = all.size() ;
        int check = 1;
        while (check == 1){
            i++;
            for(PhieuNhap x : all){
                if (x.getMaPhieuNhap().equals("PN" + i)){
                    check = 0;
                }
            }
            if (check == 0){
                check = 1;
            } else {
                return "PN" + i;
            }
        }
        return null;
    }

    public String generateNewIdSupplier(){
        List<NCC> all = new NCCDAO().getInstance().selectAll();
        int i = all.size() ;
        int check = 1;
        while (check == 1){
            i++;
            for(NCC ncc : all){
                if (ncc.getMaNCC().equals("NCC" + i)){
                    check = 0;
                }
            }
            if (check == 0){
                check = 1;
            } else {
                return "NCC" + i;
            }
        }
        return null;
    }

    public String generateNewIdEmployee(){
        List<NhanVien> all = new NhanVienDAO().getInstance().selectAll();
        int i = all.size() ;
        int check = 1;
        while (check == 1){
            i++;
            for(NhanVien x : all){
                if (x.getMaNV().equals("NV" + i)){
                    check = 0;
                }
            }
            if (check == 0){
                check = 1;
            } else {
                return "NV" + i;
            }
        }
        return null;
    }

    public String generateNewIdAccount(){
        List<TaiKhoan> all = new TaiKhoanDAO().getInstance().selectAll();
        int i = all.size() ;
        int check = 1;
        while (check == 1){
            i++;
            for(TaiKhoan x : all){
                if (x.getMaTK().equals("TK" + i)){
                    check = 0;
                }
            }
            if (check == 0){
                check = 1;
            } else {
                return "TK" + i;
            }
        }
        return null;
    }

    public String generateNewIdReport(){
        List<BaoCao> all = new BaoCaoDAO().getInstance().selectAll();
        int i = all.size() ;
        int check = 1;
        while (check == 1){
            i++;
            for(BaoCao x : all){
                if (x.getMaBaoCao().equals("BC" + i)){
                    check = 0;
                }
            }
            if (check == 0){
                check = 1;
            } else {
                return "BC" + i;
            }
        }
        return null;
    }
}
