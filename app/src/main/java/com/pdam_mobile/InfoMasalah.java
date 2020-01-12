package com.pdam_mobile;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pdam_mobile.Adapter.MasalahAdapter;
import com.pdam_mobile.Local.SharedPrefManager;
import com.pdam_mobile.Model.MasalahModel;
import com.pdam_mobile.ModelData.MasalahData;
import com.pdam_mobile.NetworkService.ApiClient;
import com.pdam_mobile.NetworkService.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoMasalah extends AppCompatActivity {

    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static InfoMasalah mainActivity;

    Button btnLogout;

    TextView tNama;

    SharedPrefManager prefManager;
    Context context;

    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_masalah);

        setTitle("Informasi Gangguan Air");

        recyclerView = (RecyclerView) findViewById(R.id.rvMasalah);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        prefManager = new SharedPrefManager(this);

        mainActivity = this;
        context = this;

        refreshLayout = findViewById(R.id.refMasalah);

        getMasalah();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMasalah();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }

                },2500);
            }
        });

    }

    public void getMasalah() {
        Call<MasalahData> masalahModelCall = apiInterface.masalahData();

        masalahModelCall.enqueue(new Callback<MasalahData>() {
            @Override
            public void onResponse(Call<MasalahData> call, Response<MasalahData> response) {
                List<MasalahModel> masalahDataList = response.body().getMasalahDataList();
                adapter = new MasalahAdapter(masalahDataList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MasalahData> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}
