package com.example.d_odo.todolist.models;

import java.text.DateFormat;

/**
 * Created by d-odo on 20/02/2017.
 */

public class Note {
    public String titolo;
    String dataCreazione;

    DateFormat date
    public Note(String titolo, String corpo, String dataScadenza) {
        this.titolo = titolo;
        this.corpo = corpo;
        this.dataScadenza = dataScadenza;
    }

    public Note(){}

    String ultimaModifica;
    String corpo;
    String dataScadenza;



    enum stato {DA_FARE, ARCHIVIATE};

    public String getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(String dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getUltimaModifica() {
        return ultimaModifica;
    }

    public void setUltimaModifica(String ultimaModifica) {
        this.ultimaModifica = ultimaModifica;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }
}
