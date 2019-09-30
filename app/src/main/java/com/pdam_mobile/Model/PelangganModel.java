package com.pdam_mobile.Model;

import com.google.gson.annotations.SerializedName;

public class PelangganModel {
    @SerializedName("no_daftar")
    private String no_daftar;
    @SerializedName("no_ktp")
    private String no_ktp;
    @SerializedName("nama")
    private String nama;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("email")
    private String email;
    @SerializedName("no_hp")
    private String no_hp;
    @SerializedName("foto_ktp")
    private String foto_ktp;
    @SerializedName("pilih_tarif")
    private String pilih_tarif;

    public PelangganModel(String no_daftar, String no_ktp, String nama, String alamat, String email, String no_hp, String foto_ktp, String pilih_tarif) {
        this.no_daftar = no_daftar;
        this.no_ktp = no_ktp;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.no_hp = no_hp;
        this.foto_ktp = foto_ktp;
        this.pilih_tarif = pilih_tarif;
    }

    public String getNo_daftar() {
        return no_daftar;
    }

    public void setNo_daftar(String no_daftar) {
        this.no_daftar = no_daftar;
    }

    public String getNo_ktp() {
        return no_ktp;
    }

    public void setNo_ktp(String no_ktp) {
        this.no_ktp = no_ktp;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getFoto_ktp() {
        return foto_ktp;
    }

    public void setFoto_ktp(String foto_ktp) {
        this.foto_ktp = foto_ktp;
    }

    public String getPilih_tarif() {
        return pilih_tarif;
    }

    public void setPilih_tarif(String pilih_tarif) {
        this.pilih_tarif = pilih_tarif;
    }
}
