package com.example.score.service;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.score.LesScores;
import com.example.score.model.Joueurs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataReloader {
    List<Joueurs> mylist = null;


    public  List<Joueurs> reloadData() {


        MyWebSerice myWebService =
                MyWebSerice.retrofit.create(MyWebSerice.class);
        Call<List<Joueurs>> call = myWebService.getAllScore();
        call.enqueue(new Callback<List<Joueurs>>() {
            @Override
            public void onResponse(Call<List<Joueurs>> call, Response<List<Joueurs>> response) {
                if (response.isSuccessful()) {

Log.i("rechercheheheh",response.body().toString());
                    mylist = response.body();

                }
            }

            @Override
            public void onFailure(Call<List<Joueurs>> call, Throwable t) {
                Log.i("Error", t.getLocalizedMessage().toString());
            }
        });

return mylist;
    }
}

