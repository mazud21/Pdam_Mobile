package com.pdam_mobile.PengaduanFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pdam_mobile.Local.SharedPrefManager;
import com.pdam_mobile.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pengaduan_Frag extends Fragment {

    TextView tNama;
    SharedPrefManager prefManager;

    public Pengaduan_Frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengaduan_frag, container, false);

        prefManager = new SharedPrefManager(view.getContext());

        tNama = view.findViewById(R.id.txtNama);
        tNama.setText(prefManager.getSPNama());

        return view;
    }

}
