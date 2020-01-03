package com.pdam_mobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.pdam_mobile.Local.SharedPrefManager;
import com.pdam_mobile.NetworkService.ApiClient;
import com.pdam_mobile.NetworkService.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PelangganLogin extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty(message = "This field required")
    @Length(min = 8, message = "This field must have at least 8")
    private EditText etPasPel;

    @NotEmpty(message = "This field required")
    private EditText etNoPel;

    CheckBox checkBox;
    Button btnLog, btnReg;
    Context context;
    ProgressDialog pd;

    ApiInterface apiInterface;

    SharedPrefManager prefManager;

    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelanggan_login);

        validator = new Validator(this);
        validator.setValidationListener(this);

        pd = new ProgressDialog(this, R.style.MyAlertDialogStyle);
        pd.setMessage("Loading...");
        context = this;

        etNoPel = findViewById(R.id.noPelanggan);
        etPasPel = findViewById(R.id.passPelanggan);
        btnLog = findViewById(R.id.btnLogin);
        btnReg = findViewById(R.id.btnSignUp);

        checkBox = (CheckBox) findViewById(R.id.checkbox);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        prefManager = new SharedPrefManager(this);

        if (prefManager.getSPSudahLogin()) {
            startActivity(new Intent(PelangganLogin.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    etPasPel.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    etPasPel.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        //method login
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
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

                            //Integer no_pelanggan = jsonObject.getJSONObject("data").getInt("no_pelanggan");
                            String no_pelanggan = etNoPel.getText().toString();
                            String nama = jsonObject.getJSONObject("data").getString("nama");
                            String alamat = jsonObject.getJSONObject("data").getString("alamat");
                            String email = jsonObject.getJSONObject("data").getString("email");

                            //prefManager.saveSPInt(SharedPrefManager.SP_NO_PELANGGAN, no_pelanggan);
                            prefManager.saveSPString(SharedPrefManager.SP_NO_PELANGGAN, no_pelanggan);
                            prefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
                            prefManager.saveSPString(SharedPrefManager.SP_ALAMAT, alamat);
                            prefManager.saveSPString(SharedPrefManager.SP_EMAIL, email);

                            prefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

                            startActivity(new Intent(context, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                            //intent.putExtra("result_nama", nama);
                            //startActivity(intent);
                        } if (jsonObject.getString("status").equals("false")) {
                            pd.dismiss();
                            Toast.makeText(context, "No Pelanggan atau Password salah", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (!response.isSuccessful()){
                    Toast.makeText(PelangganLogin.this, "No Pelanggan atau Password salah", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onValidationSucceeded() {
        requestLogin();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}