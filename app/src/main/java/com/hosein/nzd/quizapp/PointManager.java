package com.hosein.nzd.quizapp;

import android.content.Context;
import android.content.SharedPreferences;

public class PointManager {

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public PointManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("point" , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void savePoint(int Point){
        editor.putString("bestPoint" , String.valueOf(Point));
        editor.apply();
    }

    public String getBestPoint(){
        return sharedPreferences.getString("bestPoint" , "");
    }

}
