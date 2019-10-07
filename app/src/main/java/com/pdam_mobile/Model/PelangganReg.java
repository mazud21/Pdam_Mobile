package com.pdam_mobile.Model;

import com.google.gson.annotations.SerializedName;

public class PelangganReg {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    PelangganModel pelangganModel;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PelangganModel getPelangganModel() {
        return pelangganModel;
    }

    public void setPelangganModel(PelangganModel pelangganModel) {
        this.pelangganModel = pelangganModel;
    }
}
