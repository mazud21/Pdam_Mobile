package com.pdam_mobile.Model;

import com.google.gson.annotations.SerializedName;

public class PengaduanModel {

    @SerializedName("id_pengaduan")
    private String id_pengaduan;
    @SerializedName("keluhan")
    private String keluhan;
    @SerializedName("tanggapan")
    private String tanggapan;

    public String getKeluhan() {
        return keluhan;
    }

    public String getTanggapan() {
        return tanggapan;
    }

    public PengaduanModel(String id_pengaduan, String keluhan, String tanggapan) {
        this.id_pengaduan = id_pengaduan;
        this.keluhan = keluhan;
        this.tanggapan = tanggapan;
    }
}
