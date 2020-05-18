package com.example.score;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.score.model.Joueurs;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Joueurs joueur = getIntent().getExtras().getParcelable("ItemSelected");
        if (joueur != null){
            Toast.makeText(this,"You Selected "+joueur.getNom() + " : "+joueur.getPrenom(),Toast.LENGTH_SHORT).show();
           TextView nom=findViewById(R.id.nom_item);
            TextView prenom=findViewById(R.id.prenom_item);
            TextView score=findViewById(R.id.score_item);
            TextView commentaire=findViewById(R.id.commentaire_item);
            nom.setText(joueur.getNom());
            prenom.setText(joueur.getPrenom());
            score.setText(joueur.getScore()+"");
            commentaire.setText(joueur.getCommentaire());


        }
        else {
            Toast.makeText(this,"rien reçu ",Toast.LENGTH_SHORT).show();
        }
    }



}
