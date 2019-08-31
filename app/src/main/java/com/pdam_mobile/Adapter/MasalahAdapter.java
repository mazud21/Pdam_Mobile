package com.pdam_mobile.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pdam_mobile.Model.Masalah;
import com.pdam_mobile.Model.MasalahModel;
import com.pdam_mobile.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MasalahAdapter extends RecyclerView.Adapter<MasalahAdapter.ViewHolder> {
    private List<Masalah> masalahList;

    public MasalahAdapter(List<Masalah> masalahList) {
        this.masalahList = masalahList;
    }

    @NonNull
    @Override
    public MasalahAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.masalah_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull MasalahAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mTxtNoInfo.setText(masalahList.get(i).getNo_info());
        viewHolder.mTxtWilayah.setText(masalahList.get(i).getWilayah());
        viewHolder.mTxtHari.setText(masalahList.get(i).getHari());
        viewHolder.mTxtTanggal.setText(masalahList.get(i).getTanggal());
        viewHolder.mTxtEstimasi.setText(masalahList.get(i).getEstimasi());
        viewHolder.mTxtKerusakan.setText(masalahList.get(i).getKerusakan());
        viewHolder.mTxtAlternatif.setText(masalahList.get(i).getAlternatif());
        viewHolder.mTxtPenanganan.setText(masalahList.get(i).getPenanganan());
    }

    @Override
    public int getItemCount() {
        return masalahList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtNoInfo, mTxtWilayah, mTxtHari, mTxtTanggal, mTxtEstimasi, mTxtKerusakan, mTxtAlternatif, mTxtPenanganan;

        ViewHolder( View itemView) {
            super(itemView);
            mTxtNoInfo = (TextView) itemView.findViewById(R.id.txtNoInfo);
            mTxtWilayah = (TextView) itemView.findViewById(R.id.txtWilayah);
            mTxtHari = (TextView) itemView.findViewById(R.id.txtHari);
            mTxtTanggal = (TextView) itemView.findViewById(R.id.txtTanggal);
            mTxtEstimasi = (TextView) itemView.findViewById(R.id.txtEstimasi);
            mTxtKerusakan = (TextView) itemView.findViewById(R.id.txtKerusakan);
            mTxtAlternatif = (TextView) itemView.findViewById(R.id.txtAlternatif);
            mTxtPenanganan = (TextView) itemView.findViewById(R.id.txtPenanganan);
        }
    }
}