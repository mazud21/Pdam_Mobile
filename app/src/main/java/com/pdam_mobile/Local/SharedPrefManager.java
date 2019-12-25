package com.pdam_mobile.Local;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String SP_PDAM_APP = "spPdamApp";

    public static final String SP_NAMA = "spNama";
    public static final String SP_NO_PELANGGAN = "spNoPelanggan";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_ALAMAT = "spAlamat";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    public final static String FIREBASE_NOTIF_TOKEN = "firebase_notif_token";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_PDAM_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, Integer value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSpNoPelanggan() {
        return sp.getString(SP_NO_PELANGGAN, "");
    }

    public String getFirebaseNotifToken() {
        return sp.getString(FIREBASE_NOTIF_TOKEN, "");
    }

    /*
    public Integer getSpNoPelanggan() {
        return sp.getInt(SP_NO_PELANGGAN, 0);
    }

     */

    public String getSPEmail(){
        return sp.getString(SP_EMAIL, "");
    }

    public String getSpAlamat(){
        return sp.getString(SP_ALAMAT, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
