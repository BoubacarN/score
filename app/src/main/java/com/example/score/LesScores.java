package com.example.score;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.score.model.Joueurs;

import java.util.ArrayList;
import java.util.List;

public class LesScores extends AppCompatActivity implements DetailFragment.FragmentListener {
    ListView listView;
    private ViewGroup fragmentContainer;
    private boolean mTablet;
    List<Joueurs> myListCompleteData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_les_scores);


        ArrayList<String> result;


        Intent callingIntent = getIntent();
        if (callingIntent != null) {
            result = callingIntent.getStringArrayListExtra("score");
            myListCompleteData = getIntent().getParcelableArrayListExtra("my_key");
            Log.i("ISSSSSSSSS", myListCompleteData.toString());
            ArrayList<String> Nom_Prenom = new ArrayList<>();
        }


            /*
            for (Joueurs joueur : myListCompleteData) {
                Nom_Prenom.add(joueur.getNom() + " " + joueur.getPrenom());
            }
             ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Nom_Prenom);
*/


        DataScoreAdapterListView datapter = new DataScoreAdapterListView(this, myListCompleteData);
        listView = (ListView) (findViewById(android.R.id.list));
        listView.setAdapter(datapter);

        fragmentContainer = (ViewGroup) findViewById(R.id.detail_container);
        mTablet = (fragmentContainer != null);
        final Intent intent2=new Intent(this,DetailPortableScore.class);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (mTablet) {
                    Joueurs joueur = myListCompleteData.get(position);
                    OnFragmentItemClicked(joueur);

                    Log.i("trotro", joueur.toString());



                                DetailFragment fragmentDetail = DetailFragment.newInstance(joueur);
                                FragmentManager
                            manager=getSupportFragmentManager();

                    manager.beginTransaction().replace(R.id.detail_container,fragmentDetail).commit();


                }
else{
                    intent2.putExtra("detail_display",myListCompleteData.get(position));
                    startActivity(intent2);


                }



            }

        });


//        DataItemAdapter datapter = new DataItemAdapter(this,myListCompleteData);
//        RecyclerView recyclerView = (RecyclerView) (findViewById(R.id.rvItems));
//        recyclerView.setAdapter(datapter)

        //Using Adapter


        ;


        /*Only for the listView*/


    }

    @Override
    public void OnFragmentItemClicked(Joueurs joueur) {


    }
}
