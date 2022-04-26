package com.hosein.nzd.quizapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ModelQuestion {

    @SerializedName("id")
    private int id;
    @SerializedName("quiz")
    private String quiz;
    @SerializedName("options0")
    private String option_zero;
    @SerializedName("options1")
    private String option_own;
    @SerializedName("options2")
    private String option_two;
    @SerializedName("options3")
    private String option_three;
    @SerializedName("answer")
    private String answerTrue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public String getOption_zero() {
        return option_zero;
    }

    public void setOption_zero(String option_zero) {
        this.option_zero = option_zero;
    }

    public String getOption_own() {
        return option_own;
    }

    public void setOption_own(String option_own) {
        this.option_own = option_own;
    }

    public String getOption_two() {
        return option_two;
    }

    public void setOption_two(String option_two) {
        this.option_two = option_two;
    }

    public String getOption_three() {
        return option_three;
    }

    public void setOption_three(String option_three) {
        this.option_three = option_three;
    }

    public String getAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(String answerTrue) {
        this.answerTrue = answerTrue;
    }
}
