package com.kizias.thuchanh_android;

import java.util.Objects;

public class SanPham {
    private int masp;
    private String tensp;
    private int soluong;
    private double dongia;

    public SanPham(int masp, String tensp, int soluong, double dongia) {
        this.masp = masp;
        this.tensp = tensp;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public SanPham() {

    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        if (masp > 0)
            this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SanPham sanPham = (SanPham) o;
        return masp == sanPham.masp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(masp, tensp, soluong, dongia);
    }
}
