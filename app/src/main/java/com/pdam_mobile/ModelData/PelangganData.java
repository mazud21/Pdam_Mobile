package com.pdam_mobile.ModelData;

import com.google.gson.annotations.SerializedName;
import com.pdam_mobile.Model.PelangganModel;

import java.util.List;

public class PelangganData {

    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<PelangganModel> pelangganModelList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PelangganModel> getPelangganModelList() {
        return pelangganModelList;
    }

    public void setPelangganModelList(List<PelangganModel> pelangganModelList) {
        this.pelangganModelList = pelangganModelList;
    }
}
