package com.hosein.nzd.quizapp;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

import java.util.Timer;
import java.util.TimerTask;

public class StartGame {

    long myTime;
    Context context;

    public StartGame(Context context) {
        this.context = context;
    }


    public void setTextScore(TextView textScore , int score) {
        textScore.setText(String.valueOf(score));
    }

    public void setTextQuiz(TextView textQuiz , String quiz) {
        textQuiz.setText(quiz);
    }

    public void setOptionZero(MaterialButton optionZero , String question) {
        optionZero.setText(question);
    }

    public void setOptionOwn(MaterialButton optionOwn, String question) {
        optionOwn.setText(question);
    }

    public void setOptionTwo(MaterialButton optionTwo, String question) {
        optionTwo.setText(question);
    }

    public void setOptionThree(MaterialButton optionThree, String question) {
        optionThree.setText(question);
    }

    // methods set the time

    public void setTextTime(TextView textTime , int time) {

        new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                myTime = millisUntilFinished;
                updateTime(textTime);
                setColorTextTime(textTime);
            }

            public void onFinish() {
                textTime.setText("done!");
            }
        }.start();

    }

    private void updateTime(TextView text){

        int minute = (int) myTime / 60000;
        int second = (int) myTime % 60000 / 1000;

        String textTime;
        textTime = "" + minute;
        textTime += ":";
        if (second < 10) textTime+= "0";
        textTime += second;
        text.setText(textTime);
    }

    private void setColorTextTime(TextView textTime) {
        if (myTime<60000){
            textTime.setTextColor(ContextCompat.getColor(context , R.color.yellow));
        }

        if (myTime<20000){
            textTime.setTextColor(ContextCompat.getColor(context , R.color.red));
        }

    }
}
