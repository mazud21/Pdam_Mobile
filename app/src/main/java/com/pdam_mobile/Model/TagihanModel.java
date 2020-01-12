package com.pdam_mobile.Model;

import com.google.gson.annotations.SerializedName;

public class TagihanModel {
    /*
    @SerializedName("no_info")
    private String no_info;
    */
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


    public String getBulan_bayar() {
        return bulan_bayar;
    }

    public String getStd_awal() {
        return std_awal;
    }

    public String getStd_akhir() {
        return std_akhir;
    }

    public String getBiaya_air() {
        return biaya_air;
    }

    public String getDenda() {
        return denda;
    }

    public String getBiaya_segel() {
        return biaya_segel;
    }

    public String getAngs_air() {
        return angs_air;
    }

    public String getAngs_non_air() {
        return angs_non_air;
    }

    public String getTotal_tagihan() {
        return total_tagihan;
    }

    public String getStatus_bayar() {
        return status_bayar;
    }

    public String getTgl_bayar() {
        return tgl_bayar;
    }

    public TagihanModel(String bulan_bayar, String std_awal, String std_akhir, String biaya_air, String denda, String biaya_segel, String angs_air, String angs_non_air, String total_tagihan, String status_bayar, String tgl_bayar) {
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
