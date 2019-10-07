package com.pdam_mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pdam_mobile.Model.CrudMasalahData;
import com.pdam_mobile.NetworkService.ApiClient;
import com.pdam_mobile.NetworkService.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMasalah extends AppCompatActivity {
    EditText etWilayah, etHari, etTanggal, etEstimasi, etKerusakan, etAlternatif, etPenanganan;
    Button btnTambahMasalah, btnBack;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_masalah);

        etWilayah = (EditText) findViewById(R.id.etWilayah);
        etHari = (EditText) findViewById(R.id.etHari);
        etTanggal = (EditText) findViewById(R.id.etTanggal);
        etEstimasi = (EditText) findViewById(R.id.etEstimasi);
        etKerusakan = (EditText) findViewById(R.id.etKerusakan);
        etAlternatif = (EditText) findViewById(R.id.etAlternatif);
        etPenanganan = (EditText) findViewById(R.id.etPenanganan);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        btnTambahMasalah = (Button) findViewById(R.id.btnTambahMasalah);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnTambahMasalah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<CrudMasalahData> crudMasalahDataCall = apiInterface
                        .crudMasalahData(
                                etWilayah.getText().toString(),
                                etHari.getText().toString(),
                                etTanggal.getText().toString(),
                                etEstimasi.getText().toString(),
                                etKerusakan.getText().toString(),
                                etAlternatif.getText().toString(),
                                etPenanganan.getText().toString()
                        );

                crudMasalahDataCall.enqueue(new Callback<CrudMasalahData>() {
                    @Override
                    public void onResponse(Call<CrudMasalahData> call, Response<CrudMasalahData> response) {
                        MainActivity.mainActivity.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<CrudMasalahData> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mainActivity.refresh();
                finish();
            }
        });

    }
}