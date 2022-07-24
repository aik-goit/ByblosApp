package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class truckform extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    boolean nameHidden = false;
    boolean licenseHidden = false;
    boolean pickupHidden = false;
    boolean emailHidden = false;
    boolean areaHidden = false;
    boolean returnHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.truck_rental_form);

        Spinner licpinner = findViewById(R.id.spinnerlicense);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.licenses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        licpinner.setAdapter(adapter);
        licpinner.setOnItemSelectedListener(this);

        Button hideName = (Button) findViewById(R.id.hideNameAndDateOfBirth);
        Button hideLicense = (Button) findViewById(R.id.hideTypeOfLicense);
        Button hidePickup = (Button) findViewById(R.id.hidePickupTime);
        Button hideEmail = (Button) findViewById(R.id.hideTruckEmail);
        Button hideArea = (Button) findViewById(R.id.hideArea);
        Button hideReturn = (Button) findViewById(R.id.hideReturnTime);
        Button submit = (Button) findViewById(R.id.submit);

        hideName.setVisibility(View.INVISIBLE);
        hideLicense.setVisibility(View.INVISIBLE);
        hidePickup.setVisibility(View.INVISIBLE);
        hideEmail.setVisibility(View.INVISIBLE);
        hideArea.setVisibility(View.INVISIBLE);
        hideReturn.setVisibility(View.INVISIBLE);

        if (adminActivity.isAdmin==true){
            hideName.setVisibility(View.VISIBLE);
            hideLicense.setVisibility(View.VISIBLE);
            hidePickup.setVisibility(View.VISIBLE);
            hideEmail.setVisibility(View.VISIBLE);
            hideArea.setVisibility(View.VISIBLE);
            hideReturn.setVisibility(View.VISIBLE);
            submit.setVisibility(View.INVISIBLE);
        }

        TextView firstNameTitle = (TextView) findViewById(R.id.nametitle);
        EditText firstNameInfo = (EditText) findViewById(R.id.nameinfo1);
        EditText birthInfo = (EditText) findViewById(R.id.dateofbirth1);

        TextView licenseTypeTitle = (TextView) findViewById(R.id.licensetitle1);
        Spinner licenseSpinner = (Spinner) findViewById(R.id.spinnerlicense);

        TextView emailTitle = (TextView) findViewById(R.id.emailtitle1);
        EditText emailInput = (EditText) findViewById(R.id.emailinfo1);

        TextView pickupDateAndTime = (TextView) findViewById(R.id.textView7);
        EditText pickupDate = (EditText) findViewById(R.id.editTextTextPersonName5);
        EditText pickupTime = (EditText) findViewById(R.id.editTextTextPersonName6);

        TextView returnDateAndTime = (TextView) findViewById(R.id.textView8);
        EditText returnDate = (EditText) findViewById(R.id.editTextTextPersonName7);
        EditText returnTime = (EditText) findViewById(R.id.editTextTextPersonName8);

        TextView areaText = (TextView) findViewById(R.id.titlekms);
        EditText distance = (EditText) findViewById(R.id.kms);
        EditText location = (EditText) findViewById(R.id.area);

        DAOform dao = new DAOform();
        AdminSettings.update();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = null;
                String birthdate = null;
                String email = null;
                String kilometres = null;
                String area = null;
                String licensetype =null;
                String pickupDat = null;
                String pickuptime = null;
                String returndate = null;
                String returntime = null;

                if (!AdminSettings.TruckRental.nameHidden) {
                    fullname = firstNameInfo.getText().toString();
                    birthdate = birthInfo.getText().toString();
                }
                if (!AdminSettings.TruckRental.emailHidden) {
                    email = emailInput.getText().toString();
                }
                if (!AdminSettings.TruckRental.areaHidden) {
                    kilometres = distance.getText().toString();
                    area = location.getText().toString();
                }
                if (!AdminSettings.TruckRental.licenseHidden) {
                    licensetype = licenseSpinner.getSelectedItem().toString();
                }
                if (!AdminSettings.TruckRental.pickupHidden) {
                    pickuptime = pickupTime.getText().toString();
                    pickupDat = pickupDate.getText().toString();
                }
                if (!AdminSettings.TruckRental.returnHidden) {
                    returndate = returnDate.getText().toString();
                    returntime = returnTime.getText().toString();
                }
                Request request = new Request("truck", fullname, birthdate,email,kilometres,area,licensetype,pickupDat,pickuptime, returndate,returntime);
                Requests.requests.add(request);
                Form form = new Form("truck rental", fullname, email,licensetype,kilometres,area,pickupDat,pickuptime, returndate,returntime);
                dao.add(form);
                Toast.makeText(truckform.this, "Request submitted", Toast.LENGTH_SHORT).show();
            }
        });

        if (AdminSettings.TruckRental.nameHidden) {
            firstNameTitle.setVisibility(View.INVISIBLE);
            firstNameInfo.setVisibility(View.INVISIBLE);
            birthInfo.setVisibility(View.INVISIBLE);
            nameHidden = true;
        } else {
            firstNameTitle.setVisibility(View.VISIBLE);
            firstNameInfo.setVisibility(View.VISIBLE);
            birthInfo.setVisibility(View.VISIBLE);
            nameHidden = false;
        }

        if (AdminSettings.TruckRental.licenseHidden) {
            licenseTypeTitle.setVisibility(View.INVISIBLE);
            licenseSpinner.setVisibility(View.INVISIBLE);
            licenseHidden = true;
        } else {
            licenseTypeTitle.setVisibility(View.VISIBLE);
            licenseSpinner.setVisibility(View.VISIBLE);
            licenseHidden = false;
        }

        if (AdminSettings.TruckRental.emailHidden) {
            emailTitle.setVisibility(View.INVISIBLE);
            emailInput.setVisibility(View.INVISIBLE);
            emailHidden = true;
        } else {
            emailTitle.setVisibility(View.VISIBLE);
            emailInput.setVisibility(View.VISIBLE);
            emailHidden = false;
        }

        if (AdminSettings.TruckRental.pickupHidden) {
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

        if (AdminSettings.TruckRental.returnHidden) {
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

        if (AdminSettings.TruckRental.areaHidden) {
            areaText.setVisibility(View.INVISIBLE);
            distance.setVisibility(View.INVISIBLE);
            location.setVisibility(View.INVISIBLE);
            areaHidden = true;
        } else {
            areaText.setVisibility(View.VISIBLE);
            distance.setVisibility(View.VISIBLE);
            location.setVisibility(View.VISIBLE);
            areaHidden = false;
        }



        hideName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameHidden==false){
                    firstNameTitle.setVisibility(View.INVISIBLE);
                    firstNameInfo.setVisibility(View.INVISIBLE);
                    birthInfo.setVisibility(View.INVISIBLE);
                    nameHidden = true;
                }
                else if (nameHidden==true){
                    firstNameTitle.setVisibility(View.VISIBLE);
                    firstNameInfo.setVisibility(View.VISIBLE);
                    birthInfo.setVisibility(View.VISIBLE);
                    nameHidden = false;
                }
                update();
            }
        });

        hideLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (licenseHidden==false){
                    licenseTypeTitle.setVisibility(View.INVISIBLE);
                    licenseSpinner.setVisibility(View.INVISIBLE);
                    licenseHidden = true;
                }
                else if (licenseHidden==true){
                    licenseTypeTitle.setVisibility(View.VISIBLE);
                    licenseSpinner.setVisibility(View.VISIBLE);
                    licenseHidden = false;
                }
                update();
            }
        });

        hideEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailHidden==false){
                    emailTitle.setVisibility(View.INVISIBLE);
                    emailInput.setVisibility(View.INVISIBLE);
                    emailHidden = true;
                }
                else if (emailHidden==true){
                    emailTitle.setVisibility(View.VISIBLE);
                    emailInput.setVisibility(View.VISIBLE);
                    emailHidden = false;
                }
                update();
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
                }
                else if (pickupHidden==true){
                    pickupDateAndTime.setVisibility(View.VISIBLE);
                    pickupDate.setVisibility(View.VISIBLE);
                    pickupTime.setVisibility(View.VISIBLE);
                    pickupHidden = false;
                }
                update();
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
                }
                else if (returnHidden==true){
                    returnDateAndTime.setVisibility(View.VISIBLE);
                    returnDate.setVisibility(View.VISIBLE);
                    returnTime.setVisibility(View.VISIBLE);
                    returnHidden = false;
                }
                update();
            }
        });

        hideArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (areaHidden==false){
                    areaText.setVisibility(View.INVISIBLE);
                    distance.setVisibility(View.INVISIBLE);
                    location.setVisibility(View.INVISIBLE);
                    areaHidden = true;
                }
                else if (areaHidden==true){
                    areaText.setVisibility(View.VISIBLE);
                    distance.setVisibility(View.VISIBLE);
                    location.setVisibility(View.VISIBLE);
                    areaHidden = false;
                }
                update();
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
        AdminSettings.TruckRental.nameHidden = nameHidden;
        AdminSettings.TruckRental.licenseHidden = licenseHidden;
        AdminSettings.TruckRental.pickupHidden = pickupHidden;
        AdminSettings.TruckRental.emailHidden = emailHidden;
        AdminSettings.TruckRental.areaHidden = areaHidden;
        AdminSettings.TruckRental.returnHidden = returnHidden;
        AdminSettings.update();

    }
}