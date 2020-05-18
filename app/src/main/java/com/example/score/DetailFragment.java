package com.example.score;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.score.model.Joueurs;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    FragmentListener mlistener;
    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Joueurs joueur) {

        Bundle args = new Bundle();
        args.putParcelable("display_detail",joueur);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(context instanceof FragmentListener)) throw new  AssertionError();
        mlistener=(FragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_detail, container, false);

        Joueurs joueur;
        joueur= getArguments().getParcelable("display_detail");

        TextView nom = view.findViewById(R.id.nom_item_detail);
        TextView prenom = view.findViewById(R.id.prenom_item_detail);
        TextView score =  view.findViewById(R.id.score_item_detail);
        TextView commentaire = view.findViewById(R.id.commentaire_item_detail);

        nom.setText(joueur.getNom());
        prenom.setText(joueur.getPrenom());
        score.setText(joueur.getScore()+"");
        commentaire.setText(joueur.getCommentaire());


return  view;

    }

    public interface FragmentListener{
        public void OnFragmentItemClicked(Joueurs joueur);
        }
    }

