package com.pdam_mobile.ModelData;

import com.google.gson.annotations.SerializedName;
import com.pdam_mobile.Model.TarifModel;

import java.util.List;

public class TarifData {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<TarifModel> tarifDataList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TarifModel> getTarifDataList() {
        return tarifDataList;
    }

    public void setTarifDataList(List<TarifModel> tarifDataList) {
        this.tarifDataList = tarifDataList;
    }
}
