package com.pdam_mobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.pdam_mobile.Model.PelangganData;
import com.pdam_mobile.Model.PelangganReg;
import com.pdam_mobile.NetworkService.ApiClient;
import com.pdam_mobile.NetworkService.ApiInterface;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PelangganDaftar extends FragmentActivity implements OnMapReadyCallback {

    EditText etNoKtp, etNama, etAlamat, etEmail, etNoHp, etFotoKtp, etTarif;
    Button btnDaftar;
    ApiInterface apiInterface;

    GoogleMap mMap;
    Geocoder geo;

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

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnDaftar = (Button) findViewById(R.id.btnDaftar);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PelangganReg> pelangganRegCall =
                        apiInterface.pelangganReg(
                                etNoKtp.getText().toString(),
                                etNama.getText().toString(),
                                etAlamat.getText().toString(),
                                etEmail.getText().toString(),
                                etNoHp.getText().toString(),
                                etFotoKtp.getText().toString(),
                                etTarif.getText().toString()
                                );

                pelangganRegCall.enqueue(new Callback<PelangganReg>() {
                    @Override
                    public void onResponse(Call<PelangganReg> call, Response<PelangganReg> response) {
                        Call<PelangganData> pelangganDataCall = apiInterface.pelangganData();

                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Pendaftaran Telah Terkirim", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(PelangganDaftar.this, MainActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<PelangganReg> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng bantul = new LatLng(-7.90233, 110.3255365);
        mMap.addMarker(new MarkerOptions().position(bantul).title("Bantul"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bantul));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onMapClick(LatLng latLng) {
                try {
                    if (geo == null)
                        geo = new Geocoder(PelangganDaftar.this, Locale.getDefault());
                    List<Address> address = geo.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    if (address.size() > 0) {
                        mMap.addMarker(new MarkerOptions().position(latLng).title(
                                address.get(0).getLatitude()
                                +""+address.get(0).getLongitude()));
                        etAlamat.setText("Lat:" + address.get(0).getLatitude()
                                + ". Long:" + address.get(0).getLongitude());
                    }
                } catch (IOException ex) {
                    Toast.makeText(PelangganDaftar.this, "Error:" + ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onMarkerClick(Marker marker) {

                etAlamat.setText(marker.getTitle().toString() + " Lat:" + marker.getPosition().latitude + " Long:" + marker.getPosition().longitude);
                return false;
            }
        });
    }
}
