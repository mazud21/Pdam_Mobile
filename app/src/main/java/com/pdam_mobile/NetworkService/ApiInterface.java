package com.pdam_mobile.NetworkService;

import com.pdam_mobile.ModelData.CrudMasalahData;
import com.pdam_mobile.ModelData.MasalahData;
import com.pdam_mobile.ModelData.PelangganData;
import com.pdam_mobile.Model.PelangganDaftarModel;
import com.pdam_mobile.ModelData.TagihanData;
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

public interface ApiInterface {
    @GET("Pdam_masalah")
    Call<MasalahData> masalahData();

    @FormUrlEncoded
    @POST("Pdam_pelangganLogin")
    Call<ResponseBody> loginPelangganData(@Field("no_pelanggan") String no_pelanggan,
                                          @Field("password") String password);

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

    @GET("Pdam_tagihan")
    Call<TagihanData> tagihanData();

    /*
    @FormUrlEncoded
    @POST("Pdam_pelanggan")
    Call<PelangganDaftarModel> pelangganReg(@Field("no_ktp") String no_ktp,
                                    @Field("nama") String nama,
                                    @Field("alamat") String alamat,
                                    @Field("email") String email,
                                    @Field("no_hp") String no_hp,
                                    @Field("foto_ktp") String foto_ktp,
                                    @Field("pilih_tarif") String pilih_tarif
    );

     */

    @Multipart
    @POST("Pdam_pelanggan")
    Call<PelangganDaftarModel> uploadImg (@Part MultipartBody.Part image,
                                          @PartMap Map<String, RequestBody> text);

    @GET("Pdam_tarif")
    Call<TarifData> getTarifData();
}