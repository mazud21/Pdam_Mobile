package com.pdam_mobile.Model;

import com.google.gson.annotations.SerializedName;

public class MasalahModel {
    /*
    @SerializedName("no_info")
    private String no_info;
    */
    @SerializedName("wilayah")
    private String wilayah;
    @SerializedName("hari")
    private String hari;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("estimasi")
    private String estimasi;
    @SerializedName("est_mulai")
    private String est_mulai;
    @SerializedName("est_selesai")
    private String est_selesai;
    @SerializedName("kerusakan")
    private String kerusakan;
    @SerializedName("alternatif")
    private String alternatif;
    @SerializedName("penanganan")
    private String penanganan;

    public String getHari() {
        return hari;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getEstimasi() {
        return estimasi;
    }

    public String getEst_mulai() {
        return est_mulai;
    }

    public String getEst_selesai() {
        return est_selesai;
    }

    public String getKerusakan() {
        return kerusakan;
    }

    public String getAlternatif() {
        return alternatif;
    }

    public String getPenanganan() {
        return penanganan;
    }

    public MasalahModel(String wilayah, String hari, String tanggal, String estimasi, String est_mulai, String est_selesai, String kerusakan, String alternatif, String penanganan) {
        this.wilayah = wilayah;
        this.hari = hari;
        this.tanggal = tanggal;
        this.estimasi = estimasi;
        this.est_mulai = est_mulai;
        this.est_selesai = est_selesai;
        this.kerusakan = kerusakan;
        this.alternatif = alternatif;
        this.penanganan = penanganan;
    }

    public String getWilayah() {
        return wilayah;
    }

}
