package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class adminActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private ImageView carforms, truckforms, movingforms;
    private Button admindelete;
    public static boolean isAdmin = false;

    public Switch carSwitch;
    public Switch truckSwitch;
    public Switch movingSwitch;

    public EditText carEditText;
    public EditText truckEditText;
    public EditText movingEditText;

    public Button updateSettings;
    public Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_services);

        AdminSettings.update();

        logout = (Button) findViewById(R.id.logout);

        carforms = (ImageView) findViewById(R.id.carmodify);
        carforms.setOnClickListener(this);
        truckforms = (ImageView) findViewById(R.id.truckmodify);
        truckforms.setOnClickListener(this);
        movingforms = (ImageView) findViewById(R.id.movingmodify);
        movingforms.setOnClickListener(this);

        admindelete =  (Button) findViewById(R.id.admindelete);
        admindelete.setOnClickListener(this);

        updateSettings = (Button) findViewById(R.id.updateSettings);

        carSwitch = (Switch) findViewById(R.id.carRentalSwitch);
        truckSwitch = (Switch) findViewById(R.id.truckRentalSwitch);
        movingSwitch = (Switch) findViewById(R.id.movingSwitch);
        carEditText = (EditText) findViewById(R.id.carRentalPrice);
        truckEditText = (EditText) findViewById(R.id.truckRentalPrice);
        movingEditText = (EditText) findViewById(R.id.movingHelpPrice);

        carSwitch.setChecked(AdminSettings.CarRental.toggle);
        truckSwitch.setChecked(AdminSettings.TruckRental.toggle);
        movingSwitch.setChecked(AdminSettings.MovingAssistance.toggle);
        carEditText.setText(AdminSettings.CarRental.price);
        truckEditText.setText(AdminSettings.TruckRental.price);
        movingEditText.setText(AdminSettings.MovingAssistance.price);

        updateSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        carSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AdminSettings.CarRental.toggle = isChecked;
            }
        });

        truckSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AdminSettings.TruckRental.toggle = isChecked;
            }
        });

        movingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AdminSettings.MovingAssistance.toggle = isChecked;
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.carmodify:
                isAdmin = true;
                startActivity(new Intent(this, carform.class));
                break;
            case R.id.truckmodify:
                isAdmin = true;
                startActivity(new Intent(this, truckform.class));
                break;
            case R.id.movingmodify:
                isAdmin = true;
                startActivity(new Intent(this, movingform.class));
                break;
            case R.id.admindelete:
                isAdmin = true;
                startActivity(new Intent(this, DeleteEmployee.class));
                break;
        }

    }

    public void update(){
        //AdminSettings.CarRental.toggle = carSwitch.isActivated();
        //AdminSettings.TruckRental.toggle = truckSwitch.isActivated();
        //AdminSettings.MovingAssistance.toggle = movingSwitch.isActivated();

        AdminSettings.CarRental.price = carEditText.getText().toString();
        AdminSettings.TruckRental.price = truckEditText.getText().toString();
        AdminSettings.MovingAssistance.price = movingEditText.getText().toString();
        AdminSettings.update();

        Toast.makeText(getApplicationContext(),"price settings updated", Toast.LENGTH_SHORT).show();
    }
    public void logout() {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }
}