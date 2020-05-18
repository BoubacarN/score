package com.example.score;

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
public class ViewPageFragment extends Fragment {
private static final String player_key="player_key";
private Joueurs joueurs;
    public ViewPageFragment() {
        // Required empty public constructor
    }

    public static ViewPageFragment newInstance(Joueurs joueur) {
        
        Bundle args = new Bundle();
        args.putParcelable(player_key,joueur);
        ViewPageFragment fragment = new ViewPageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_view_page, container, false);

Bundle args=getArguments();
if (args==null)throw new AssertionError();
joueurs=args.getParcelable("player_key");
if(joueurs==null)throw new AssertionError();

        TextView nom = view.findViewById(R.id.view_noom);
        TextView prenom = view.findViewById(R.id.view_prenom);
        TextView score =  view.findViewById(R.id.view_score);
        TextView commentaire = view.findViewById(R.id.view_commentaire);

        nom.setText(joueurs.getNom());
        prenom.setText(joueurs.getPrenom());
        score.setText(joueurs.getScore()+"");
        commentaire.setText(joueurs.getCommentaire());

    return view;
    }
}
