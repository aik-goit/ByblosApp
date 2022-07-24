package com.example.byblos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmployeeWelcomeActivity extends AppCompatActivity {



    private Button logout;
    private Button form;
    private Button profile;
    private TextView test;
    public static Switch carRental;
    public static Switch truckRental;
    public static Switch movingAssistance;

    private Button serviceRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userActivity.employeeOpened = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_welcome);

        logout = (Button) findViewById(R.id.logoutbutton);
        form  = (Button) findViewById(R.id.branchForm);
        test = (TextView) findViewById(R.id.welcomeadmin);

        serviceRequests = (Button) findViewById(R.id.ServiceRequests);

        serviceRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmployeeWelcomeActivity.this, ServiceRequests.class));
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                startActivity(new Intent(EmployeeWelcomeActivity.this, MainActivity.class));
            }
        });


        form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(EmployeeWelcomeActivity.this, BranchForm.class));
            }
        });

        profile = (Button) findViewById(R.id.branchProfile);


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployeeWelcomeActivity.this, branchlist.class));
            }
        });

        carRental = (Switch) findViewById(R.id.carRental);
        if (!AdminSettings.CarRental.toggle){
            carRental.setVisibility(View.INVISIBLE);
        }
        truckRental = (Switch) findViewById(R.id.truckRental);
        if (!AdminSettings.TruckRental.toggle){
            truckRental.setVisibility(View.INVISIBLE);
        }
        movingAssistance = (Switch) findViewById(R.id.movingAssistance);
        if (!AdminSettings.MovingAssistance.toggle){
            movingAssistance.setVisibility(View.INVISIBLE);
        }
    }
}