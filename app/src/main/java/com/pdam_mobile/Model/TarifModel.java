package com.pdam_mobile.Model;

import com.google.gson.annotations.SerializedName;

public class TarifModel {
    @SerializedName("id_tarif_air")
    private String id_tarif_air;

    public TarifModel(String id_tarif_air) {
        this.id_tarif_air = id_tarif_air;
    }

    public String getId_tarif_air() {
        return id_tarif_air;
    }

}
