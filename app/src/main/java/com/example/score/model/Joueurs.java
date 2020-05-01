package com.example.score.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

public class Joueurs implements Parcelable {


    private long joueurId;

    private String nom;

    private String prenom;

    private String commentaire;

    private Date datejeu;

    private int score;


    public Joueurs() {

    }

    @Override
    public String toString() {
        return "Joueurs{" +
                "joueurId=" + joueurId +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", commentaire='" + commentaire + '\'' +
                ", datejeu=" + datejeu +
                ", score=" + score +
                '}';
    }


    public Joueurs(long joueurId, String nom, String prenom, String commentaire, Date datejeu, int score) {
        this.joueurId = joueurId;
        this.nom = nom;
        this.prenom = prenom;
        this.commentaire = commentaire;
        this.datejeu = datejeu;
        this.score = score;
    }

    public long getJoueurId() {
        return joueurId;
    }

    public void setJoueurId(long joueurId) {
        this.joueurId = joueurId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDatejeu() {
        return datejeu;
    }

    public void setDatejeu(Date dateJeu) {
        this.datejeu = dateJeu;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }



    protected Joueurs(Parcel in) {
        joueurId = in.readLong();
        nom = in.readString();
        prenom = in.readString();
        commentaire = in.readString();
        long tmpDateJeu = in.readLong();
        datejeu = tmpDateJeu != -1 ? new Date(tmpDateJeu) : null;
        score = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(joueurId);
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(commentaire);
        dest.writeLong(datejeu != null ? datejeu.getTime() : -1L);
        dest.writeInt(score);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Joueurs> CREATOR = new Parcelable.Creator<Joueurs>() {
        @Override
        public Joueurs createFromParcel(Parcel in) {
            return new Joueurs(in);
        }

        @Override
        public Joueurs[] newArray(int size) {
            return new Joueurs[size];
        }
    };
}