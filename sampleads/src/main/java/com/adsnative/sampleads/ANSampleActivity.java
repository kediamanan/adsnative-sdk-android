package com.adsnative.sampleads;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ANSampleActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentactivity_main);

        DefaultFragment defaultFragment = new DefaultFragment();
        loadFragment(defaultFragment, false, false);

        Button btnListView = (Button) findViewById(R.id.btn_list_view);
        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListViewFragment listViewFragment = new ListViewFragment();
                loadFragment(listViewFragment, true, true);
            }
        });

        Button btnRecyclerView = (Button) findViewById(R.id.btn_recycler_view);
        btnRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
                loadFragment(recyclerViewFragment, true, true);
            }
        });

        Button btnDFPRecyclerView = (Button) findViewById(R.id.btn_dfp_recycler_view);
        btnDFPRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DFPRecyclerView recyclerViewFragment = new DFPRecyclerView();
                loadFragment(recyclerViewFragment, true, true);
            }
        });

        Button btnNativeAd = (Button) findViewById(R.id.btn_native_ad);
        btnNativeAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NativeAdFragment nativeAdFragment = new NativeAdFragment();
                EditText placementId = (EditText) findViewById(R.id.placement_id);
                nativeAdFragment.setAdUnitId(placementId.getText().toString());
                loadFragment(nativeAdFragment, true, true);
            }
        });
        Button btnDFPNativeAd = (Button) findViewById(R.id.btn_dfp_native_ad);
        btnDFPNativeAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DFPNativeAd nativeAdFragment = new DFPNativeAd();
                EditText placementId = (EditText) findViewById(R.id.placement_id);
                nativeAdFragment.setAdUnitId(placementId.getText().toString());
                loadFragment(nativeAdFragment, true, true);
            }
        });
//
//        Button btnBannerAd = (Button) findViewById(R.id.btn_banner_ad);
//        btnBannerAd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BannerAdFragment bannerAdFragment = new BannerAdFragment();
//                EditText placementId = (EditText) findViewById(R.id.placement_id);
//                bannerAdFragment.setAdUnitId(placementId.getText().toString());
//                loadFragment(bannerAdFragment, true, true);
//            }
//        });
//        Button btnDFPBannerAd = (Button) findViewById(R.id.btn_dfp_banner_ad);
//        btnDFPBannerAd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DFPBannerAd bannerAdFragment = new DFPBannerAd();
//                EditText placementId = (EditText) findViewById(R.id.placement_id);
//                bannerAdFragment.setAdUnitId(placementId.getText().toString());
//                loadFragment(bannerAdFragment, true, true);
//            }
//        });
    }

    private void loadFragment(Fragment adFragment, boolean replace, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (replace) {
            fragmentTransaction.replace(R.id.fragment_container, adFragment);
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null);
            }
        } else {
            fragmentTransaction.add(R.id.fragment_container, adFragment);
        }
        fragmentTransaction.commit();
    }
}
