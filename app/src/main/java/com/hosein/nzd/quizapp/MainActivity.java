package com.hosein.nzd.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MaterialButton buttonStartGame , buttonExitApp;
    RelativeLayout layout_start_parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupStartPage();
        getQuestionFromServer();

    }

    private void getQuestionFromServer() {

        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        apiInterface.getQuestion().enqueue(new Callback<List<ModelQuestion>>() {
            @Override
            public void onResponse(Call<List<ModelQuestion>> call, Response<List<ModelQuestion>> response) {
                List<ModelQuestion> modelQuestions = new ArrayList<>();
                modelQuestions.addAll(response.body());
                Toast.makeText(MainActivity.this, "s", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ModelQuestion>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: " + t);
            }
        });

    }

    private void setupStartPage(){

        buttonStartGame = findViewById(R.id.button_start_game);
        layout_start_parent = findViewById(R.id.layout_start_parent);
        buttonExitApp = findViewById(R.id.button_exit_app);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0,2);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        buttonStartGame.startAnimation(alphaAnimation);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1,1.5f , 1,1.5f ,
                Animation.RELATIVE_TO_SELF , 0.5f , Animation.RELATIVE_TO_SELF , 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.REVERSE);


        AnimationSet set = new AnimationSet(true);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        buttonStartGame.startAnimation(set);

        buttonStartGame.setOnClickListener(view -> {
            layout_start_parent.setVisibility(View.GONE);
        });

        buttonExitApp.setOnClickListener(view -> {
            finish();
        });

    }

}