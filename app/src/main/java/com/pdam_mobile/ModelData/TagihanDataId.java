package com.pdam_mobile.ModelData;

import com.google.gson.annotations.SerializedName;
import com.pdam_mobile.Model.TagihanModel;

import java.util.List;

public class TagihanDataId {

    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<TagihanModel> tagihanDataList;

    public List<TagihanModel> getTagihanDataList() {
        return tagihanDataList;
    }

}

