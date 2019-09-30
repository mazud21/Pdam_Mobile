package com.pdam_mobile.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pdam_mobile.Model.MasalahModel;
import com.pdam_mobile.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MasalahAdapter extends RecyclerView.Adapter<MasalahAdapter.ViewHolder> {
    private List<MasalahModel> masalahDataList;

    public MasalahAdapter(List<MasalahModel> masalahDataList) {
        this.masalahDataList = masalahDataList;
    }

    @NonNull
    @Override
    public MasalahAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.masalah_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull MasalahAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mTxtNoInfo.setText(masalahDataList.get(i).getNo_info());
        viewHolder.mTxtWilayah.setText(masalahDataList.get(i).getWilayah());
        viewHolder.mTxtHari.setText(masalahDataList.get(i).getHari());
        viewHolder.mTxtTanggal.setText(masalahDataList.get(i).getTanggal());
        viewHolder.mTxtEstimasi.setText(masalahDataList.get(i).getEstimasi());
        viewHolder.mTxtKerusakan.setText(masalahDataList.get(i).getKerusakan());
        viewHolder.mTxtAlternatif.setText(masalahDataList.get(i).getAlternatif());
        viewHolder.mTxtPenanganan.setText(masalahDataList.get(i).getPenanganan());
    }

    @Override
    public int getItemCount() {
        return masalahDataList.size();
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