package com.example.score;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.score.model.Joueurs;

public class DetailPortableScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_portable_score);

        Intent callingIntent = getIntent();
        Joueurs joueur = getIntent().getExtras().getParcelable("detail_display");
        TextView nom = findViewById(R.id.nom_item_detail_portable);
        TextView prenom = findViewById(R.id.prenom_item_portable);
        TextView score =  findViewById(R.id.score_item_portable);
        TextView commentaire = findViewById(R.id.commentaire_item_portable);

        nom.setText(joueur.getNom());
        prenom.setText(joueur.getPrenom());
        score.setText(joueur.getScore()+"");
        commentaire.setText(joueur.getCommentaire());
    }
}
