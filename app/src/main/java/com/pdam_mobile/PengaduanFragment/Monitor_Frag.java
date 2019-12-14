package com.pdam_mobile.PengaduanFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pdam_mobile.Adapter.MasalahAdapter;
import com.pdam_mobile.Adapter.PengaduanAdapter;
import com.pdam_mobile.InfoMasalah;
import com.pdam_mobile.Local.SharedPrefManager;
import com.pdam_mobile.Model.MasalahModel;
import com.pdam_mobile.Model.PengaduanModel;
import com.pdam_mobile.ModelData.MasalahData;
import com.pdam_mobile.ModelData.PengaduanData;
import com.pdam_mobile.NetworkService.ApiClient;
import com.pdam_mobile.NetworkService.ApiInterface;
import com.pdam_mobile.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Monitor_Frag extends Fragment {

    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static Monitor_Frag mainActivity;

    Button btnLogout;

    TextView tNama;

    SharedPrefManager prefManager;
    Context context;



    public Monitor_Frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monitor_frag, container, false);

        recyclerView = view.findViewById(R.id.rvPengaduanMonitor);
        layoutManager = new LinearLayoutManager(view.getContext());

        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        prefManager = new SharedPrefManager(view.getContext());

        mainActivity = this;

        refresh();

        return view;
    }

    private void refresh() {
        Call<PengaduanData> pengaduanModelCall = apiInterface.pengaduanData();

        pengaduanModelCall.enqueue(new Callback<PengaduanData>() {
            @Override
            public void onResponse(Call<PengaduanData> call, Response<PengaduanData> response) {
                List<PengaduanModel> pengaduanDataList = response.body().getPengaduanDataList();
                adapter = new PengaduanAdapter(pengaduanDataList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PengaduanData> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}
