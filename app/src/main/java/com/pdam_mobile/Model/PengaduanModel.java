package com.pdam_mobile.Model;

import com.google.gson.annotations.SerializedName;

public class PengaduanModel {
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
    @SerializedName("id_pengaduan")
    private String id_pengaduan;
    @SerializedName("keluhan")
    private String keluhan;
    @SerializedName("tanggapan")
    private String tanggapan;

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

    public String getId_pengaduan() {
        return id_pengaduan;
    }

    public void setId_pengaduan(String id_pengaduan) {
        this.id_pengaduan = id_pengaduan;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getTanggapan() {
        return tanggapan;
    }

    public void setTanggapan(String tanggapan) {
        this.tanggapan = tanggapan;
    }

    public PengaduanModel(String no_pelanggan, String nama, String alamat, String no_hp, String id_pengaduan, String keluhan, String tanggapan) {
        this.no_pelanggan = no_pelanggan;
        this.nama = nama;
        this.alamat = alamat;
        this.no_hp = no_hp;
        this.id_pengaduan = id_pengaduan;
        this.keluhan = keluhan;
        this.tanggapan = tanggapan;
    }
}
