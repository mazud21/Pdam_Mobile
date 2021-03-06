package com.pdam_mobile.PengaduanFragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pdam_mobile.Local.SharedPrefManager;
import com.pdam_mobile.NetworkService.ApiClient;
import com.pdam_mobile.NetworkService.ApiInterface;
import com.pdam_mobile.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pengaduan_Frag extends Fragment {

    TextView tNoPel, tNama, tAlamat;
    EditText eIsiKeluhan;
    Button btnKeluhan;

    Context context;
    ProgressDialog pd;

    ApiInterface apiInterface;

    SharedPrefManager prefManager;

    public Pengaduan_Frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengaduan_frag, container, false);

        context = view.getContext();
        prefManager = new SharedPrefManager(context);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        tNoPel = view.findViewById(R.id.noPell);
        tNoPel.setText(prefManager.getSpNoPelanggan());

        tNama = view.findViewById(R.id.txtNama);
        tNama.setText(prefManager.getSPNama());

        tAlamat = view.findViewById(R.id.txtAlamat);
        //tAlamat.setText(prefManager.getSpAlamat());
        tAlamat.setText(prefManager.getSpAlamat().substring(9, 50));

        eIsiKeluhan = view.findViewById(R.id.isiKeluhan);

        btnKeluhan = view.findViewById(R.id.btnKeluhan);

        pd = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
        pd.setMessage("Loading...");

        //method login
        btnKeluhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pd = ProgressDialog.show(context, null,"Loading...", true, false);
                pd.show();
                sendPengaduan();
            }
        });

        return view;
    }

    private void sendPengaduan() {
        //post keluhan process
        Call<ResponseBody> responseBodyCall =
                apiInterface.postPengaduan(
                        tNoPel.getText().toString(),
                        eIsiKeluhan.getText().toString()
                );
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                pd.dismiss();
                Toast.makeText(context, "Keluhan Terkirim", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}