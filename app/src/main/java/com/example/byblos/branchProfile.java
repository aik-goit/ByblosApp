package com.example.byblos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class branchProfile extends AppCompatActivity {

    private TextView address, phone, starthour, finishhour;
    private Button carRental;
    private Button truckRental;
    private Button movingHelp;
    private Button modify;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_profile);

        address = (TextView) findViewById(R.id.address);
        phone = (TextView) findViewById(R.id.phone);

        Intent intent = getIntent();
        branch br = intent.getParcelableExtra("Branch");

        address.setText(br.getName());
        phone.setText(br.getPhonenumber());



        starthour = (TextView) findViewById(R.id.starthour);
        finishhour = (TextView) findViewById(R.id.finishhour);

        starthour.setText(br.getMondayopen());
        finishhour.setText(br.getMondayclose());

        carRental = (Button) findViewById(R.id.carRental);
        truckRental = (Button) findViewById(R.id.truckRental);
        movingHelp = (Button) findViewById(R.id.movingAssistance);
        modify = (Button) findViewById(R.id.modifybutton);

        ArrayList<String> services = br.getServices();

        //ArrayAdapter<String> Hoursadapter = new ArrayAdapter<String>(this, findViewById(R.id.End_hour), hours);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String role = userProfile.role;
                    if(role.equals("Customer")){
                        modify.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        if (!services.contains("Car rental")){
            carRental.setVisibility(View.INVISIBLE);
        }

        if (!services.contains("Truck rental")){
            truckRental.setVisibility(View.INVISIBLE);
        }

        if (!services.contains("Moving Assistance")){
            movingHelp.setVisibility(View.INVISIBLE);
        }


        carRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(branchProfile.this, carform.class));
            }
        });

        truckRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(branchProfile.this, truckform.class));
            }
        });

        movingHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(branchProfile.this, movingform.class));
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(branchProfile.this, ModifyBranch.class);
                intent1.putExtra("Branch", br);
                startActivity(intent1);
            }
        });




    }
}