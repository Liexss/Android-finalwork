package com.example.finalfunction;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    List<Fragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavigationView navigationView=getActivity().findViewById(R.id.navi);
        final DrawerLayout daraw=getActivity().findViewById(R.id.daraw);
        if (!daraw.isDrawerOpen(navigationView)){
            daraw.openDrawer(navigationView);
        }
        final ViewPager viewpager=getActivity().findViewById(R.id.vp_setting);
        fragments.add(new item_AdminFragment());
        fragments.add(new item_CityFragment());
        fragments.add(new item_HisFragment());
        fragments.add(new item_loginFragment());
        Button button=getActivity().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (daraw.isDrawerOpen(navigationView)){
                    daraw.closeDrawer(navigationView);
                }else{
                    daraw.openDrawer(navigationView);
                }
            }
        });
        viewpager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
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
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int menuId = menuItem.getItemId();
                Log.d("msg","setting resume"+menuId+R.id.itemadmin);
                // 跳转指定页面：Fragment
                switch (menuId) {
                    case R.id.itemadmin:
                        viewpager.setCurrentItem(0);
                        daraw.closeDrawer(navigationView);
                        return true;
                    case R.id.itemcity:
                        viewpager.setCurrentItem(1);
                        daraw.closeDrawer(navigationView);
                        return true;
                    case R.id.itemhis:
                        viewpager.setCurrentItem(2);
                        daraw.closeDrawer(navigationView);
                        return true;
                    case R.id.itemlogin:
                        //Log.d("msg","setting resume");
                        viewpager.setCurrentItem(3);
                        daraw.closeDrawer(navigationView);
                        return true;
                }
                return false;
            }
        });
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
