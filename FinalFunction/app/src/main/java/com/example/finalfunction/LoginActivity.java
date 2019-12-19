package com.example.finalfunction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import static com.baidu.mapapi.BMapManager.getContext;

public class LoginActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private void initDatabase() {
        //初始化数据相关工具
        dbHelper = new MyDatabaseHelper(LoginActivity.this,
                "weather.db", null, 1);
        db = dbHelper.getWritableDatabase();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initDatabase();
        ContentValues values=new ContentValues();
        values.put("city","石家庄市");
        values.put("hightep",17);
        values.put("lowtep",3);
        values.put("time","2019-12-25");
        db.insert("Weather",null,values);
//        values.put("city","石家庄市");
//        values.put("hightep",10);
//        values.put("lowtep",7);
//        values.put("time","2019-12-20");
//        db.insert("Weather",null,values);
//        values.put("city","石家庄市");
//        values.put("hightep",4);
//        values.put("lowtep",-1);
//        values.put("time","2019-12-21");
//        db.insert("Weather",null,values);
//        values.put("city","石家庄市");
//        values.put("hightep",17);
//        values.put("lowtep",3);
//        values.put("time","2019-12-22");
//        db.insert("Weather",null,values);
//        values.put("city","石家庄市");
//        values.put("hightep",21);
//        values.put("lowtep",12);
//        values.put("time","2019-12-23");
//        db.insert("Weather",null,values);
//        values.put("city","石家庄市");
//        values.put("hightep",7);
//        values.put("lowtep",4);
//        values.put("time","2019-12-24");
//        db.insert("Weather",null,values);
//        values.put("city","南昌市");
//        values.put("hightep",17);
//        values.put("lowtep",10);
//        values.put("time","2019-12-19");
//        db.insert("Weather",null,values);
//        values.put("city","南昌市");
//        values.put("hightep",10);
//        values.put("lowtep",7);
//        values.put("time","2019-12-20");
//        db.insert("Weather",null,values);
//        values.put("city","南昌市");
//        values.put("hightep",15);
//        values.put("lowtep",4);
//        values.put("time","2019-12-21");
//        db.insert("Weather",null,values);
//        values.put("city","南昌市");
//        values.put("hightep",17);
//        values.put("lowtep",10);
//        values.put("time","2019-12-22");
//        db.insert("Weather",null,values);
//        values.put("city","南昌市");
//        values.put("hightep",8);
//        values.put("lowtep",3);
//        values.put("time","2019-12-23");
//        db.insert("Weather",null,values);
        values.put("city","南昌市");
        values.put("hightep",5);
        values.put("lowtep",-10);
        values.put("time","2019-12-25");
        db.insert("Weather",null,values);
        Button loginbtn=findViewById(R.id.loginbtn);
        final EditText user=findViewById(R.id.username);
        final EditText password=findViewById(R.id.password);
        Context ctx=LoginActivity.this;
        try{
        final SharedPreferences sp=ctx.getSharedPreferences("SP", Context.MODE_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String use=sp.getString("isknow","");
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("name","admin");
        editor.putString("password","123456");
        editor.putInt("weatherday",6);
        editor.apply();
        if(use.equals("1")){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getText().toString().equals(sp.getString("name",""))&&password.getText().toString().equals(sp.getString("password",""))){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
