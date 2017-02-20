package com.example.d_odo.todolist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by d-odo on 20/02/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVH> {


    @Override
    public NoteVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(NoteVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class NoteVH extends RecyclerView.ViewHolder{
        public NoteVH(View itemView) {
            super(itemView);
        }
    }
}
