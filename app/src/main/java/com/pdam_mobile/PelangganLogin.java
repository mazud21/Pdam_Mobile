package com.pdam_mobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pdam_mobile.Local.SharedPrefManager;
import com.pdam_mobile.NetworkService.ApiClient;
import com.pdam_mobile.NetworkService.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PelangganLogin extends AppCompatActivity {

    EditText etNoPel, etPasPel;
    Button btnLog, btnReg;
    Context context;
    ProgressDialog pd;

    ApiInterface apiInterface;

    SharedPrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelanggan_login);

        etNoPel = findViewById(R.id.noPelanggan);
        etPasPel = findViewById(R.id.passPelanggan);
        btnLog = findViewById(R.id.btnLogin);
        btnReg = findViewById(R.id.btnSignUp);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        pd = new ProgressDialog(this, R.style.MyAlertDialogStyle);
        pd.setMessage("Loading...");
        context = this;

        prefManager = new SharedPrefManager(this);

        if (prefManager.getSPSudahLogin()) {
            startActivity(new Intent(PelangganLogin.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        //method login
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pd = ProgressDialog.show(context, null,"Loading...", true, false);
                pd.show();
                requestLogin();
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_register = new Intent(PelangganLogin.this,PelangganDaftar.class);
                startActivity(intent_register);
            }
        });
    }

    private void requestLogin() {
        //login process
        Call<ResponseBody> responseBodyCall =
                apiInterface.loginPelangganData(
                        etNoPel.getText().toString(),
                        etPasPel.getText().toString()
                );
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    pd.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("status").equals("true")) {

                            Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(context, MainActivity.class);
                            String nama = jsonObject.getJSONObject("data").getString("nama");

                            prefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);

                            prefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

                            startActivity(new Intent(context, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                            //intent.putExtra("result_nama", nama);
                            //startActivity(intent);
                        } else if (jsonObject.getString("status").equals("false")){
                            Toast.makeText(context, "Login Gagal", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                pd.dismiss();
            }
        });
    }
}