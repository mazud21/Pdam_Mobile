package com.pdam_mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.pdam_mobile.Adapter.MasalahAdapter;
import com.pdam_mobile.Model.Masalah;
import com.pdam_mobile.Model.MasalahModel;
import com.pdam_mobile.NetworkService.ApiClient;
import com.pdam_mobile.NetworkService.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rvMasalah);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        mainActivity = this;

        refresh();
    }

    private void refresh() {
        Call<MasalahModel> masalahModelCall = apiInterface.masalahModel();
        masalahModelCall.enqueue(new Callback<MasalahModel>() {
            @Override
            public void onResponse(Call<MasalahModel> call, Response<MasalahModel> response) {
                List<Masalah> masalahList = response.body().getMasalahList();
                adapter = new MasalahAdapter(masalahList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MasalahModel> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
