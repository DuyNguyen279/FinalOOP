/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doan.controller;

import com.doan.dao.HoaDonDAO;
import com.doan.dao.NCCDAO;
import com.doan.dao.NhanVienDAO;
import com.doan.dao.PhieuNhapDAO;
import com.doan.dao.SanPhamDAO;
import com.doan.model.HoaDon;
import com.doan.model.NCC;
import com.doan.model.NhanVien;
import com.doan.model.PhieuNhap;
import com.doan.model.SanPham;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class search {
    public static search getInstance(){
        return new search();
    }

    public ArrayList<SanPham> searchItem(String key){
        ArrayList<SanPham> list = new SanPhamDAO().getInstance().selectByName(key);
        return list;
    }

    public ArrayList<HoaDon> searchBill(Date date){
        ArrayList<HoaDon> list = new HoaDonDAO().getInstance().selectByDate(date);
        return list;
    }

    public NCC searchNCCById(String id){
        NCC ncc = new NCCDAO().getInstance().selectById(id);
        return ncc;
    }

    public ArrayList<NCC> searchNCCByName(String name){
        ArrayList<NCC> list = new NCCDAO().getInstance().selectByName(name);
        return list;
    }

    public NCC searchNCCByNumberPhone(String numberPhone){
        NCC ncc = new NCCDAO().getInstance().selectByNumberPhone(numberPhone);
        return ncc;
    }

    public NCC searchNCCByEmailNcc(String emailNcc){
        NCC ncc = new NCCDAO().getInstance().selectByEmailNcc(emailNcc);
        return ncc;
    }

    public ArrayList<PhieuNhap> searchGrn (Date fromDate, Date toDate){
        ArrayList<PhieuNhap> list = new PhieuNhapDAO().getInstance().selectByDate(fromDate, toDate);
        return list;
    }

    public NhanVien searchEmployeeById(String id){
        NhanVien nv = new NhanVienDAO().getInstance().selectById(id);
        return nv;
    }

    public ArrayList<NhanVien> searchEmployeeByName(String name){
        ArrayList<NhanVien> list = new NhanVienDAO().getInstance().selectByName(name);
        return list;
    }

}
