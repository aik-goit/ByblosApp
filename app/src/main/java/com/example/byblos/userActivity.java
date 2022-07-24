package com.example.byblos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    private Button logout;
    public static Boolean employeeOpened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        logout = (Button) findViewById(R.id.logoutbutton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(userActivity.this, MainActivity.class));
            }
        });

        if (!MainActivity.editTextemail.getText().toString().trim().equals("customer")){
            user = FirebaseAuth.getInstance().getCurrentUser();
            reference = FirebaseDatabase.getInstance().getReference("Users");
            userID = user.getUid();
        }


        final TextView wecloneTextView = (TextView) findViewById(R.id.welcome);
        final TextView fullnameTextView = (TextView) findViewById(R.id.fullname);
        final TextView emailTextView = (TextView) findViewById(R.id.useremail);
        final TextView roleTextView = (TextView) findViewById(R.id.userrole);
        String email = MainActivity.editTextemail.getText().toString().trim();

        if (!MainActivity.editTextemail.getText().toString().trim().equals("customer")) {
            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);
                    if (userProfile != null) {
                        String fullname = userProfile.firstName + " " + userProfile.lastName;
                        String role = "Customer";

                        wecloneTextView.setText("Welcome " + email + "!");
                        fullnameTextView.setText(fullname);
                        emailTextView.setText(email);
                        roleTextView.setText(role);
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Toast.makeText(userActivity.this, "Something wrong happened !", Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            String role = "Customer";

            wecloneTextView.setText("Welcome " + email + "!");
            fullnameTextView.setText("Customer");
            emailTextView.setText(email);
            roleTextView.setText(role);
        }

        Button carRental = (Button) findViewById(R.id.carRental);
        carRental.setVisibility(View.INVISIBLE);
        if (employeeOpened){
            if (EmployeeWelcomeActivity.carRental.isChecked()){
                carRental.setVisibility(View.VISIBLE);
            }
        }

        Button truckRental = (Button) findViewById(R.id.truckRental);
        truckRental.setVisibility(View.INVISIBLE);
        if (employeeOpened) {
            if (EmployeeWelcomeActivity.truckRental.isChecked()) {
                truckRental.setVisibility(View.VISIBLE);
            }
        }

        Button movingAssistance = (Button) findViewById(R.id.movingAssistance);
        movingAssistance.setVisibility(View.INVISIBLE);
        if (employeeOpened) {
            if (EmployeeWelcomeActivity.movingAssistance.isChecked()) {
                movingAssistance.setVisibility(View.VISIBLE);
            }
        }

        carRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(userActivity.this, carform.class));
                adminActivity.isAdmin=false;
            }
        });

        truckRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(userActivity.this, truckform.class));
                adminActivity.isAdmin=false;
            }
        });

        movingAssistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(userActivity.this, movingform.class));
                adminActivity.isAdmin=false;
            }
        });

        Button search = (Button) findViewById(R.id.buttonSearch);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(userActivity.this, branchlist.class));
            }
        });
    }
}