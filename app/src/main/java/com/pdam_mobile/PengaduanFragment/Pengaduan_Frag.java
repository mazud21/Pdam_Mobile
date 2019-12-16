package com.pdam_mobile.PengaduanFragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pdam_mobile.Local.SharedPrefManager;
import com.pdam_mobile.ModelPost.PengaduanModelPost;
import com.pdam_mobile.NetworkService.ApiClient;
import com.pdam_mobile.NetworkService.ApiInterface;
import com.pdam_mobile.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pengaduan_Frag extends Fragment {

    TextView tNoPel;
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

        tNoPel = view.findViewById(R.id.noPell);
        tNoPel.setText("12");

        eIsiKeluhan = view.findViewById(R.id.isiKeluhan);

        btnKeluhan = view.findViewById(R.id.btnKeluhan);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        pd = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);

        pd.setMessage("Loading...");
        context = view.getContext();

        prefManager = new SharedPrefManager(context);


        //method login
        btnKeluhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pd = ProgressDialog.show(context, null,"Loading...", true, false);
                pd.show();
                requestKeluhan();
            }
        });

        return view;
    }

    private void requestKeluhan() {
        //post keluhan process
        Call<PengaduanModelPost> responseBodyCall =
                apiInterface.postPengaduan(
                        tNoPel.getText().toString(),
                        eIsiKeluhan.getText().toString()
                );
        responseBodyCall.enqueue(new Callback<PengaduanModelPost>() {
            @Override
            public void onResponse(Call<PengaduanModelPost> call, Response<PengaduanModelPost> response) {
                pd.dismiss();
                Toast.makeText(context, "Keluhan Terkirim", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PengaduanModelPost> call, Throwable t) {

            }
        });
    }
}

