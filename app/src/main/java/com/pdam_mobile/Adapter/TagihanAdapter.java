package com.pdam_mobile.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pdam_mobile.Model.TagihanModel;
import com.pdam_mobile.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TagihanAdapter extends RecyclerView.Adapter<TagihanAdapter.ViewHolder> {
    private List<TagihanModel> tagihanDataList;

    public TagihanAdapter(List<TagihanModel> tagihanDataList) {
        this.tagihanDataList = tagihanDataList;
    }

    @NonNull
    @Override
    public TagihanAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tagihan_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull TagihanAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mTxtBulanBayar.setText(tagihanDataList.get(i).getBulan_bayar());
        viewHolder.mTxtStdAwal.setText(tagihanDataList.get(i).getStd_awal());
        viewHolder.mTxtStdAkhir.setText(tagihanDataList.get(i).getStd_akhir());
        viewHolder.mTxtBiayaAir.setText(tagihanDataList.get(i).getBiaya_air());
        viewHolder.mTxtDenda.setText(tagihanDataList.get(i).getDenda());
        viewHolder.mTxtBiayaSegel.setText(tagihanDataList.get(i).getBiaya_segel());
        viewHolder.mTxtAngsAir.setText(tagihanDataList.get(i).getAngs_air());
        viewHolder.mTxtAngsNon.setText(tagihanDataList.get(i).getAngs_non_air());
        viewHolder.mTxtTotTag.setText(tagihanDataList.get(i).getTotal_tagihan());
        viewHolder.mTxtStatus.setText(tagihanDataList.get(i).getStatus_bayar());
        viewHolder.mTxtTglBayar.setText(tagihanDataList.get(i).getTgl_bayar());
    }

    @Override
    public int getItemCount() {
        return tagihanDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtBulanBayar, mTxtStdAwal, mTxtStdAkhir, mTxtBiayaAir, mTxtDenda,
                 mTxtBiayaSegel, mTxtAngsAir, mTxtAngsNon, mTxtTotTag,
                 mTxtStatus, mTxtTglBayar;

        ViewHolder( View itemView) {
            super(itemView);
            mTxtBulanBayar = itemView.findViewById(R.id.txtBulanBayar);
            mTxtStdAwal = itemView.findViewById(R.id.stdAwal);
            mTxtStdAkhir = itemView.findViewById(R.id.stdAkhir);
            mTxtBiayaAir = itemView.findViewById(R.id.biayaAir);
            mTxtDenda = itemView.findViewById(R.id.txtDenda);
            mTxtBiayaSegel = itemView.findViewById(R.id.biayaSegel);
            mTxtAngsAir = itemView.findViewById(R.id.angsAir);
            mTxtAngsNon = itemView.findViewById(R.id.angsNon);
            mTxtTotTag = itemView.findViewById(R.id.totTagihan);
            mTxtStatus = itemView.findViewById(R.id.statusBayar);
            mTxtTglBayar = itemView.findViewById(R.id.tglBayar);
        }
    }
}