package com.pdam_mobile.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MasalahModel {

    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<Masalah> masalahList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Masalah> getMasalahList() {
        return masalahList;
    }

    public void setMasalahList(List<Masalah> masalahList) {
        this.masalahList = masalahList;
    }
}

