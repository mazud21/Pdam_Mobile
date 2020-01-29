package com.pdam_mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.pdam_mobile.Local.SharedPrefManager;

import static com.pdam_mobile.Local.SharedPrefManager.FIREBASE_NOTIF_TOKEN;

public class MainActivity extends AppCompatActivity {

    TextView tNama;

    SharedPrefManager prefManager;
    private SharedPreferences sharedPreferences;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Dashboard Pelanggan");

        prefManager = new SharedPrefManager(this);

        sharedPreferences = getSharedPreferences(SharedPrefManager.SP_PDAM_APP, Activity.MODE_PRIVATE);

        FirebaseMessaging.getInstance().subscribeToTopic("infomasalah");
        Log.d("FIREBASE_NOTIF_TOKEN", sharedPreferences.getString(FIREBASE_NOTIF_TOKEN, ""));
        //Log.d("token", "onClick: " + FirebaseInstanceId.getInstance().getToken());

        tNama = findViewById(R.id.txtNama);
        tNama.setText("Selamat Datang "+prefManager.getSPNama());

        context = this;

        LinearLayout llDetail = findViewById(R.id.lldetail);
        LinearLayout llPengaduan = findViewById(R.id.llpengaduan);
        LinearLayout llInfo = findViewById(R.id.llinfo);
        LinearLayout llKontak = findViewById(R.id.llKontak);

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

        llKontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_info = new Intent(MainActivity.this, Kontak.class);
                startActivity(intent_info);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.logout) {
            prefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);

            startActivity(new Intent(context, StartActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        return true;
    }
}