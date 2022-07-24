package com.example.byblos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private EditText editTextfirstname, editTextlastname, editTextemail, editTextpassword;
    private Spinner spinner;
    private String choice;
    private TextView registerUser;
    private ImageView banner;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        // *************************
        // Commands for spinner
        spinner = findViewById(R.id.rolespinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        // *************************

        mAuth = FirebaseAuth.getInstance();

        banner = (ImageView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.buttonreg);
        registerUser.setOnClickListener(this);

        editTextfirstname = (EditText) findViewById(R.id.firstname);
        editTextlastname = (EditText) findViewById(R.id.lastname);
        editTextemail = (EditText) findViewById(R.id.email);
        editTextpassword = (EditText) findViewById(R.id.password);


    }
    // *************************
    // Functions for the spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        choice = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    // *************************
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.buttonreg:
                registerUser();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    private void registerUser() {
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();
        String firstName = editTextfirstname.getText().toString().trim();
        String lastName = editTextlastname.getText().toString().trim();
        String role = choice;

        if(firstName.isEmpty()){
            editTextfirstname.setError("First name is required!");
            editTextfirstname.requestFocus();
            return;
        }
        if(lastName.isEmpty()){
            editTextlastname.setError("Last name is required!");
            editTextlastname.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextemail.setError("Email is required!");
            editTextemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Please provide valid Email!");
            editTextemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextpassword.setError("Password is required!");
            editTextpassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTextpassword.setError("Minimum password length should be 6 characters!");
            editTextpassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(firstName, lastName, email, role);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterUser.this, "User has been registered successfully", Toast.LENGTH_LONG).show();

                                        // redirect to welcome screen
                                    }
                                    else{
                                        Toast.makeText(RegisterUser.this, "Failed to register, Try again!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(RegisterUser.this, "Failed to register, Try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

        }


}