package com.example.byblos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class branchlist extends AppCompatActivity implements Myadapter.OnNoteListener {


    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    Myadapter myadapter;
    ArrayList<branch> list;
    public branch chosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branchlist);

        recyclerView = findViewById(R.id.branchList);
        databaseReference = FirebaseDatabase.getInstance().getReference("branch");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myadapter = new Myadapter(this, list, this);
        recyclerView.setAdapter(myadapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    branch br = dataSnapshot.getValue(branch.class);
                    list.add(br);
                }
                myadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onNoteClick(int position) {
        chosen = list.get(position);
        Intent intent = new Intent(this, branchProfile.class);
        intent.putExtra("Branch", chosen);
        startActivity(intent);

    }
}