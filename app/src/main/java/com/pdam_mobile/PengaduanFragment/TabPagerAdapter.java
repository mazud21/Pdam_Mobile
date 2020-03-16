package com.pdam_mobile.PengaduanFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public TabPagerAdapter(FragmentManager fm, int numberOftabs) {
        super(fm);
        this.tabCount = numberOftabs;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                return new Pengaduan_Frag();
            case 1:
                return new Monitor_Frag();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
