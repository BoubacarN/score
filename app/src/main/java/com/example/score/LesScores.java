package com.example.score;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.score.model.Joueurs;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class LesScores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_les_scores);



        ArrayList<String> result;

        List<Joueurs> myListCompleteData=null;
        Intent callingIntent = getIntent();
        if (callingIntent != null) {
            result = callingIntent.getStringArrayListExtra("score");
             myListCompleteData = getIntent().getParcelableArrayListExtra("my_key");
            ArrayList<String> Nom_Prenom = new ArrayList<>();
        }


            /*
            for (Joueurs joueur : myListCompleteData) {
                Nom_Prenom.add(joueur.getNom() + " " + joueur.getPrenom());
            }
             ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Nom_Prenom);
*/


            DataScoreAdapter datapter = new DataScoreAdapter(this,myListCompleteData);
            ListView listView = (ListView) (findViewById(android.R.id.list));
              listView.setAdapter(datapter)


         //Using Adapter



            ;


    }


    public void retourAuMenu(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
