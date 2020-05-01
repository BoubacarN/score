package com.example.score;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.score.model.Joueurs;
import com.example.score.service.MyWebSerice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewScoreUser extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.new_score_user);
        /*
        FragmentInscription fragment = new FragmentInscription();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
*/

    }



}
