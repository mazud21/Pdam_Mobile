package com.pdam_mobile.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pdam_mobile.Model.MasalahModel;
import com.pdam_mobile.Model.PengaduanModel;
import com.pdam_mobile.Pengaduan;
import com.pdam_mobile.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PengaduanAdapter extends RecyclerView.Adapter<PengaduanAdapter.ViewHolder> {
    private List<PengaduanModel> pengaduanDataList;

    public PengaduanAdapter(List<PengaduanModel> pengaduanDataList) {
        this.pengaduanDataList = pengaduanDataList;
    }

    @NonNull
    @Override
    public PengaduanAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.monitor_pengaduan_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull PengaduanAdapter.ViewHolder viewHolder, int i) {
        //viewHolder.mTxtNoInfo.setText(masalahDataList.get(i).getNo_info());
        viewHolder.mTxtNama.setText(pengaduanDataList.get(i).getNama());
        viewHolder.mTxtAlamat.setText(pengaduanDataList.get(i).getAlamat());
        viewHolder.mTxtNoHp.setText(pengaduanDataList.get(i).getNo_hp());
        viewHolder.mTxtKeluhan.setText(pengaduanDataList.get(i).getKeluhan());
        viewHolder.mTxtTanggapan.setText(pengaduanDataList.get(i).getTanggapan());
    }

    @Override
    public int getItemCount() {
        return pengaduanDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtNoInfo, mTxtNama, mTxtAlamat, mTxtNoHp, mTxtKeluhan, mTxtTanggapan ;

        ViewHolder( View itemView) {
            super(itemView);
            //mTxtNoInfo = (TextView) itemView.findViewById(R.id.txtNoInfo);
            mTxtNama = (TextView) itemView.findViewById(R.id.txtNama);
            mTxtAlamat = (TextView) itemView.findViewById(R.id.txtAlamat);
            mTxtNoHp = (TextView) itemView.findViewById(R.id.txtNoHp);
            mTxtKeluhan = (TextView) itemView.findViewById(R.id.isiKeluhan);
            mTxtTanggapan = (TextView) itemView.findViewById(R.id.isiTanggapan);
        }
    }
}