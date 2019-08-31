package com.pdam_mobile.NetworkService;

import com.pdam_mobile.Model.MasalahModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("Pdam_masalah")
    Call<MasalahModel> masalahModel();
}