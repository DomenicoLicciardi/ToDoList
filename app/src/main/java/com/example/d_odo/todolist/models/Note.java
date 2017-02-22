package com.example.d_odo.todolist.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by d-odo on 20/02/2017.
 */

public class Note {
    public String title;
    String dataCreazione;
    String ultimaModifica;
    String body;
    String dataScadenza;
private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    DateFormat date = new SimpleDateFormat("MM/dd/yy");
    Date today = Calendar.getInstance().getTime();


    public Note(String title, String body, String dataCreazione, String dataScadenza) {
        this.title = title;
        this.body = body;
        this.dataCreazione= date.format(today).toString();
        this.dataScadenza = dataScadenza;
    }

    public Note(){}




    enum stato {DA_FARE, ARCHIVIATE};

    public String getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(String dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUltimaModifica() {
        return ultimaModifica;
    }

    public void setUltimaModifica(String ultimaModifica) {
        this.ultimaModifica = ultimaModifica;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }
}
