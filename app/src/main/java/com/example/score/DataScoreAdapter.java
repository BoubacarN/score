package com.example.score;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.score.model.Joueurs;

import java.util.ArrayList;
import java.util.List;

public class DataScoreAdapter extends ArrayAdapter<Joueurs> {
    LayoutInflater mflagter;
    List<Joueurs> joueursliste;



    public DataScoreAdapter(Context context, List<Joueurs> objects) {
        super(context,R.layout.item_list, objects);
        joueursliste=objects;
        mflagter=LayoutInflater.from(context);

     //   Log.i("From Adapter",joueursliste.toString());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView=mflagter.inflate(R.layout.item_list,parent,false);
        }

        TextView nom=convertView.findViewById(R.id.nomf);
        TextView prenom=convertView.findViewById(R.id.prenomf);
        TextView commentaire=convertView.findViewById(R.id.commentairef);
        TextView score=convertView.findViewById(R.id.scoref);
        TextView date=convertView.findViewById(R.id.date);

        Joueurs joueurs = joueursliste.get(position);

        nom.append(joueurs.getNom());
        prenom.append(joueurs.getPrenom());
        commentaire.append(joueurs.getCommentaire());
        score.append(joueurs.getScore()+"");

Log.i("output adapter",joueurs.toString()+"postition "+position);
        return convertView;
    }
}
