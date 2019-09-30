package com.pdam_mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pdam_mobile.Model.PelangganData;
import com.pdam_mobile.NetworkService.ApiClient;
import com.pdam_mobile.NetworkService.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PelangganDaftar extends AppCompatActivity {

    EditText etNoKtp, etNama, etAlamat, etEmail, etNoHp, etFotoKtp, etTarif;
    Button btnDaftar;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelanggan_daftar);

        etNoKtp = (EditText) findViewById(R.id.etNoKtp);
        etNama = (EditText) findViewById(R.id.etNama);
        etAlamat = (EditText) findViewById(R.id.etAlamat);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etNoHp = (EditText) findViewById(R.id.etNoHp);
        etFotoKtp = (EditText) findViewById(R.id.etFotoKtp);
        etTarif = (EditText) findViewById(R.id.etTarif);

        btnDaftar = (Button) findViewById(R.id.btnDaftar);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PelangganData> pelangganDataCall =
                        apiInterface.pelangganData(
                                etNoKtp.getText().toString(),
                                etNama.getText().toString(),
                                etAlamat.getText().toString(),
                                etEmail.getText().toString(),
                                etNoHp.getText().toString(),
                                etFotoKtp.getText().toString(),
                                etTarif.getText().toString()
                                );

                pelangganDataCall.enqueue(new Callback<PelangganData>() {
                    @Override
                    public void onResponse(Call<PelangganData> call, Response<PelangganData> response) {
                        MainActivity.mainActivity.refresh();
                        finish();

                    }

                    @Override
                    public void onFailure(Call<PelangganData> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });


    }
}
