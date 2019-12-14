package com.pdam_mobile.ModelData;

import com.google.gson.annotations.SerializedName;
import com.pdam_mobile.Model.MasalahModel;
import com.pdam_mobile.Model.PengaduanModel;

import java.util.List;

public class PengaduanData {

    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<PengaduanModel> pengaduanDataList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PengaduanModel> getPengaduanDataList() {
        return pengaduanDataList;
    }

    public void setPengaduanDataList(List<PengaduanModel> pengaduanDataList) {
        this.pengaduanDataList = pengaduanDataList;
    }
}

