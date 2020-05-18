package com.example.score;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.score.model.Joueurs;
import com.example.score.service.MyWebSerice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInscription extends Fragment {
    EditText nom;
    EditText prenom;
    EditText score;
    EditText commentaire;
    EditText date;

    private FragmentListener mlistner;


    public FragmentInscription() {
        // Required empty public constructor
    }

    public static FragmentInscription newInstance(Joueurs joueurs) {

        Bundle args = new Bundle();
        args.putParcelable("New_player",joueurs);
        FragmentInscription fragment = new FragmentInscription();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(!(context instanceof FragmentListener)) throw new AssertionError();
        mlistner=(FragmentListener)context ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//Joueurs joueurs = new Joueurs();
//joueurs= getArguments().getParcelable("New_player");
//nom.setText(joueurs.getNom());
//        prenom.setText(joueurs.getPrenom());
//        score.setText(joueurs.getScore());
//        commentaire.setText(joueurs.getCommentaire());

        View view= inflater.inflate(R.layout.fragment_inscription, container, false);


        nom=view.findViewById(R.id.nomid);
        prenom=view.findViewById(R.id.prenomid);
        commentaire=view.findViewById(R.id.commentaire);
        score=view.findViewById(R.id.score);
        date=view.findViewById(R.id.date);

        Button doneButton=(Button)view.findViewById(R.id.nouveu_score);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random(10000);
                int Idj = random.nextInt()+5000;

                SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
                Log.i("Test Fragment",nom.getText().toString());
                if(mlistner==null) throw new AssertionError();
                Joueurs joueurs =  new Joueurs();
                joueurs.setScore(Integer.parseInt(score.getText().toString()));
                joueurs.setNom(nom.getText().toString());
                joueurs.setCommentaire(commentaire.getText().toString());
                joueurs.setPrenom(prenom.getText().toString());
                joueurs.setJoueurId(Idj);
                try {
                    joueurs.setDatejeu(new java.sql.Date( df.parse(date.getText().toString()).getTime() ));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mlistner.onFragmentFinish(joueurs);

            }
        });
        Button cancelButton=(Button)view.findViewById(R.id.Clear);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          Intent intent = new Intent(getContext(),MainActivity.class);
          startActivity(intent);
            }
        });
        return view;
    }


    public interface FragmentListener{

        public void onFragmentFinish(Joueurs joueur);
    }
}
