package com.example.finalfunction;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class HisFragment extends Fragment {
    TextView histitle;
    WeatherChartView chartView;
    private boolean isPrepared = false;
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private void initDatabase() {
        //初始化数据相关工具
        dbHelper = new MyDatabaseHelper(getContext(),
                "weather.db", null, 1);
        db = dbHelper.getWritableDatabase();
    }
    public HisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isPrepared && isVisibleToUser) {
            Context ctx=getActivity();
            final SharedPreferences sp=ctx.getSharedPreferences("SP", Context.MODE_PRIVATE);
            int daytime=sp.getInt("weatherday",0);
            String aul=sp.getString("weathercity","");
            String nowcity="";
            if(aul.equals("石家庄市")||aul.equals("南昌市")){
                nowcity=aul;
            }
            else
            {
                nowcity="南昌市";
            }
            Log.d("msg",""+daytime);
            chartView.setLENGTH(daytime);
            Cursor cursor=db.query("Weather",null,null,null,null,null,"time ASC");
            int [ ] highArray = new int [30];
            int [ ] lowArray = new int [30];
            int num=0;
            if(cursor.moveToFirst()){
                do{
                    String name=cursor.getString(cursor.getColumnIndex("city"));
                    int hightep=cursor.getInt(cursor.getColumnIndex("hightep"));
                    int lowtep=cursor.getInt(cursor.getColumnIndex("lowtep"));
                    String date=cursor.getString(cursor.getColumnIndex("time"));
                    if(name.equals(nowcity)){
                        highArray[num]=hightep;
                        lowArray[num++]=lowtep;
                    }
                    Log.d("msg",name+hightep+lowtep+date);
                }while(cursor.moveToNext());
            }
            cursor.close();
            histitle.setText("显示历史"+nowcity+""+daytime+"天天气的最高温度和最低温度");
            chartView .setTempDay(highArray);
            // 设置夜间温度曲线
            chartView .setTempNight(lowArray);
            chartView .invalidate();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_his, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 设置白天温度曲线
        initDatabase();
        chartView = (WeatherChartView) getActivity().findViewById(R.id.line_char);
        histitle=getActivity().findViewById(R.id.histitle);
        isPrepared = true;
        setUserVisibleHint(getUserVisibleHint());
//        Context ctx=getActivity();
////        final SharedPreferences sp=ctx.getSharedPreferences("SP", Context.MODE_PRIVATE);
////        int daytime=sp.getInt("weatherday",0);
////        Log.d("msg",""+daytime);
////        chartView.setLENGTH(daytime);
////        histitle.setText("显示未来"+daytime+"天最高温度和最低温度");
////        chartView .setTempDay(new int[]{16,12,13,20,31,35,31});
////        // 设置夜间温度曲线
////        chartView .setTempNight(new int[]{11,8,0,10,21,25,21});
////        chartView .invalidate();
//        Cursor cursor;
//        cursor = db.query("history_tbl", null, null,
//                null, null, null, "id desc");
//        cursor.close();
    }
}
