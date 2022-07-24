package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;

public class ModifyBranch extends AppCompatActivity {

    public static Button mondayOpenTimes;
    public static Button mondayCloseTimes;
    public static TextView addressTwo;
    public static EditText phoneTwo;

    ArrayList<String> services = new ArrayList<>() ;

    public static Button updatebranch;

    int mondayOpenHour;
    int mondayOpenMinute;

    int mondayCloseHour;
    int mondayCloseMinute;

    String mondayOpen ="";
    String mondayClose ="";

    public CheckBox carrental, truckrental, movingassistance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_branch);

        Intent intent = getIntent();
        branch br = intent.getParcelableExtra("Branch");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseRef = database.getReference();

        mondayOpenTimes = (Button) findViewById(R.id.hourOpen);
        mondayCloseTimes = (Button) findViewById(R.id.hourClose);
        updatebranch = (Button) findViewById(R.id.updateinfo);

        phoneTwo = (EditText) findViewById(R.id.editTextPhone2);
        addressTwo = (TextView) findViewById(R.id.editTextTextPostalAddress1);
        addressTwo.setText(br.getName());

        carrental = (CheckBox) findViewById(R.id.checkBox);
        truckrental = (CheckBox) findViewById(R.id.checkBox3);
        movingassistance = (CheckBox) findViewById(R.id.checkBox2);

        carrental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(carrental.isChecked()){
                    services.add("Car rental");
                }else{
                    services.remove("Car rental");
                }
            }
        });

        truckrental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(truckrental.isChecked()){
                    services.add("Truck rental");
                }else{
                    services.remove("Truck rental");
                }
            }
        });

        movingassistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(movingassistance.isChecked()){
                    services.add("Moving Assistance");
                }else{
                    services.remove("Moving Assistance");
                }
            }
        });

        updatebranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPhone = phoneTwo.getText().toString();
                if(newPhone != br.getPhonenumber()){
                    mDatabaseRef.child("branch").child(br.getName()).child("phonenumber").setValue(newPhone);
                }
                if(mondayOpen != br.getMondayopen()){
                    mDatabaseRef.child(br.getName()).child("mondayopen").setValue(mondayOpen);
                }
                if(mondayClose != br.getMondayclose()){
                    mDatabaseRef.child(br.getName()).child("mondayclose").setValue(mondayClose);
                }
                if(services != br.getServices()){
                    mDatabaseRef.child(br.getName()).child("services").setValue(services);
                }
            }
        });
    }

    public void hourOpenTime(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                mondayOpenHour = selectedHour;
                mondayOpenMinute = selectedMinute;
                mondayOpenTimes.setText(String.format(Locale.getDefault(), "%02d:%02d", mondayOpenHour, mondayOpenMinute));
                mondayOpen = String.format(Locale.getDefault(), "%02d:%02d", mondayOpenHour, mondayOpenMinute);
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, mondayOpenHour, mondayOpenMinute, false);
        timePickerDialog.setTitle("Pick time");
        timePickerDialog.show();
    }


    public void hourCloseTime(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                mondayCloseHour = selectedHour;
                mondayCloseMinute = selectedMinute;
                mondayCloseTimes.setText(String.format(Locale.getDefault(), "%02d:%02d", mondayCloseHour, mondayCloseMinute));
                mondayClose = String.format(Locale.getDefault(), "%02d:%02d", mondayCloseHour, mondayCloseMinute);
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, mondayCloseHour, mondayCloseMinute, false);
        timePickerDialog.setTitle("Pick time");
        timePickerDialog.show();
    }
}