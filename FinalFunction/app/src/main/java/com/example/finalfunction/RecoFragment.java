package com.example.finalfunction;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecoFragment extends Fragment {
    List<Fragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    private ImageView iv_qr_code;
    public RecoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reco, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        iv_qr_code =getActivity().findViewById(R.id.imageView);
        Bitmap bitmap = ZXingUtils.createQRCodeBitmap("www.hznu.edu.cn", 200, 200,"UTF-8","H", "1", Color.BLACK, Color.WHITE);
        Log.d("msg",""+bitmap.getHeight());
        iv_qr_code.setImageBitmap(bitmap);
        TabLayout mTablayout=getActivity().findViewById(R.id.recotab);
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
        ViewPager viewpager=getActivity().findViewById(R.id.vp_reco);
        fragments.clear();
        titles.clear();
        fragments.add(new SubAFragment());
        fragments.add(new SubBFragment());
        fragments.add(new SubCFragment());
        titles.add("天气晴朗");
        titles.add("天气阴凉");
        titles.add("天气下雨");
        viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                super.destroyItem(container, position, object);
            }
        });
//        viewpager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
        mTablayout.setupWithViewPager(viewpager);
        super.onViewCreated(view, savedInstanceState);
    }
}
