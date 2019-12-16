package com.pdam_mobile.ModelPost;

import com.google.gson.annotations.SerializedName;
import com.pdam_mobile.Model.PengaduanModel;

public class PengaduanModelPost {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    PengaduanModel pengaduanModel;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PengaduanModel getPengaduanModel() {
        return pengaduanModel;
    }

    public void setPengaduanModel(PengaduanModel pengaduanModel) {
        this.pengaduanModel = pengaduanModel;
    }
}
