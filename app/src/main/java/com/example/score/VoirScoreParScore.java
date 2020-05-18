package com.example.score;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.score.model.Joueurs;
import com.example.score.service.MyWebSerice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoirScoreParScore extends AppCompatActivity {

    private ViewPager viewPager;

    private List<Joueurs> joueurs;
    private int saizelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_score_par_score);


       joueurs= getIntent().getParcelableArrayListExtra("view_page_item");
       saizelist=joueurs.size();

        viewPager=(ViewPager)findViewById(R.id.pager);

        PagerAdapter pagerAdapter=new ViewPagerAdatper(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    private class ViewPagerAdatper extends FragmentStatePagerAdapter {

        public ViewPagerAdatper(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ViewPageFragment.newInstance(joueurs.get(position));
        }

        @Override
        public int getCount() {
            Log.i("SIIIIZE",saizelist+"");
            return saizelist;
        }
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem()==0){
            super.onBackPressed();
        }
        else{
            viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
        }


    }
}
