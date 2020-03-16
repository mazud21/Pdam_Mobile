package com.pdam_mobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pdam_mobile.Local.SharedPrefManager;

public class StartActivity extends AppCompatActivity {

    Button btnLog, btnReg;

    SharedPrefManager prefManager;

    ProgressDialog pd;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnLog = findViewById(R.id.btnLogin);
        btnReg = findViewById(R.id.btnSignUp);

        pd = new ProgressDialog(this, R.style.MyAlertDialogStyle);
        pd.setMessage("Loading...");
        context = this;

        prefManager = new SharedPrefManager(this);

        if (prefManager.getSPSudahLogin()) {
            startActivity(new Intent(StartActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                Intent intent_login = new Intent(StartActivity.this,PelangganLogin.class);
                startActivity(intent_login);
                finish();
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    pd.show();
                    Intent intent_register = new Intent(StartActivity.this,PelangganDaftar.class);
                    startActivity(intent_register);
                finish();
            }
        });
    }
}
