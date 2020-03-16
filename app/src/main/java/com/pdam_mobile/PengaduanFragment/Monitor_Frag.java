package com.pdam_mobile.PengaduanFragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pdam_mobile.Adapter.PengaduanAdapter;
import com.pdam_mobile.Local.SharedPrefManager;
import com.pdam_mobile.Model.PengaduanModel;
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

    TextView tNoPel, tNama, tAlamat;

    SharedPrefManager prefManager;
    String sNoPel;
    Context context;

    ProgressDialog pd;

    SwipeRefreshLayout refreshLayout;

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
        context = view.getContext();

        mainActivity = this;

        tNoPel = view.findViewById(R.id.noPell);
        sNoPel = prefManager.getSpNoPelanggan();
        tNoPel.setText(sNoPel);

        tNama = view.findViewById(R.id.txtNama);
        tNama.setText(prefManager.getSPNama());

        tAlamat = view.findViewById(R.id.txtAlamat);
        //tAlamat.setText(prefManager.getSpAlamat());
        tAlamat.setText(prefManager.getSpAlamat().substring(9, 50));

        pd = new ProgressDialog(context, R.style.MyAlertDialogStyle);
        pd.setMessage("Loading...");
        pd.show();

        refreshLayout = view.findViewById(R.id.refPengaduan);

        getPengaduan();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPengaduan();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                },2500);
            }
        });

        return view;
    }

    private void getPengaduan() {
        Call<PengaduanData> pengaduanModelCall =
                apiInterface.pengaduanDataId(sNoPel);

        pengaduanModelCall.enqueue(new Callback<PengaduanData>() {
            @Override
            public void onResponse(Call<PengaduanData> call, Response<PengaduanData> response) {
                if (response.isSuccessful()) {
                    pd.dismiss();

                    List<PengaduanModel> pengaduanDataList = response.body().getPengaduanDataList();
                    adapter = new PengaduanAdapter(pengaduanDataList);
                    recyclerView.setAdapter(adapter);
                } else {
                    pd.dismiss();
                    Toast.makeText(context, "Data masih kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PengaduanData> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}
