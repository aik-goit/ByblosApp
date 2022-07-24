package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class movingform extends AppCompatActivity {
    boolean nameAndBirthHidden = false;
    boolean emailHidden = false;
    boolean startLocationHidden = false;
    boolean endLocationHidden = false;
    boolean moversNeededHidden = false;
    boolean numberOfBoxesHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moving_assitance_form);

        Button hideName = (Button) findViewById(R.id.hideNameAndBirth);
        Button hideEmail = (Button) findViewById(R.id.hideEmail);
        Button hideStartLocation = (Button) findViewById(R.id.hideStartLocation);
        Button hideEndLocation = (Button) findViewById(R.id.hideEndLocation);
        Button hideMoversNeeded = (Button) findViewById(R.id.hideMoversNeeded);
        Button hideNumberOfBoxes = (Button) findViewById(R.id.hideNumberOfBoxes);
        Button submit = (Button) findViewById(R.id.submit);

        hideName.setVisibility(View.INVISIBLE);
        hideEmail.setVisibility(View.INVISIBLE);
        hideStartLocation.setVisibility(View.INVISIBLE);
        hideEndLocation.setVisibility(View.INVISIBLE);
        hideMoversNeeded.setVisibility(View.INVISIBLE);
        hideNumberOfBoxes.setVisibility(View.INVISIBLE);

        if (adminActivity.isAdmin==true){
            hideName.setVisibility(View.VISIBLE);
            hideEmail.setVisibility(View.VISIBLE);
            hideStartLocation.setVisibility(View.VISIBLE);
            hideEndLocation.setVisibility(View.VISIBLE);
            hideMoversNeeded.setVisibility(View.VISIBLE);
            hideNumberOfBoxes.setVisibility(View.VISIBLE);
            submit.setVisibility(View.INVISIBLE);
        }

        TextView firstNameTitle = (TextView) findViewById(R.id.textView4);
        EditText firstNameInfo = (EditText) findViewById(R.id.editTextTextPersonName9);
        EditText dateOfBirth = (EditText) findViewById(R.id.editTextTextPersonName10);

        TextView emailTitle = (TextView) findViewById(R.id.textView9);
        EditText emailInput = (EditText) findViewById(R.id.editTextTextPersonName11);

        TextView movingStartTitle = (TextView) findViewById(R.id.movingstarttitle);
        EditText movingStart = (EditText) findViewById(R.id.movingstart);

        TextView movingEndTitle = (TextView) findViewById(R.id.movingendtitile);
        EditText movingEnd = (EditText) findViewById(R.id.movingend);

        EditText movers = (EditText) findViewById(R.id.nofmovers);

        EditText boxesNeeded = (EditText) findViewById(R.id.nofboxes);

        DAOform dao = new DAOform();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = null;
                String birthdate = null;
                String email = null;
                String startlocation = null;
                String endlocation = null;
                String moversneeded = null;
                String numberofboxes = null;

                if (!AdminSettings.MovingAssistance.nameAndBirthHidden) {
                    fullname = firstNameInfo.getText().toString();
                    birthdate = dateOfBirth.getText().toString();
                }
                if (!AdminSettings.MovingAssistance.emailHidden) {
                    email = emailInput.getText().toString();
                }
                if (!AdminSettings.MovingAssistance.numberOfBoxesHidden) {
                    numberofboxes = boxesNeeded.getText().toString();
                }
                if (!AdminSettings.MovingAssistance.moversNeededHidden) {
                    moversneeded = movers.getText().toString();
                }
                if (!AdminSettings.MovingAssistance.startLocationHidden) {
                    startlocation = movingStart.getText().toString();
                }
                if (!AdminSettings.MovingAssistance.endLocationHidden) {
                    endlocation = movingEnd.getText().toString();
                }
                Request request = new Request("moving", fullname, birthdate, email, startlocation, endlocation, moversneeded, numberofboxes, true);
                Requests.requests.add(request);
                Form form = new Form("moving assistance", fullname, email, startlocation, endlocation, moversneeded, numberofboxes);
                dao.add(form);
                Toast.makeText(movingform.this, "Request submitted", Toast.LENGTH_SHORT).show();
            }
        });

        if (AdminSettings.MovingAssistance.nameAndBirthHidden == false) {
            firstNameTitle.setVisibility(View.VISIBLE);
            firstNameInfo.setVisibility(View.VISIBLE);
            dateOfBirth.setVisibility(View.VISIBLE);
            nameAndBirthHidden = false;
        } else {
            firstNameTitle.setVisibility(View.INVISIBLE);
            firstNameInfo.setVisibility(View.INVISIBLE);
            dateOfBirth.setVisibility(View.INVISIBLE);
            nameAndBirthHidden = true;
        }

        if (AdminSettings.MovingAssistance.emailHidden == false) {
            emailTitle.setVisibility(View.VISIBLE);
            emailInput.setVisibility(View.VISIBLE);
            emailHidden = false;
        } else {
            emailTitle.setVisibility(View.INVISIBLE);
            emailInput.setVisibility(View.INVISIBLE);
            emailHidden = true;
        }
        if (AdminSettings.MovingAssistance.startLocationHidden == false) {
            movingStartTitle.setVisibility(View.VISIBLE);
            movingStart.setVisibility(View.VISIBLE);
            startLocationHidden = false;
        } else {
            movingStartTitle.setVisibility(View.INVISIBLE);
            movingStart.setVisibility(View.INVISIBLE);
            startLocationHidden = true;
        }

        if (AdminSettings.MovingAssistance.endLocationHidden == false) {
            movingEnd.setVisibility(View.VISIBLE);
            movingEndTitle.setVisibility(View.VISIBLE);
            endLocationHidden = false;

        } else {
            movingEnd.setVisibility(View.INVISIBLE);
            movingEndTitle.setVisibility(View.INVISIBLE);
            endLocationHidden = true;
        }

        if (AdminSettings.MovingAssistance.moversNeededHidden == false) {
            movers.setVisibility(View.VISIBLE);
            moversNeededHidden = false;
        } else {
            movers.setVisibility(View.INVISIBLE);
            moversNeededHidden = true;
        }

        if (AdminSettings.MovingAssistance.numberOfBoxesHidden == false) {
            boxesNeeded.setVisibility(View.VISIBLE);
            numberOfBoxesHidden = false;
        } else {
            boxesNeeded.setVisibility(View.INVISIBLE);
            numberOfBoxesHidden = true;
        }

        hideName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameAndBirthHidden==false){
                    firstNameTitle.setVisibility(View.INVISIBLE);
                    firstNameInfo.setVisibility(View.INVISIBLE);
                    dateOfBirth.setVisibility(View.INVISIBLE);
                    nameAndBirthHidden = true;
                }
                else if (nameAndBirthHidden==true){
                    firstNameTitle.setVisibility(View.VISIBLE);
                    firstNameInfo.setVisibility(View.VISIBLE);
                    dateOfBirth.setVisibility(View.VISIBLE);
                    nameAndBirthHidden = false;
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

        hideStartLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startLocationHidden==false){
                    movingStartTitle.setVisibility(View.INVISIBLE);
                    movingStart.setVisibility(View.INVISIBLE);
                    startLocationHidden = true;
                }
                else if (startLocationHidden==true){
                    movingStartTitle.setVisibility(View.VISIBLE);
                    movingStart.setVisibility(View.VISIBLE);
                    startLocationHidden = false;
                }
                update();
            }
        });

        hideEndLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (endLocationHidden==false){
                    movingEnd.setVisibility(View.INVISIBLE);
                    movingEndTitle.setVisibility(View.INVISIBLE);
                    endLocationHidden = true;
                }
                else if (endLocationHidden==true){
                    movingEnd.setVisibility(View.VISIBLE);
                    movingEndTitle.setVisibility(View.VISIBLE);
                    endLocationHidden = false;
                }
                update();
            }
        });

        hideMoversNeeded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moversNeededHidden==false){
                    movers.setVisibility(View.INVISIBLE);
                    moversNeededHidden = true;
                }
                else if (moversNeededHidden==true){
                    movers.setVisibility(View.VISIBLE);
                    moversNeededHidden = false;
                }
                update();
            }
        });

        hideNumberOfBoxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOfBoxesHidden==false){
                    boxesNeeded.setVisibility(View.INVISIBLE);
                    numberOfBoxesHidden = true;
                }
                else if (numberOfBoxesHidden==true){
                    boxesNeeded.setVisibility(View.VISIBLE);
                    numberOfBoxesHidden = false;
                }
                update();
            }
        });

    }

    public void update() {
        AdminSettings.MovingAssistance.nameAndBirthHidden = nameAndBirthHidden;
        AdminSettings.MovingAssistance.emailHidden = emailHidden;
        AdminSettings.MovingAssistance.startLocationHidden = startLocationHidden;
        AdminSettings.MovingAssistance.endLocationHidden = endLocationHidden;
        AdminSettings.MovingAssistance.moversNeededHidden = moversNeededHidden;
        AdminSettings.MovingAssistance.numberOfBoxesHidden = numberOfBoxesHidden;
        AdminSettings.update();
    }
}