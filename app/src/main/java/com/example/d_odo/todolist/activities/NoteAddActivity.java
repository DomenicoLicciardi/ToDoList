package com.example.d_odo.todolist.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.d_odo.todolist.R;

/**
 * Created by d-odo on 20/02/2017.
 */

public class NoteAddActivity extends AppCompatActivity{
    EditText titleET, bodyET, dateDeadlineET;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        titleET= (EditText) findViewById(R.id.note_add_title);
        bodyET= (EditText) findViewById(R.id.note_add_body);
        //dateDeadlineET= (EditText) findViewById(R.id.note_add_date_deadline);


        intent = getIntent();

        if(intent!= null){
            if(intent.getIntExtra(MainActivity.ACTION_MODE,0) == MainActivity.NOTE_EDIT_REQUEST) {
                titleET.setText(intent.getStringExtra(MainActivity.NOTE_TITLE_KEY));
                bodyET.setText(intent.getStringExtra(MainActivity.NOTE_BODY_KEY));
                //dateDeadlineET.setText(intent.getStringExtra(MainActivity.NOTE_DEADLINE_KEY));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();

        if(id==R.id.action_confirm) {
            confirmNote();
            return true;
        }
        if(id == android.R.id.home) {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void confirmNote() {
        Intent i = new Intent();
        i.putExtra(MainActivity.NOTE_TITLE_KEY,titleET.getText().toString());
        i.putExtra(MainActivity.NOTE_BODY_KEY,bodyET.getText().toString());
        //i.putExtra(MainActivity.NOTE_DEADLINE_KEY,dateDeadlineET.getText().toString());
        setResult(Activity.RESULT_OK,i);
        finish();

    }
}
