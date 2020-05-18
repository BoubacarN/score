package com.example.score;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;


import android.view.ViewGroup;
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

public class MainActivity extends AppCompatActivity implements FragmentInscription.FragmentListener {
private ViewGroup fragmentContainer;
private boolean mTablet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  fragmentContainer=(ViewGroup)findViewById(R.id.fragment_containerInscription);
     mTablet=(fragmentContainer!=null);

    }

    public void handleResultat(View view) {
        if(mTablet){
            Toast.makeText(getApplicationContext(), "Nous sommes sur tablet", Toast.LENGTH_LONG).show();
            chargeFragment();
        }
       else{
            Intent intent = new Intent(this, Destination.class);
            startActivity(intent);
        }

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

    public void chargeFragment(){
        FragmentInscription fragmentInscription = new FragmentInscription();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_containerInscription,fragmentInscription).commit();

    }

    @Override
    public void onFragmentFinish(Joueurs joueur) {
        MyWebSerice myWebService =
                MyWebSerice.retrofit.create(MyWebSerice.class);
        Call<Joueurs> call = myWebService.saveItems(joueur);
        call.enqueue(new Callback<Joueurs>() {
            @Override
            public void onResponse(Call<Joueurs> call, Response<Joueurs> response) {
                if (response.isSuccessful()) {
                    Fragment fragment =(FragmentInscription)getSupportFragmentManager().findFragmentById(R.id.fragment_containerInscription);
                            getSupportFragmentManager().beginTransaction()
                            .remove(fragment).commit();
                }

            }

            @Override
            public void onFailure(Call<Joueurs> call, Throwable t) {
                Log.i("Error",t.getLocalizedMessage().toString());
            }
        });

    }

    public void voirUnResultat(View view) {
        MyWebSerice myWebService =
                MyWebSerice.retrofit.create(MyWebSerice.class);
        Call<List<Joueurs>> call = myWebService.getAllScore();
        call.enqueue(new Callback<List<Joueurs>>() {
            @Override
            public void onResponse(Call<List<Joueurs>> call, Response<List<Joueurs>> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(getApplicationContext(), "OK: Données disponible un a un ", Toast.LENGTH_LONG).show();
                    Log.i("données", response.body().toString());
                    changePageViewPager(response.body());


                }
            }

            @Override
            public void onFailure(Call<List<Joueurs>> call, Throwable t) {
                Log.i("Error", t.getLocalizedMessage().toString());
            }
        });

    }

    public void changePageViewPager(List<Joueurs> data){
        Intent intent = new Intent(this, VoirScoreParScore.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("view_page_item", (ArrayList<? extends Parcelable>) data);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
