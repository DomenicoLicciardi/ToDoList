package com.example.d_odo.todolist.models;

/**
 * Created by d-odo on 20/02/2017.
 */

public class Note {
    String titolo;
    String dataCreazione;
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
