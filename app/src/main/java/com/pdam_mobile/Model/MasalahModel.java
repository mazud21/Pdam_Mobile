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

    public String getKerusakan() {
        return kerusakan;
    }

    public String getAlternatif() {
        return alternatif;
    }

    public String getPenanganan() {
        return penanganan;
    }


    public MasalahModel(String no_info, String wilayah, String hari, String tanggal, String estimasi, String kerusakan, String alternatif, String penanganan) {
        //this.no_info = no_info;
        this.wilayah = wilayah;
        this.hari = hari;
        this.tanggal = tanggal;
        this.estimasi = estimasi;
        this.kerusakan = kerusakan;
        this.alternatif = alternatif;
        this.penanganan = penanganan;
    }

    public String getWilayah() {
        return wilayah;
    }

}
