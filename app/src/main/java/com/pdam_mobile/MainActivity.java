package com.pdam_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.pdam_mobile.Adapter.MasalahAdapter;
import com.pdam_mobile.Model.MasalahModel;
import com.pdam_mobile.Model.MasalahData;
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

    Button btnAdd;

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

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this,));
            }
        });
    }

    public void refresh() {
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
