package com.pdam_mobile.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pdam_mobile.Model.PengaduanModel;
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
        viewHolder.mTxtKeluhan.setText(pengaduanDataList.get(i).getKeluhan());
        viewHolder.mTxtTanggapan.setText(pengaduanDataList.get(i).getTanggapan());
    }

    @Override
    public int getItemCount() {
        return pengaduanDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtKeluhan, mTxtTanggapan ;

        ViewHolder( View itemView) {
            super(itemView);
            mTxtKeluhan = (TextView) itemView.findViewById(R.id.isiKeluhan);
            mTxtTanggapan = (TextView) itemView.findViewById(R.id.isiTanggapan);
        }
    }
}