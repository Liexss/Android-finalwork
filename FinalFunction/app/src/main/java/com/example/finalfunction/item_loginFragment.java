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
public class item_loginFragment extends Fragment {


    public item_loginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_login2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Switch switch1=getActivity().findViewById(R.id.switch1);
        Context ctx=getActivity();
        final SharedPreferences sp=ctx.getSharedPreferences("SP",Context.MODE_PRIVATE);
        String user=sp.getString("isknow","");
        if(user.equals("1")){
            switch1.setChecked(true);
        }
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("isknow","1");
                    editor.apply();
                }
                else{
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("isknow","0");
                    editor.apply();
                }
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}
