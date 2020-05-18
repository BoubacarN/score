package com.example.score;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import com.example.score.model.Joueurs;
import com.example.score.service.DataReloader;

import java.util.List;

public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.ViewHolder> {

    private List<Joueurs> mItems;
    private Context mContext;


    public DataItemAdapter(Context context, List<Joueurs> items) {
        this.mContext = context;
        this.mItems = items;

    }

    @Override
    public DataItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DataItemAdapter.ViewHolder holder, int position) {
        final Joueurs item = mItems.get(position);

            holder.nom.setText(item.getNom());
            holder.prenom.setText(item.getPrenom());
           /* holder.commentaire.setText(item.getCommentaire());*/
            holder.score.setText(item.getScore()+"");
            holder.mView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
//        Toast.makeText(mContext,"You Selected "+item.getNom() + " : "+item.getPrenom(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mContext,DetailActivity.class);
        intent.putExtra("ItemSelected",item);
        mContext.startActivity(intent);


    }
});
            holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(mContext,"You Long clicked "+item.getNom() + " : "+item.getPrenom(),Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nom;
        public TextView prenom;
        public TextView score;
        public View mView;
        public TextView commentaire;
        public ViewHolder(View itemView) {
            super(itemView);

            nom = (TextView) itemView.findViewById(R.id.nomf);
            prenom = (TextView) itemView.findViewById(R.id.prenomf);
            score = (TextView) itemView.findViewById(R.id.scoref);
            mView=itemView;
            /*commentaire = (TextView) itemView.findViewById(R.id.commentairef);*/
        }
    }
}