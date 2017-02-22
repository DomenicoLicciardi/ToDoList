package com.example.d_odo.todolist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.d_odo.todolist.activities.MainActivity;
import com.example.d_odo.todolist.models.Note;

import java.util.ArrayList;

/**
 * Created by d-odo on 22/02/2017.
 */

public class Database extends SQLiteOpenHelper {

    //Notes Table Columns names
    private static final String KEY_ID ="id";
    private static final String KEY_TITLE ="title";
    private static final String KEY_BODY ="body";

    //Database version
    private static final int DATABASE_VERSION= 2;

    //Database name
    private static final String DATABASE_NAME = "notes";

    //Contacts table name
    private static final String TABLE_NOTES ="contacts";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_NOTE_DATABASE = "CREATE TABLE " + TABLE_NOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_BODY + " TEXT" + ")";
        db.execSQL(CREATE_NOTE_DATABASE);
    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        // Create table again
        onCreate(db);
    }

    public void addNote(Note note) {
        Log.d("Appena entrato in addDB","entro");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, note.getTitle());
        values.put(KEY_BODY, note.getBody());

        //Inserting Row
        db.insert(TABLE_NOTES, null, values);
        db.close(); //Closing database connection
    }

    //Getting All Notes
    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notesList = new ArrayList<>();
        //Select All Query

        String selectQuery = "SELECT * FROM " + TABLE_NOTES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all row adding to list
        if(cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setBody(cursor.getString(2));
                //Adding note to list
                notesList.add(note);
            } while (cursor.moveToNext());
        }

        //return notes list
        return notesList;
    }

    // Updating single note
    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, note.getTitle());
        values.put(KEY_BODY, note.getBody());
        // updating row
        return db.update(TABLE_NOTES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    // Deleting single note
    public void deletNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTES, KEY_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }



}
