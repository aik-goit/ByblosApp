package com.example.byblos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder>  {

    Context context;

    ArrayList<branch> list;
    private OnNoteListener monNoteListener;

    public Myadapter(Context context, ArrayList<branch> list, OnNoteListener onNoteListener) {
        this.context = context;
        this.list = list;
        this.monNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v, monNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        branch branch = list.get(position);
        holder.address.setText(branch.getName());
        holder.services.setText(branch.getServices().toString());
        holder.workinghours.setText("From "+branch.getMondayopen()+" to "+branch.getMondayclose());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView address,services,workinghours;
        OnNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            this.onNoteListener = onNoteListener;
            address = itemView.findViewById(R.id.addressdb);
            services = itemView.findViewById(R.id.servicesOffereddb);
            workinghours = itemView.findViewById(R.id.workinghoursdb);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);

    }
}
