package com.pdam_mobile.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MasalahData {

    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<MasalahModel> masalahDataList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MasalahModel> getMasalahDataList() {
        return masalahDataList;
    }

    public void setMasalahDataList(List<MasalahModel> masalahDataList) {
        this.masalahDataList = masalahDataList;
    }
}

