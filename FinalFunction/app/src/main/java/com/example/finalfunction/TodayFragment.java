package com.example.finalfunction;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodayFragment extends Fragment {
    private int flag = 1;
    private String tip =null;
    private ListView weatherList;
    private List<String> listItem;
    private static final int COMPLETED = 0;

    public TodayFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherList = getActivity().findViewById(R.id.watchWeather);
        Context ctx=getActivity();
        final SharedPreferences sp=ctx.getSharedPreferences("SP", Context.MODE_PRIVATE);
        String aul=sp.getString("weathercity","");
        String nowCity="北京";
        if(!aul.equals("")){
            nowCity=aul;
        }
        sendHttpResponse(nowCity,new okhttp3.Callback(){

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.i("tag",e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i("loadFail", result);

                JSONObject jsonObjectALL = null;
                try {
                    listItem = new ArrayList<String>();
                    if( new JSONObject(result).optInt("status")!=1000){
                        Log.i("loadFail", "无数据");
                        Message message = new Message();
                        message.what = 2;
                        handler.sendMessage(message);
                        return;
                    }
                    jsonObjectALL = new JSONObject(result).getJSONObject("data");
                    listItem.add( jsonObjectALL.optString("city", null));
                    String forecastJSONArray = jsonObjectALL.optString("forecast", null);
                    Gson gson = new Gson();
                    List<weather2> list = gson.fromJson(forecastJSONArray, new TypeToken<List<weather2>>(){}.getType());
//                    for (weather2 f : list) {
//                        Log.d("tag", "解析后的数据 f.toString:" + f.toString());
//                    }
                    SharedPreferences.Editor editor=sp.edit();
                    for(int i=0;i<1;i++){
                        Log.d("tag",list.get(i).getDate());
                        listItem.add(list.get(i).getDate());
                        listItem.add(list.get(i).getHigh());
                        listItem.add(list.get(i).getLow());
                        listItem.add(list.get(i).getFengli());
                        listItem.add(list.get(i).getFengxiang());
                        listItem.add(list.get(i).getType());
                        editor.putString("weather",list.get(i).getType());
                        listItem.add(" ");
                    }
                    editor.apply();
                    Message message = new Message();
                    message.what = COMPLETED;
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today, container, false);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==COMPLETED){
                ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listItem);
                weatherList.setAdapter(aa);
            }
            else if(msg.what==2){
                ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listItem);
                weatherList.setAdapter(aa);
                Toast.makeText(getActivity(), "该城市无数据", Toast.LENGTH_SHORT).show();
            }
        }
    };
    public void sendHttpResponse(String city,final okhttp3.Callback callback) {
        try {

            String url = "http://wthrcdn.etouch.cn/weather_mini?city="+city ;
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            okHttpClient.newCall(request).enqueue(callback);
        } catch (Exception e) {
            Log.i("loadFail", "加载失败:" + e.getMessage());
            Toast.makeText(getActivity(), "网站加载失败", Toast.LENGTH_SHORT).show();
        }
    }

}
