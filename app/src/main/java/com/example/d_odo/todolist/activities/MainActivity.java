package com.example.d_odo.todolist.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.d_odo.todolist.R;
import com.example.d_odo.todolist.adapters.NoteAdapter;
import com.example.d_odo.todolist.database.Database;
import com.example.d_odo.todolist.models.Note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by d-odo on 20/02/2017.
 */

public class MainActivity extends AppCompatActivity{

    //CONSTANTS
    public static final String NOTE_POSITION="NOTE_POSITION";
    public static final String NOTE_NAME = "NOTE_NAME";
    public static final int NOTE_ADD_REQUEST= 1001;
    public static final int NOTE_EDIT_REQUEST =1002;
    public static final String ACTION_MODE = "ACTION_MODE";
    public static final int EDITE_MODE = 1;
    public static final int CREATE_MODE = 2;

    //keys
    public static final String NOTE_TITLE_KEY ="NOTE_TITLE_KEY";
    public static final String NOTE_BODY_KEY = "NOTE_BODY_KEY";
    public static final String NOTE_DEADLINE_KEY = "NOTE_DEADLINE_KEY";



    //recyclerView items
    RecyclerView noteRV;
    StaggeredGridLayoutManager layoutManager;
    NoteAdapter adapter;

    Database dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noteRV = (RecyclerView) findViewById(R.id.note_rv);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        adapter = new NoteAdapter(this);

        noteRV.setAdapter(adapter);
        noteRV.setLayoutManager(layoutManager);

        registerForContextMenu(noteRV);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_note);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NoteAddActivity.class);
                i.putExtra(ACTION_MODE, CREATE_MODE);
                startActivityForResult(i, NOTE_ADD_REQUEST);
            }
        });

        if(getIntent() != null) {
            if(getIntent().getAction() != null) {
                if(getIntent().getAction().equals(Intent.ACTION_SEND)){
                    Intent i = new Intent(MainActivity.this, NoteAddActivity.class);
                    i.putExtra(ACTION_MODE, CREATE_MODE);
                    i.putExtra(NOTE_BODY_KEY,getIntent().getStringExtra(Intent.EXTRA_TEXT));
                    Log.d("MainActivity",getIntent().getStringExtra(Intent.EXTRA_TEXT));
                    startActivityForResult(i, NOTE_ADD_REQUEST);
                }
            }
        }


        dbHandler = new Database(this);
        adapter.setDataSet(dbHandler.getAllNotes());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NOTE_ADD_REQUEST && resultCode == Activity.RESULT_OK ) {
            Note note = new Note();
            note.setTitle(data.getStringExtra(NOTE_TITLE_KEY));
            note.setBody(data.getStringExtra(NOTE_BODY_KEY));
            //note.setDataScadenza(data.getStringExtra(NOTE_DEADLINE_KEY));
            dbHandler.addNote(note);
            adapter.addNote(note);
        }

        if(requestCode == NOTE_EDIT_REQUEST && resultCode == RESULT_OK) {

            editingNote.setTitle(data.getStringExtra(NOTE_TITLE_KEY));
            editingNote.setTitle(data.getStringExtra(NOTE_BODY_KEY));
            dbHandler.updateNote(editingNote);
            adapter.updateNote(editingNote,adapter.getPosition());
        }

    }
    Note editingNote;

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id= item.getItemId();
        switch (id) {
            case R.id.action_delete:
                //remove from adapter
                adapter.removeNote(adapter.getPosition());
                //remove record
                dbHandler.deletNote(adapter.getNote(adapter.getPosition()));
                break;

            case R.id.action_edit:
                editingNote = adapter.getNote(adapter.getPosition());
                Intent i = new Intent(this,NoteAddActivity.class);
                i.putExtra(ACTION_MODE,EDITE_MODE);
                i.putExtra(NOTE_TITLE_KEY,editingNote.getTitle());
                i.putExtra(NOTE_BODY_KEY,editingNote.getTitle());
                startActivityForResult(i,NOTE_EDIT_REQUEST);
                break;
        }
        return super.onContextItemSelected(item);
    }
}

