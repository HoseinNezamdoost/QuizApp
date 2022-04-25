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

    public void setTextScore(TextView textScore , String score) {
        textScore.setText(score);
    }

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

    public void setTextQuiz(TextView textQuiz , String quiz) {
        textQuiz.setText(quiz);
    }

    public void setOptionZero(MaterialButton optionZero) {
    }

    public void setOptionOwn(MaterialButton optionOwn) {
    }

    public void setOptionTwo(MaterialButton optionTwo) {
    }

    public void setOptionThree(MaterialButton optionThree) {
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
