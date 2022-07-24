package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;


public class BranchForm extends AppCompatActivity {

    private Button setBranchProfile;
    private TextView welcome;
    public static EditText addressTwo;
    public static EditText phoneTwo;
    public static Button mondayOpenTimes;
    public static Button mondayCloseTimes;
    public static boolean mandatoryAddress;
    public static boolean mandatoryPhone;

    int mondayOpenHour;
    int mondayOpenMinute;

    int mondayCloseHour;
    int mondayCloseMinute;

    ArrayList<String> services = new ArrayList<>() ;

    public CheckBox carrental, truckrental, movingassistance;


    String mondayOpen ="";
    String mondayClose ="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_form);

        setBranchProfile  = (Button) findViewById(R.id.setinfo);
        addressTwo = (EditText) findViewById(R.id.editTextTextPostalAddress);
        phoneTwo = (EditText) findViewById(R.id.editTextPhone);

        mondayOpenTimes = (Button) findViewById(R.id.mondayOpen);
        mondayCloseTimes = (Button) findViewById(R.id.mondayClose);

        carrental = (CheckBox) findViewById(R.id.checkCar);
        truckrental = (CheckBox) findViewById(R.id.checkTruck);
        movingassistance = (CheckBox) findViewById(R.id.checkAssistance);


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



        DAObranch daObranch = new DAObranch();

        setBranchProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addressTwo.getText().toString().equals("") || phoneTwo.getText().toString().equals("")){
                    mandatoryAddress = false;
                }
                else {
                    mandatoryPhone = true;
                }
                branch br = new branch(addressTwo.getText().toString(), phoneTwo.getText().toString(), mondayOpen,mondayClose, services);
                daObranch.add(br);
                startActivity(new Intent(BranchForm.this, EmployeeWelcomeActivity.class));
            }
        });


    }


    public void mondayOpenTime(View view) {
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


    public void mondayCloseTime(View view) {
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