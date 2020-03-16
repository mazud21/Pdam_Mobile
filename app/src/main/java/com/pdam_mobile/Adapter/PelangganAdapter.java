package com.pdam_mobile.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pdam_mobile.BuildConfig;
import com.pdam_mobile.Model.PelangganModel;
import com.pdam_mobile.R;

import java.util.List;

public class PelangganAdapter extends RecyclerView.Adapter<PelangganAdapter.VH> {
    private List<PelangganModel> pelangganModels;
    private Context context;

    public PelangganAdapter(List<PelangganModel> pelangganModels, Context context) {
        this.pelangganModels = pelangganModels;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.profil_data, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.namaP.setText(pelangganModels.get(i).getNama());

        Glide.with(context)
                .load(BuildConfig.BASE_URL_IMAGE + pelangganModels
                        .get(i)
                        .getFoto_ktp())
                .into(vh.imgP);
    }

    @Override
    public int getItemCount() {
        return pelangganModels.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView namaP;
        ImageView imgP;

        public VH(View view) {
            super(view);
            namaP = view.findViewById(R.id.namaP);

            imgP = view.findViewById(R.id.imgKtp);
        }
    }
}
