package com.example.score;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.score.model.Joueurs;
import com.example.score.service.MyWebSerice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Destination extends AppCompatActivity {
    Button button;

    private String TAG = "NewScore_Activity";
    EditText nom;
    EditText prenom;
    EditText score;
    EditText commentaire;
    EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        FragmentInscription fragment = new FragmentInscription();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
    }

    public void HandleRetour(View view) {
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void HandleAjout(View view) throws ParseException {

        Random random = new Random(5000);
        int Idj = random.nextInt();

        nom = findViewById(R.id.nomid);
        prenom = findViewById(R.id.prenomid);
        commentaire = findViewById(R.id.commentaire);
        date = findViewById(R.id.date);
        score = findViewById(R.id.score);
        Date StringToDate = null;

        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );

        Joueurs joueur = new Joueurs(Idj, nom.getText().toString()
                , prenom.getText().toString()
                , commentaire.getText().toString(),
                new java.sql.Date( df.parse(date.getText().toString()).getTime() ),Integer.valueOf(score.getText().toString()));
        Log.i(TAG,joueur.toString());

        Log.i(TAG,joueur.toString());
        ;
        MyWebSerice myWebService =
                MyWebSerice.retrofit.create(MyWebSerice.class);
        Call<Joueurs> call = myWebService.saveItems(joueur);
        call.enqueue(new Callback<Joueurs>() {
            @Override
            public void onResponse(Call<Joueurs> call, Response<Joueurs> response) {
                if (response.isSuccessful()) {
                    Log.i("INSERTION", "DONNÉE ENVOYÉ AU WEB SERVICE" +
                            response.toString());
                    nom.setText("");
                    prenom.setText("");
                    score.setText("");
                    commentaire.setText("");
                    date.setText("");

                    Toast.makeText(getApplicationContext(), "Nouveau score enrégistré", Toast.LENGTH_LONG).show();
                    ;
                }

            }

            @Override
            public void onFailure(Call<Joueurs> call, Throwable t) {
                Log.i("Error",t.getLocalizedMessage().toString());
            }
        });


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
