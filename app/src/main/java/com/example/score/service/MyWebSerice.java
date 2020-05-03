package com.example.score.service;

import com.example.score.model.Joueurs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public interface MyWebSerice {

    String BASE_URL="http://192.168.0.18:8080";

    /*String FEED = "api/reservations";*/
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd").create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

/*
    @GET(FEED)
    Call<List<Joueurs>> dataItems();*/

    @POST("/api/confines/liveScore/save")
    Call<Joueurs> saveItems(@Body  Joueurs joueur);

    @GET("/api/confines/liveScore/score")
    Call<List<Joueurs>> getAllScore();

    @GET("/api/confines/liveScore/scorebyId")
    Call<List<Joueurs>> getOneScore(@Field("id") String id);

    @DELETE("/api/confines/liveScore/delete")
    Call<List<Joueurs>> deleteScore(@Field("id") String id);


    @PATCH("/api/confines/score/update")
    Call<List<Joueurs>> UpdateScore(@Body  Joueurs joueur);
}
