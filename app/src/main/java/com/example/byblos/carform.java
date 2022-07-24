package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class carform extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    boolean nameHidden = false;
    boolean licenseHidden = false;
    boolean pickupHidden = false;
    boolean typeHidden = false;
    boolean returnHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_rental_form);

        Spinner licensespinner = findViewById(R.id.licensespinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.licenses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        licensespinner.setAdapter(adapter);
        licensespinner.setOnItemSelectedListener(this);

        Spinner carpinner = findViewById(R.id.carpreferencespinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.cartypes, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carpinner.setAdapter(adapter1);
        carpinner.setOnItemSelectedListener(this);

        Button hideName = (Button) findViewById(R.id.hideName);
        Button hideLicense = (Button) findViewById(R.id.hideLicense);
        Button hidePickup = (Button) findViewById(R.id.hidePickup);
        Button hideType = (Button) findViewById(R.id.hideType);
        Button hideReturn = (Button) findViewById(R.id.hideReturn);
        Button submit = (Button) findViewById(R.id.submit);

        hideName.setVisibility(View.INVISIBLE);
        hideLicense.setVisibility(View.INVISIBLE);
        hidePickup.setVisibility(View.INVISIBLE);
        hideType.setVisibility(View.INVISIBLE);
        hideReturn.setVisibility(View.INVISIBLE);

        DAOform dao = new DAOform();

        AdminSettings.update();

        if (adminActivity.isAdmin==true){
            hideName.setVisibility(View.VISIBLE);
            hideLicense.setVisibility(View.VISIBLE);
            hidePickup.setVisibility(View.VISIBLE);
            hideType.setVisibility(View.VISIBLE);
            hideReturn.setVisibility(View.VISIBLE);
            submit.setVisibility(View.INVISIBLE);
        }
        TextView firstNameTitle = (TextView) findViewById(R.id.firstnametitle);
        EditText firstNameInfo = (EditText) findViewById(R.id.firstnameinfo);

        TextView licenseTypeTitle = (TextView) findViewById(R.id.licensetypetitle);
        Spinner licenseSpinner = (Spinner) findViewById(R.id.licensespinner);

        TextView preferredCar = (TextView) findViewById(R.id.preferredcar);
        Spinner carPreferenceSpinner = (Spinner) findViewById(R.id.carpreferencespinner);

        TextView pickupDateAndTime = (TextView) findViewById(R.id.pickupdateandtime);
        EditText pickupDate = (EditText) findViewById(R.id.pickupdate);
        EditText pickupTime = (EditText) findViewById(R.id.pickuptime);

        TextView returnDateAndTime = (TextView) findViewById(R.id.returndateandtime);
        EditText returnDate = (EditText) findViewById(R.id.returndate);
        EditText returnTime = (EditText) findViewById(R.id.returntime);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = null;
                String licensetype =null;
                String preferredType = null;
                String pickupDat = null;
                String pickuptime = null;
                String returndate = null;
                String returntime = null;

                if (!AdminSettings.CarRental.nameHidden) {
                    fullname = firstNameInfo.getText().toString();
                }
                if (!AdminSettings.CarRental.licenseHidden) {
                    licensetype = licenseSpinner.getSelectedItem().toString();
                }
                if (!AdminSettings.CarRental.typeHidden) {
                    preferredType = preferredCar.getText().toString();
                }
                if (!AdminSettings.CarRental.pickupHidden) {
                    pickuptime = pickupTime.getText().toString();
                    pickupDat = pickupDate.getText().toString();
                }
                if (!AdminSettings.CarRental.returnHidden) {
                    returndate = returnDate.getText().toString();
                    returntime = returnTime.getText().toString();
                }
                Request request = new Request("car", fullname, licensetype, preferredType, pickupDat, pickuptime, returndate, returntime);
                Requests.requests.add(request);
                Form form = new Form("car rental", fullname, licensetype, preferredType, pickupDat, pickuptime, returndate, returntime);
                dao.add(form);
                Toast.makeText(carform.this, "Request submitted", Toast.LENGTH_SHORT).show();
            }
        });

        if (AdminSettings.CarRental.nameHidden) {
            firstNameTitle.setVisibility(View.INVISIBLE);
            firstNameInfo.setVisibility(View.INVISIBLE);
            nameHidden = true;
        } else {
            firstNameTitle.setVisibility(View.VISIBLE);
            firstNameInfo.setVisibility(View.VISIBLE);
            nameHidden = false;
        }

        if (AdminSettings.CarRental.licenseHidden) {
            licenseTypeTitle.setVisibility(View.INVISIBLE);
            licenseSpinner.setVisibility(View.INVISIBLE);
            licenseHidden = true;
        } else {
            licenseTypeTitle.setVisibility(View.VISIBLE);
            licenseSpinner.setVisibility(View.VISIBLE);
            licenseHidden = false;
        }

        if (AdminSettings.CarRental.typeHidden) {
            preferredCar.setVisibility(View.INVISIBLE);
            carPreferenceSpinner.setVisibility(View.INVISIBLE);
            typeHidden = true;
        } else {
            preferredCar.setVisibility(View.VISIBLE);
            carPreferenceSpinner.setVisibility(View.VISIBLE);
            typeHidden = false;
        }

        if (AdminSettings.CarRental.pickupHidden) {
            pickupDateAndTime.setVisibility(View.INVISIBLE);
            pickupDate.setVisibility(View.INVISIBLE);
            pickupTime.setVisibility(View.INVISIBLE);
            pickupHidden = true;
        } else {
            pickupDateAndTime.setVisibility(View.VISIBLE);
            pickupDate.setVisibility(View.VISIBLE);
            pickupTime.setVisibility(View.VISIBLE);
            pickupHidden = false;
        }

        if (AdminSettings.CarRental.returnHidden) {
            returnDateAndTime.setVisibility(View.INVISIBLE);
            returnDate.setVisibility(View.INVISIBLE);
            returnTime.setVisibility(View.INVISIBLE);
            returnHidden = true;
        } else {
            returnDateAndTime.setVisibility(View.VISIBLE);
            returnDate.setVisibility(View.VISIBLE);
            returnTime.setVisibility(View.VISIBLE);
            returnHidden = false;

        }



        hideName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameHidden==false){
                    firstNameTitle.setVisibility(View.INVISIBLE);
                    firstNameInfo.setVisibility(View.INVISIBLE);
                    nameHidden = true;
                    update();
                }
                else if (nameHidden==true){
                    firstNameTitle.setVisibility(View.VISIBLE);
                    firstNameInfo.setVisibility(View.VISIBLE);
                    nameHidden = false;
                    update();
                }
            }
        });

        hideLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (licenseHidden==false){
                    licenseTypeTitle.setVisibility(View.INVISIBLE);
                    licenseSpinner.setVisibility(View.INVISIBLE);
                    licenseHidden = true;
                    update();
                }
                else if (licenseHidden==true){
                    licenseTypeTitle.setVisibility(View.VISIBLE);
                    licenseSpinner.setVisibility(View.VISIBLE);
                    licenseHidden = false;
                    update();
                }
            }
        });

        hideType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeHidden==false){
                    preferredCar.setVisibility(View.INVISIBLE);
                    carPreferenceSpinner.setVisibility(View.INVISIBLE);
                    typeHidden = true;
                    update();
                }
                else if (typeHidden==true){
                    preferredCar.setVisibility(View.VISIBLE);
                    carPreferenceSpinner.setVisibility(View.VISIBLE);
                    typeHidden = false;
                    update();
                }
            }
        });

        hidePickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pickupHidden==false){
                    pickupDateAndTime.setVisibility(View.INVISIBLE);
                    pickupDate.setVisibility(View.INVISIBLE);
                    pickupTime.setVisibility(View.INVISIBLE);
                    pickupHidden = true;
                    update();
                }
                else if (pickupHidden==true){
                    pickupDateAndTime.setVisibility(View.VISIBLE);
                    pickupDate.setVisibility(View.VISIBLE);
                    pickupTime.setVisibility(View.VISIBLE);
                    pickupHidden = false;
                    update();
                }
            }
        });

        hideReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (returnHidden==false){
                    returnDateAndTime.setVisibility(View.INVISIBLE);
                    returnDate.setVisibility(View.INVISIBLE);
                    returnTime.setVisibility(View.INVISIBLE);
                    returnHidden = true;
                    update();
                }
                else if (returnHidden==true){
                    returnDateAndTime.setVisibility(View.VISIBLE);
                    returnDate.setVisibility(View.VISIBLE);
                    returnTime.setVisibility(View.VISIBLE);
                    returnHidden = false;
                    update();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void update() {
        AdminSettings.CarRental.nameHidden = nameHidden;
        AdminSettings.CarRental.licenseHidden = licenseHidden;
        AdminSettings.CarRental.pickupHidden = pickupHidden;
        AdminSettings.CarRental.typeHidden = typeHidden;
        AdminSettings.CarRental.returnHidden = returnHidden;
        AdminSettings.update();
    }

}