package com.pdam_mobile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.pdam_mobile.Local.SharedPrefManager;
import com.pdam_mobile.Model.TarifModel;
import com.pdam_mobile.ModelData.TarifData;
import com.pdam_mobile.ModelPost.PelangganDaftarModel;
import com.pdam_mobile.NetworkService.ApiClient;
import com.pdam_mobile.NetworkService.ApiInterface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pdam_mobile.Local.SharedPrefManager.FIREBASE_NOTIF_TOKEN;

public class PelangganDaftar extends FragmentActivity implements OnMapReadyCallback, Validator.ValidationListener {

    @NotEmpty(message = "This field required")
    @Length(min = 16, max = 16, message = "This field must have at least 16")
    private EditText etNoKtp;
    @NotEmpty(message = "This field required")
    @Length(min = 2, message = "This field must have at least 2")
    private EditText etNama;
    @NotEmpty(message = "This field required")
    @Email
    private EditText etEmail;
    @NotEmpty(message = "This field required")
    private EditText etAlamat;
    @NotEmpty(message = "This field required")
    @Pattern(regex = "^08[0-9]{10,}$", message = "Please enter valid Phone number")
    private EditText etNoHp;
    //@NotEmpty(message = "This field required")
    //public EditText etImg;

    //EditText etNoKtp, etNama, etAlamat, etEmail, etNoHp;
    ImageView imgKtp;
    Button btnDaftar, btnBatal;
    ApiInterface apiInterface;

    GoogleMap mMap;
    Geocoder geo;

    Spinner etTarif;
    Context context;

    String part_image, regId;
    ProgressDialog pd;
    final int REQUEST_GALLERY = 9544;

    SharedPreferences sharedPreferences;

    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelanggan_daftar);

        validator = new Validator(this);
        validator.setValidationListener(this);

        pd = new ProgressDialog(this);
        pd = new ProgressDialog(this, R.style.MyAlertDialogStyle);
        pd.setMessage("loading ... ");

        requestMultiplePermissions();

        etNoKtp = findViewById(R.id.etNoKtp);
        etNama = findViewById(R.id.etNama);
        etAlamat = findViewById(R.id.etAlamat);
        etEmail = findViewById(R.id.etEmail);
        etNoHp = findViewById(R.id.etNoHp);
        imgKtp = findViewById(R.id.imgKtp);
        //etImg = findViewById(R.id.etImg);

        //get regId FCM from sharedpref
        sharedPreferences = getSharedPreferences(SharedPrefManager.SP_PDAM_APP, Activity.MODE_PRIVATE);
        regId = sharedPreferences.getString(FIREBASE_NOTIF_TOKEN, "");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        // pendeklarasian untuk fragment google map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnDaftar = findViewById(R.id.btnDaftar);
        btnBatal = findViewById(R.id.btnBatal);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        etTarif = findViewById(R.id.etTarif);

        context = this;

        //call method initSpinnerTarif()
        initSpinnerTarif();

        //mengarahkan ke file manager
        imgKtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "open gallery"), REQUEST_GALLERY);
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate(true);
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PelangganDaftar.this, PelangganLogin.class);
                startActivity(intent);
                finish();
            }
        });

        //set on selected tarif spinner
        etTarif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedName = adapterView.getItemAtPosition(i).toString();
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLUE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //permission for allowing external storage
    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {

                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_GALLERY) {
                Uri dataimage = data.getData();
                String[] imageprojection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(dataimage, imageprojection, null, null, null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
                    part_image = cursor.getString(indexImage);

                    if (part_image != null) {
                        File image = new File(part_image);
                        imgKtp.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
                        //etImg.setText(BitmapFactory.decodeFile(image.getPath()).toString());
                    }
                }
            }
        }
    }

    private RequestBody createPartFromString(String s) {

        return RequestBody.create(okhttp3.MultipartBody.FORM, s);
    }

    //get map from gmaps api
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng bantul = new LatLng(-7.90233, 110.3255365);
        mMap.addMarker(new MarkerOptions().position(bantul).title("Bantul"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bantul));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bantul, 11.0f));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onMapClick(LatLng latLng) {

                mMap.clear();
            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                try {
                    if (geo == null)
                        geo = new Geocoder(PelangganDaftar.this, Locale.getDefault());

                    List<Address> address = geo.getFromLocation(
                            latLng.latitude,
                            latLng.longitude,
                            1);

                    if (address.size() > 0) {

                        mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title(
                                        address.get(0).getFeatureName()
                                ));

                        etAlamat.setText(
                                "Alamat : " + address.get(0).getAddressLine(0)
                                        + ". Latitude : " + address.get(0).getLatitude()
                                        + ". Longitude : " + address.get(0).getLongitude());
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

                return false;
            }
        });
    }

    //get tarif from api
    private void initSpinnerTarif() {
        apiInterface.getTarifData().enqueue(new Callback<TarifData>() {
            @Override
            public void onResponse(Call<TarifData> call, Response<TarifData> response) {
                if (response.isSuccessful()) {
                    List<TarifModel> tarifModels = response.body().getTarifDataList();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < tarifModels.size(); i++) {
                        listSpinner.add(tarifModels.get(i).getId_tarif_air());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    etTarif.setAdapter(adapter);

                } else {
                    Toast.makeText(context, "Gagal mengambil data tarif", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TarifData> call, Throwable t) {
                Toast.makeText(context, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Form Validation START
    @Override
    public void onValidationSucceeded() {
        try {
            pd.show();
            HashMap<String, RequestBody> map = new HashMap<String, RequestBody>();
            map.put("no_ktp", createPartFromString(etNoKtp.getText().toString()));
            map.put("nama", createPartFromString(etNama.getText().toString()));
            map.put("alamat", createPartFromString(etAlamat.getText().toString()));
            map.put("email", createPartFromString(etEmail.getText().toString()));
            map.put("no_hp", createPartFromString(etNoHp.getText().toString()));
            map.put("pilih_tarif", createPartFromString(etTarif.getSelectedItem().toString()));
            map.put("regId", createPartFromString(regId));

            File imagefile = new File(part_image);
            RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile);
            MultipartBody.Part partImage = MultipartBody.Part.createFormData("foto_ktp", imagefile.getName(), reqBody);

            apiInterface = ApiClient.getApiInterface();
            Call<PelangganDaftarModel> pelangganRegCall = apiInterface.uploadImg(partImage, map);
            pelangganRegCall.enqueue(new Callback<PelangganDaftarModel>() {
                @Override
                public void onResponse(Call<PelangganDaftarModel> call, Response<PelangganDaftarModel> response) {
                    pd.dismiss();
                    Toast.makeText(PelangganDaftar.this, "Data pendaftaran terkirim", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PelangganDaftar.this, PelangganLogin.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<PelangganDaftarModel> call, Throwable t) {
                    pd.dismiss();
                    Toast.makeText(PelangganDaftar.this, "Data pendaftaran gagal terkirim", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            pd.dismiss();
            Toast.makeText(PelangganDaftar.this, "Silakan periksa kembali form pendaftaran", Toast.LENGTH_LONG).show();
        }
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
    //Form Validation END

}