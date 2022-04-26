package com.hosein.nzd.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
    TextView textTime;
    TextView textScore;
    TextView textQuiz;
    MaterialButton optionZero;
    MaterialButton optionOwn;
    MaterialButton optionTwo;
    MaterialButton optionThree;
    List<ModelQuestion> modelQuestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIdInit();
        setupStartPage();
        getQuestionFromServer();
    }

    private void startGame() {
        StartGame startGame = new StartGame(this);
        startGame.setTextTime(textTime , 101000);
        startGame.setTextScore(textScore , 0);
        startGame.setTextQuiz(textQuiz , modelQuestions.get(0).getQuiz());
        startGame.setOptionZero(optionZero , modelQuestions.get(0).getOption_zero());
        startGame.setOptionOwn(optionOwn , modelQuestions.get(0).getOption_own());
        startGame.setOptionTwo(optionTwo , modelQuestions.get(0).getOption_two());
        startGame.setOptionThree(optionThree , modelQuestions.get(0).getOption_three());
    }

    private void findViewByIdInit() {
        textScore = findViewById(R.id.text_score);
        textTime = findViewById(R.id.text_time);
        textQuiz = findViewById(R.id.text_question);
        optionZero = findViewById(R.id.optionZero);
        optionOwn = findViewById(R.id.optionOwn);
        optionTwo = findViewById(R.id.optionTwo);
        optionThree = findViewById(R.id.optionThree);
    }

    private void getQuestionFromServer() {

        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        apiInterface.getQuestion().enqueue(new Callback<List<ModelQuestion>>() {
            @Override
            public void onResponse(Call<List<ModelQuestion>> call, Response<List<ModelQuestion>> response) {
                modelQuestions = response.body();
                Toast.makeText(MainActivity.this, "s", Toast.LENGTH_SHORT).show();
                startGame();
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

        animationForButtonStartGame(buttonStartGame);

        buttonStartGame.setOnClickListener(view -> {
            layout_start_parent.setVisibility(View.GONE);
        });

        buttonExitApp.setOnClickListener(view -> {
            finish();
        });

    }

    public void animationForButtonStartGame(MaterialButton buttonStartGame){
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
    }

}