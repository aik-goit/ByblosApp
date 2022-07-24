package com.example.byblos;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAObranch {

    private DatabaseReference databaseReference;
    public DAObranch(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(branch.class.getSimpleName());
    }

    public Task<Void> add(branch br){
        return databaseReference.push().setValue(br);
    }
}
