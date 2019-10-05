package com.pdam_mobile.Model;

import com.google.gson.annotations.SerializedName;

public class CrudMasalahData {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    MasalahModel masalahModel;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MasalahModel getMasalahModel() {
        return masalahModel;
    }

    public void setMasalahModel(MasalahModel masalahModel) {
        this.masalahModel = masalahModel;
    }
}
