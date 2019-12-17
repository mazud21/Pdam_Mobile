package com.pdam_mobile.NetworkService;

import com.pdam_mobile.ModelData.TagihanDataId;
import com.pdam_mobile.ModelPost.PengaduanModelPost;
import com.pdam_mobile.ModelData.MasalahData;
import com.pdam_mobile.ModelData.PelangganData;
import com.pdam_mobile.ModelPost.PelangganDaftarModel;
import com.pdam_mobile.ModelData.PengaduanData;
import com.pdam_mobile.ModelData.TarifData;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("Pdam_masalah")
    Call<MasalahData> masalahData();

    @FormUrlEncoded
    @POST("Pdam_pelangganLogin")
    Call<ResponseBody> loginPelangganData(@Field("no_pelanggan") String no_pelanggan,
                                          @Field("password") String password);

    @GET("Pdam_pelanggan")
    Call<PelangganData> pelangganData();

    @GET("Pdam_tagihan")
    Call<TagihanDataId> tagihanDataId(@Query("no_pelanggan") String no_pelanggan);

    @GET("Pdam_aduan")
    Call<PengaduanData> pengaduanDataId(@Query("no_pelanggan") String no_pelanggan);

    @FormUrlEncoded
    @POST("Pdam_aduan")
    Call<PengaduanModelPost> postPengaduan(@Field("no_pelanggan") String no_pelanggan,
                                           @Field("keluhan") String keluhan);

    @Multipart
    @POST("Pdam_pelanggan")
    Call<PelangganDaftarModel> uploadImg (@Part MultipartBody.Part image,
                                          @PartMap Map<String, RequestBody> text);

    @GET("Pdam_tarif")
    Call<TarifData> getTarifData();
}