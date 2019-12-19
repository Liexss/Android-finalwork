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


/**
 * A simple {@link Fragment} subclass.
 */
public class item_HisFragment extends Fragment {

    public item_HisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item__his2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button cofirmhisbtn=getActivity().findViewById(R.id.confirmhisbtn);
        final NumberPicker his=getActivity().findViewById(R.id.hisweaNum);
        his.setMinValue(1);
        his.setMaxValue(7);
        final int[] data = {1};
        his.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                data[0] =newVal;
            }
        });
        cofirmhisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ctx=getActivity();
                final SharedPreferences sp=ctx.getSharedPreferences("SP", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putInt("weatherday",data[0]);
                editor.apply();
                Toast.makeText(getActivity(), "天数设定成功", Toast.LENGTH_SHORT).show();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}
