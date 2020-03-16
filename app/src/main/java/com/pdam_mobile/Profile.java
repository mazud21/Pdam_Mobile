package com.pdam_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.pdam_mobile.Adapter.PelangganAdapter;
import com.pdam_mobile.Adapter.TagihanAdapter;
import com.pdam_mobile.Local.SharedPrefManager;
import com.pdam_mobile.Model.PelangganModel;
import com.pdam_mobile.Model.TagihanModel;
import com.pdam_mobile.ModelData.PelangganData;
import com.pdam_mobile.NetworkService.ApiClient;
import com.pdam_mobile.NetworkService.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {

    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static DetailTagihan detailTagihan;

    TextView tNoPel, tNama, tAlamat;

    SharedPrefManager prefManager;
    String sNoPel;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        recyclerView = (RecyclerView) findViewById(R.id.rvProfile);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        prefManager = new SharedPrefManager(this);

        sNoPel = prefManager.getSpNoPelanggan();

        context = this;

        Call<PelangganData> pellDataCall = apiInterface.pelangganData(sNoPel);

        pellDataCall.enqueue(new Callback<PelangganData>() {
            @Override
            public void onResponse(Call<PelangganData> call, Response<PelangganData> response) {
                List<PelangganModel> pelangganModelList = response.body().getPelangganModelList();
                adapter = new PelangganAdapter(pelangganModelList, context);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PelangganData> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
