package com.pdam_mobile.NetworkService;

import com.pdam_mobile.Model.CrudMasalahData;
import com.pdam_mobile.Model.MasalahData;
import com.pdam_mobile.Model.PelangganData;
import com.pdam_mobile.Model.PelangganReg;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("Pdam_masalah")
    Call<MasalahData> masalahData();

    @FormUrlEncoded
    @POST("Pdam_masalah")
    Call<CrudMasalahData> crudMasalahData(@Field("wilayah") String wilayah,
                                          @Field("hari") String hari,
                                          @Field("tanggal") String tanggal,
                                          @Field("estimasi") String estimasi,
                                          @Field("kerusakan") String kerusakan,
                                          @Field("alternatif") String alternatif,
                                          @Field("penanganan") String penanganan
    );

    @GET("Pdam_pelanggan")
    Call<PelangganData> pelangganData();

    @FormUrlEncoded
    @POST("Pdam_pelanggan")
    Call<PelangganReg> pelangganReg(@Field("no_ktp") String no_ktp,
                                    @Field("nama") String nama,
                                    @Field("alamat") String alamat,
                                    @Field("email") String email,
                                    @Field("no_hp") String no_hp,
                                    @Field("foto_ktp") String foto_ktp,
                                    @Field("pilih_tarif") String pilih_tarif
    );

}