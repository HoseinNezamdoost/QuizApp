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
}
