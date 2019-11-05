package com.pdam_mobile.Model;

import com.google.gson.annotations.SerializedName;

public class TarifModel {
    @SerializedName("id_tarif_air")
    private String id_tarif_air;
    @SerializedName("tarif")
    private String tarif;
    @SerializedName("ket_tarif")
    private String ket_tarif;

    public TarifModel(String id_tarif_air, String tarif, String ket_tarif) {
        this.id_tarif_air = id_tarif_air;
        this.tarif = tarif;
        this.ket_tarif = ket_tarif;
    }

    public String getId_tarif_air() {
        return id_tarif_air;
    }

    public void setId_tarif_air(String id_tarif_air) {
        this.id_tarif_air = id_tarif_air;
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public String getKet_tarif() {
        return ket_tarif;
    }

    public void setKet_tarif(String ket_tarif) {
        this.ket_tarif = ket_tarif;
    }
}
