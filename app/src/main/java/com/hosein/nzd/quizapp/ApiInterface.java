package com.hosein.nzd.quizapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("question.php")
    Call<List<ModelQuestion>> getQuestion();

}
