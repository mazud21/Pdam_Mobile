package com.pdam_mobile.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PelangganModel implements Parcelable {

    @SerializedName("no_ktp")
    private String no_ktp;
    @SerializedName("nama")
    private String nama;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("email")
    private String email;
    @SerializedName("no_hp")
    private String no_hp;
    @SerializedName("foto_ktp")
    private String foto_ktp;
    @SerializedName("pilih_tarif")
    private String pilih_tarif;

    public String getNo_ktp() {
        return no_ktp;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getEmail() {
        return email;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public String getFoto_ktp() {
        return foto_ktp;
    }

    public String getPilih_tarif() {
        return pilih_tarif;
    }

    public PelangganModel(String no_ktp, String nama, String alamat, String email, String no_hp, String foto_ktp, String pilih_tarif) {
        this.no_ktp = no_ktp;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.no_hp = no_hp;
        this.foto_ktp = foto_ktp;
        this.pilih_tarif = pilih_tarif;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.no_ktp);
        dest.writeString(this.nama);
        dest.writeString(this.alamat);
        dest.writeString(this.email);
        dest.writeString(this.no_hp);
        dest.writeString(this.foto_ktp);
        dest.writeString(this.pilih_tarif);
    }

    protected PelangganModel(Parcel in) {
        this.no_ktp = in.readString();
        this.nama = in.readString();
        this.alamat = in.readString();
        this.email = in.readString();
        this.no_hp = in.readString();
        this.foto_ktp = in.readString();
        this.pilih_tarif = in.readString();
    }

    public static final Parcelable.Creator<PelangganModel> CREATOR = new Parcelable.Creator<PelangganModel>() {
        @Override
        public PelangganModel createFromParcel(Parcel source) {
            return new PelangganModel(source);
        }

        @Override
        public PelangganModel[] newArray(int size) {
            return new PelangganModel[size];
        }
    };
}
