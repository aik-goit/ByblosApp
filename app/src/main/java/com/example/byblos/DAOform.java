package com.example.byblos;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOform {

    private DatabaseReference databaseReference;

    public DAOform(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Form.class.getSimpleName());
    }

    public Task<Void> add(Form form){
        return databaseReference.push().setValue(form);
    }
}
