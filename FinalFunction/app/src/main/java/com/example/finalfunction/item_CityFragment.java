package com.example.finalfunction;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.lljjcoder.citypickerview.widget.CityPickerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class item_CityFragment extends Fragment {


    public item_CityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item__city2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final CityPickerView cityPickerView = new CityPickerView(getActivity());
        final TextView choosetext=getActivity().findViewById(R.id.choosetext);
        Context ctx=getActivity();
        final SharedPreferences sp=ctx.getSharedPreferences("SP", Context.MODE_PRIVATE);
        String nowallcity=sp.getString("allcity","");
        if(!nowallcity.equals("")){
            choosetext.setText("当前选择的城市是"+nowallcity);
        }
        cityPickerView.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                SharedPreferences.Editor editor=sp.edit();
                choosetext.setText("当前选择的城市是"+province+"-"+city+"-"+district);
                editor.putString("weathercity",city);
                editor.putString("allcity",province+"-"+city+"-"+district);
                editor.apply();


            }
        });
        Button choosebtn=getActivity().findViewById(R.id.choosebtn);
        choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityPickerView.show();
            }
        });
        super.onViewCreated(view, savedInstanceState);

    }
}
