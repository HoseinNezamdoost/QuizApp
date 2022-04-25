package com.hosein.nzd.quizapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){

        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://hosein-nzd.ir/android_app/QuizApp/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
