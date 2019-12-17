package com.pdam_mobile.Model;

import com.google.gson.annotations.SerializedName;

public class TagihanModel {
    /*
    @SerializedName("no_info")
    private String no_info;
    */
    @SerializedName("no_pelanggan")
    private String no_pelanggan;
    @SerializedName("nama")
    private String nama;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("no_hp")
    private String no_hp;
    @SerializedName("bulan_bayar")
    private String bulan_bayar;
    @SerializedName("std_awal")
    private String std_awal;
    @SerializedName("std_akhir")
    private String std_akhir;
    @SerializedName("biaya_air")
    private String biaya_air;
    @SerializedName("denda")
    private String denda;
    @SerializedName("biaya_segel")
    private String biaya_segel;
    @SerializedName("angs_air")
    private String angs_air;
    @SerializedName("angs_non_air")
    private String angs_non_air;
    @SerializedName("total_tagihan")
    private String total_tagihan;
    @SerializedName("status_bayar")
    private String status_bayar;
    @SerializedName("tgl_bayar")
    private String tgl_bayar;

    public String getNo_pelanggan() {
        return no_pelanggan;
    }

    public void setNo_pelanggan(String no_pelanggan) {
        this.no_pelanggan = no_pelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getBulan_bayar() {
        return bulan_bayar;
    }

    public void setBulan_bayar(String bulan_bayar) {
        this.bulan_bayar = bulan_bayar;
    }

    public String getStd_awal() {
        return std_awal;
    }

    public void setStd_awal(String std_awal) {
        this.std_awal = std_awal;
    }

    public String getStd_akhir() {
        return std_akhir;
    }

    public void setStd_akhir(String std_akhir) {
        this.std_akhir = std_akhir;
    }

    public String getBiaya_air() {
        return biaya_air;
    }

    public void setBiaya_air(String biaya_air) {
        this.biaya_air = biaya_air;
    }

    public String getDenda() {
        return denda;
    }

    public void setDenda(String denda) {
        this.denda = denda;
    }

    public String getBiaya_segel() {
        return biaya_segel;
    }

    public void setBiaya_segel(String biaya_segel) {
        this.biaya_segel = biaya_segel;
    }

    public String getAngs_air() {
        return angs_air;
    }

    public void setAngs_air(String angs_air) {
        this.angs_air = angs_air;
    }

    public String getAngs_non_air() {
        return angs_non_air;
    }

    public void setAngs_non_air(String angs_non_air) {
        this.angs_non_air = angs_non_air;
    }

    public String getTotal_tagihan() {
        return total_tagihan;
    }

    public void setTotal_tagihan(String total_tagihan) {
        this.total_tagihan = total_tagihan;
    }

    public String getStatus_bayar() {
        return status_bayar;
    }

    public void setStatus_bayar(String status_bayar) {
        this.status_bayar = status_bayar;
    }

    public String getTgl_bayar() {
        return tgl_bayar;
    }

    public void setTgl_bayar(String tgl_bayar) {
        this.tgl_bayar = tgl_bayar;
    }

    public TagihanModel(String no_pelanggan, String nama, String alamat, String no_hp, String bulan_bayar, String std_awal, String std_akhir, String biaya_air, String denda, String biaya_segel, String angs_air, String angs_non_air, String total_tagihan, String status_bayar, String tgl_bayar) {
        this.no_pelanggan = no_pelanggan;
        this.nama = nama;
        this.alamat = alamat;
        this.no_hp = no_hp;
        this.bulan_bayar = bulan_bayar;
        this.std_awal = std_awal;
        this.std_akhir = std_akhir;
        this.biaya_air = biaya_air;
        this.denda = denda;
        this.biaya_segel = biaya_segel;
        this.angs_air = angs_air;
        this.angs_non_air = angs_non_air;
        this.total_tagihan = total_tagihan;
        this.status_bayar = status_bayar;
        this.tgl_bayar = tgl_bayar;
    }
}
