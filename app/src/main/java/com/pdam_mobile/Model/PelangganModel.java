package com.pdam_mobile.Model;

import com.google.gson.annotations.SerializedName;

public class PelangganModel {

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

    public PelangganModel(String no_ktp, String nama, String alamat, String email, String no_hp, String foto_ktp, String pilih_tarif) {
        this.no_ktp = no_ktp;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.no_hp = no_hp;
        this.foto_ktp = foto_ktp;
        this.pilih_tarif = pilih_tarif;
    }
}
