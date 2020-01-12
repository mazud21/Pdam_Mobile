package com.pdam_mobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.pdam_mobile.Adapter.TagihanAdapter;
import com.pdam_mobile.Local.SharedPrefManager;
import com.pdam_mobile.Model.TagihanModel;
import com.pdam_mobile.ModelData.TagihanDataId;
import com.pdam_mobile.NetworkService.ApiClient;
import com.pdam_mobile.NetworkService.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTagihan extends AppCompatActivity {

    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static DetailTagihan detailTagihan;

    TextView tNoPel, tNama, tAlamat;

    SharedPrefManager prefManager;
    String sNoPel;
    Context context;

    ProgressDialog pd;

    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tagihan);

        setTitle("Tagihan Air");

        recyclerView = (RecyclerView) findViewById(R.id.rvTagihan);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        prefManager = new SharedPrefManager(this);

        refreshLayout = findViewById(R.id.refTagihan);

        tNoPel = findViewById(R.id.noPell);
        sNoPel = prefManager.getSpNoPelanggan();
        tNoPel.setText(sNoPel);

        tNama = findViewById(R.id.txtNama);
        tNama.setText(prefManager.getSPNama());

        tAlamat = findViewById(R.id.txtAlamat);
        tAlamat.setText(prefManager.getSpAlamat());

        detailTagihan = this;
        context = this;

        pd = new ProgressDialog(this, R.style.MyAlertDialogStyle);
        pd.setMessage("Loading...");
        pd.show();

        getTagihan();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTagihan();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                },2500);
            }
        });

    }

    public void getTagihan() {
        Call<TagihanDataId> tagihanModelCall =
                apiInterface.tagihanDataId(sNoPel);

        tagihanModelCall.enqueue(new Callback<TagihanDataId>() {
            @Override
            public void onResponse(Call<TagihanDataId> call, Response<TagihanDataId> response) {
                if (response.isSuccessful()) {
                    pd.dismiss();

                    List<TagihanModel> tagihanModelList = response.body().getTagihanDataList();
                    adapter = new TagihanAdapter(tagihanModelList);
                    recyclerView.setAdapter(adapter);

                } else {
                    Toast.makeText(context, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TagihanDataId> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
