package com.example.d_odo.todolist.adapters;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.d_odo.todolist.R;
import com.example.d_odo.todolist.activities.MainActivity;
import com.example.d_odo.todolist.activities.NoteAddActivity;
import com.example.d_odo.todolist.models.Note;

import java.util.ArrayList;

/**
 * Created by d-odo on 20/02/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVH> {

    private ArrayList<Note> dataSet = new ArrayList<>();
    private int position;

    public void updateNote(Note note, int position) {
        dataSet.set(position,note);
        notifyItemChanged(0);
    }

    public Note getNote(int position) {
        return dataSet.get(position);
    }

    public void setDataSet(ArrayList<Note> dataSet) {
        this.dataSet= dataSet;
        notifyDataSetChanged();
    }

    public void addNote(Note note) {
        dataSet.add(0,note);
        notifyItemInserted(0);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position= position;
    }

    @Override
    public NoteVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteVH(view);
    }

    @Override
    public void onBindViewHolder(NoteVH holder, int position) {
        Note note= dataSet.get(position);
        holder.titleTv.setText(note.getTitolo());
        holder.dateCreateTv.setText(note.getDataCreazione());
        holder.lastUpdateTv.setText(note.getUltimaModifica());
        holder.bodyTv.setText(note.getCorpo());
        holder.dateDeadlineTv.setText(note.getDataScadenza());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void removeNote(int position) {
        dataSet.remove(position);
    }


    public class NoteVH extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleTv,dateCreateTv,lastUpdateTv,bodyTv,dateDeadlineTv;

        public NoteVH(View itemView) {
            super(itemView);
            titleTv= (TextView)itemView.findViewById(R.id.note_title);
            dateCreateTv= (TextView) itemView.findViewById(R.id.note_date_create);
            lastUpdateTv= (TextView) itemView.findViewById(R.id.note_last_update);
            bodyTv= (TextView) itemView.findViewById(R.id.note_body);
            dateDeadlineTv= (TextView) itemView.findViewById(R.id.note_date_deadline);
        }

        @Override
        public void onClick(View v) {
            EditText title = (EditText) v.findViewById(R.id.note_add_title);
            EditText body = (EditText) v.findViewById(R.id.note_add_body);
            EditText dateDeadline = (EditText) v.findViewById(R.id.note_add_date_deadline);
            Intent i = new Intent();
            i.setClass(v.getContext(),NoteAddActivity.class);
            ((MainActivity) v.getContext()).startActivityForResult(i,MainActivity.NOTE_ADD_REQUEST);
    }
    }

}
