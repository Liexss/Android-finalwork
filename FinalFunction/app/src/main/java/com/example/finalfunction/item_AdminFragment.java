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
public class item_AdminFragment extends Fragment {


    public item_AdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item__admin2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final EditText prepas=getActivity().findViewById(R.id.prepas);
        final EditText newpas=getActivity().findViewById(R.id.newpas);
        Button conbtn=getActivity().findViewById(R.id.confirmbtn);
        Context ctx=getActivity();
        final SharedPreferences sp=ctx.getSharedPreferences("SP", Context.MODE_PRIVATE);
        conbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pree=prepas.getText().toString();
                String neww =newpas.getText().toString();
                if(pree.equals(sp.getString("password",""))&&!neww.equals("")){
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("password",neww);
                    editor.apply();
                    Toast.makeText(getActivity(), "密码修改成功", Toast.LENGTH_SHORT).show();
                    prepas.setText("");
                    newpas.setText("");
                }
                else {
                    Toast.makeText(getActivity(), "密码不一致或不得为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}
