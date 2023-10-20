package com.example.thedarenapp;

public class Email {

    private int jour;
    private int mois;
    private int annee;

    private String sujet;
    private String message;


    //setters
    public void setJour (int jour){
        this.jour = jour;
    }
    public void setMois (int mois){
        this.mois = mois;
    }
    public void setAnnee (int annee){
        this.annee = annee;
    }

    public void setSujet (String sujet) {
        this.sujet = sujet;
    }
    public void setMessage (String message) {
        this.sujet = message;
    }

    //tostring
    @Override
    public String toString(){
        StringBuilder retval = new StringBuilder();
        retval.append((jour > 9) ? ""+jour : "0"+jour);
        retval.append((mois > 9) ? ""+mois : "0"+mois);
        retval.append(annee);

        retval.append("+"+sujet);
        retval.append("+"+message);

        return retval.toString();
    }

}
