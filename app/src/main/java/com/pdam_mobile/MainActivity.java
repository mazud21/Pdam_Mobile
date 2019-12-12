package com.pdam_mobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pdam_mobile.Local.SharedPrefManager;
import com.pdam_mobile.NetworkService.ApiInterface;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    Button btnLogout;

    TextView tNama;

    SharedPrefManager prefManager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefManager = new SharedPrefManager(this);

        tNama = findViewById(R.id.txtNama);
        tNama.setText(prefManager.getSPNama());

        context = this;

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);

                startActivity(new Intent(context, PelangganLogin.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        LinearLayout llDetail = (LinearLayout) findViewById(R.id.lldetail);
        LinearLayout llPengaduan = (LinearLayout) findViewById(R.id.llpengaduan);
        LinearLayout llInfo = (LinearLayout) findViewById(R.id.llinfo);

        llDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_detail = new Intent(MainActivity.this,DetailTagihan.class);
                startActivity(intent_detail);
            }
        });

        llPengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_pengaduan = new Intent(MainActivity.this,Pengaduan.class);
                startActivity(intent_pengaduan);
            }
        });

        llInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_info = new Intent(MainActivity.this, InfoMasalah.class);
                startActivity(intent_info);
            }
        });
    }
}