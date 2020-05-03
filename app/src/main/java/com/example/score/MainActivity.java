package com.example.score;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.score.model.Joueurs;
import com.example.score.service.MyWebSerice;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void handleResultat(View view) {

        Intent intent = new Intent(this, Destination.class);
        startActivity(intent);
/*
        FragmentInscription fragment = new FragmentInscription();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
*/

    }


    public void AllScore(View view) {

        MyWebSerice myWebService =
                MyWebSerice.retrofit.create(MyWebSerice.class);
        Call<List<Joueurs>> call = myWebService.getAllScore();
        call.enqueue(new Callback<List<Joueurs>>() {
            @Override
            public void onResponse(Call<List<Joueurs>> call, Response<List<Joueurs>> response) {
                if (response.isSuccessful()) {
                    Log.i("RESTITUTION", "données reçu du webservice" +
                            response.toString());

                    Toast.makeText(getApplicationContext(), "OK: Données disponible", Toast.LENGTH_LONG).show();
                    Log.i("données", response.body().toString());
                    changePage(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Joueurs>> call, Throwable t) {
                Log.i("Error", t.getLocalizedMessage().toString());
            }
        });

    }

    public void changePage(List<Joueurs> data){
         Intent intent = new Intent(this, LesScores.class);
      /*   intent.putStringArrayListExtra("score", data);

*/


        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("my_key", (ArrayList<? extends Parcelable>) data);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
