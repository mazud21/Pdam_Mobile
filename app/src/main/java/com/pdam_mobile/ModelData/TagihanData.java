package com.pdam_mobile.ModelData;

import com.google.gson.annotations.SerializedName;
import com.pdam_mobile.Model.MasalahModel;
import com.pdam_mobile.Model.TagihanModel;

import java.util.List;

public class TagihanData {

    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<TagihanModel> tagihanDataList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TagihanModel> getTagihanDataList() {
        return tagihanDataList;
    }

    public void setTagihanDataList(List<TagihanModel> tagihanDataList) {
        this.tagihanDataList = tagihanDataList;
    }
}

