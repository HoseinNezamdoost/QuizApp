package com.hosein.nzd.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialButton buttonStartGame , buttonExitApp;
    public static RelativeLayout layout_start_parent;
    TextView textTime;
    TextView textScore;
    TextView textQuiz;
    TextView textNumberQuiz;
    TextView textMostPoint;
    MaterialButton optionZero;
    MaterialButton optionOwn;
    MaterialButton optionTwo;
    MaterialButton optionThree;
    List<ModelQuestion> questionList = new ArrayList<>();
    StartGame startGame;
    private int idQuiz = 0;
    private int index = 0;
    private int score = 0;
    PointManager pointManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIdInit();
        setupStartPage();
        getQuestionFromServer();

    }

    private void startGame() {
        startGame = new StartGame(this);
        startGame.setTextTime(textTime , 101000);
        startGame.setTextScore(textScore , 0);
        startGame.setTextNumberQuiz(textNumberQuiz , 1);
        startGame.setTextQuiz(textQuiz , questionList.get(0).getQuiz());
        startGame.setOptionZero(optionZero , questionList.get(0).getOption_zero() , this);
        startGame.setOptionOwn(optionOwn , questionList.get(0).getOption_own() , this);
        startGame.setOptionTwo(optionTwo , questionList.get(0).getOption_two() , this);
        startGame.setOptionThree(optionThree , questionList.get(0).getOption_three() , this);
    }

    private void startGameForOwn() {
        startGame = new StartGame(this);
        startGame.setTextScore(textScore , 0);
        startGame.setTextNumberQuiz(textNumberQuiz , 1);
        startGame.setTextQuiz(textQuiz , questionList.get(0).getQuiz());
        startGame.setOptionZero(optionZero , questionList.get(0).getOption_zero() , this);
        startGame.setOptionOwn(optionOwn , questionList.get(0).getOption_own() , this);
        startGame.setOptionTwo(optionTwo , questionList.get(0).getOption_two() , this);
        startGame.setOptionThree(optionThree , questionList.get(0).getOption_three() , this);
    }

    private void findViewByIdInit() {
        textScore = findViewById(R.id.text_score);
        textTime = findViewById(R.id.text_time);
        textNumberQuiz = findViewById(R.id.text_number_question);
        textQuiz = findViewById(R.id.text_question);
        optionZero = findViewById(R.id.optionZero);
        optionOwn = findViewById(R.id.optionOwn);
        optionTwo = findViewById(R.id.optionTwo);
        optionThree = findViewById(R.id.optionThree);
        textMostPoint = findViewById(R.id.most_point);
        optionZero.setTag(0);
        optionOwn.setTag(1);
        optionTwo.setTag(2);
        optionThree.setTag(3);
        pointManager = new PointManager(MainActivity.this);
    }

    private void getQuestionFromServer() {

        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        apiInterface.getQuestion().enqueue(new Callback<List<ModelQuestion>>() {
            @Override
            public void onResponse(Call<List<ModelQuestion>> call, Response<List<ModelQuestion>> response) {
                questionList = response.body();
                Toast.makeText(MainActivity.this, "s", Toast.LENGTH_SHORT).show();
                startGameForOwn();
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

        textMostPoint.setText("Most Point is : " + pointManager.getBestPoint());

        buttonStartGame.setOnClickListener(view -> {
            layout_start_parent.setVisibility(View.GONE);
            idQuiz = 0;
            index = 0;
            score = 0;
            startGame();
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

    @Override
    public void onClick(View view) {

        index+=1;

        if (!(questionList.size()+1 <= index)){
            idQuiz += 1;
            if ((int) view.getTag() == questionList.get(idQuiz-1).getAnswerTrue()) {
                ((MaterialButton) view).setStrokeColor(ContextCompat.getColorStateList(MainActivity.this, R.color.green1));
                ((MaterialButton) view).setTextColor(ContextCompat.getColor(MainActivity.this, R.color.green1));
                ((MaterialButton) view).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_check));
                ((MaterialButton) view).setIconTint(ContextCompat.getColorStateList(MainActivity.this, R.color.green1));
                score+=1;
                textScore.setText(String.valueOf(score));
            } else {
                ((MaterialButton) view).setStrokeColor(ContextCompat.getColorStateList(MainActivity.this, R.color.red));
                ((MaterialButton) view).setTextColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                ((MaterialButton) view).setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_close));
                ((MaterialButton) view).setIconTint(ContextCompat.getColorStateList(MainActivity.this, R.color.red));
            }
            updateGame(view);
        }
    }

    public void updateGame(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    startGame.setTextQuiz(textQuiz , questionList.get(idQuiz).getQuiz());
                    startGame.setTextNumberQuiz(textNumberQuiz , questionList.get(idQuiz).getId());
                    startGame.setOptionZero(optionZero , questionList.get(idQuiz).getOption_zero() ,MainActivity.this);
                    startGame.setOptionOwn(optionOwn , questionList.get(idQuiz).getOption_own() , MainActivity.this);
                    startGame.setOptionTwo(optionTwo , questionList.get(idQuiz).getOption_two() , MainActivity.this);
                    startGame.setOptionThree(optionThree , questionList.get(idQuiz).getOption_three() , MainActivity.this);

                    ((MaterialButton)view).setStrokeColor(ContextCompat.getColorStateList(MainActivity.this , R.color.gray2));
                    ((MaterialButton)view).setTextColor(ContextCompat.getColor(MainActivity.this , R.color.white));
                    ((MaterialButton)view).setIcon(ContextCompat.getDrawable(MainActivity.this , R.drawable.ic_circle));
                    ((MaterialButton)view).setIconTint(ContextCompat.getColorStateList(MainActivity.this , R.color.gray2));
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "finish", Toast.LENGTH_SHORT).show();
                    if (Integer.parseInt(pointManager.getBestPoint()) < score){
                        pointManager.savePoint(score);
                    }
                    layout_start_parent.setVisibility(View.VISIBLE);
                    ((MaterialButton)view).setStrokeColor(ContextCompat.getColorStateList(MainActivity.this , R.color.gray2));
                    ((MaterialButton)view).setTextColor(ContextCompat.getColor(MainActivity.this , R.color.white));
                    ((MaterialButton)view).setIcon(ContextCompat.getDrawable(MainActivity.this , R.drawable.ic_circle));
                    ((MaterialButton)view).setIconTint(ContextCompat.getColorStateList(MainActivity.this , R.color.gray2));
                    startGame.countDownTimer.cancel();
                }
            }
        } , 500);

    }
}