package com.example.finalfunction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.baidu.mapapi.SDKInitializer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final class FragmentAdapter extends FragmentPagerAdapter{
        private List<Fragment> mFragments;
        public FragmentAdapter(List<Fragment> fragments, FragmentManager fm) {
            super(fm);
            this.mFragments = fragments;
        }
        @Override
        public int getCount() {
            return mFragments.size();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        final  BottomNavigationView navView = findViewById(R.id.nav_view);
        final ViewPager viewpage=findViewById(R.id.vp_content);
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new MapFragment());
        fragments.add(new WeatherFragment());
        fragments.add(new HisFragment());
        fragments.add(new SettingFragment());
        FragmentAdapter adapter = new FragmentAdapter(fragments, getSupportFragmentManager());
        viewpage.setAdapter(adapter);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int menuId = menuItem.getItemId();

                // 跳转指定页面：Fragment
                switch (menuId) {
                    case R.id.navigation_map:
                        viewpage.setCurrentItem(0);
                        return true;
                    case R.id.navigation_weather:
                        viewpage.setCurrentItem(1);
                        return true;
                    case R.id.navigation_history:
                        viewpage.setCurrentItem(2);
                        return true;
                    case R.id.navigation_setting:
                        //Log.d("msg","setting resume");
                        viewpage.setCurrentItem(3);
                        return true;
                }
                return false;
            }
        });
        viewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               // Log.d("msg","setting resume"+position);
                navView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
