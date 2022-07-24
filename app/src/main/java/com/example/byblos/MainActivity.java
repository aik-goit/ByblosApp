package com.example.byblos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView register;
    public static EditText editTextemail;
    private EditText editTextpassword;


    private Button signIn;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdminSettings.update();

        register = (TextView) findViewById(R.id.registerbutton);
        register.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.signinbutton);
        signIn.setOnClickListener(this);

        editTextemail = (EditText) findViewById(R.id.emailsign);
        editTextpassword = (EditText) findViewById(R.id.passwordsign);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registerbutton:
                startActivity(new Intent( this, RegisterUser.class ));
                break;
            case R.id.signinbutton:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if(email.equals("admin") && password.equals("admin")){
            startActivity(new Intent(MainActivity.this, adminActivity.class));
        }
        else if (email.equals("employee") && password.equals("employee")){
            startActivity(new Intent(MainActivity.this, EmployeeWelcomeActivity.class));
        }
        else if (email.equals("customer") && password.equals("customer")){
            startActivity(new Intent(MainActivity.this, userActivity.class));
        }


        else{
            if(email.isEmpty()){
                editTextemail.setError("Email is required!");
                editTextemail.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                editTextemail.setError("Please enter a valid email!");
                editTextemail.requestFocus();
                return;
            }
            if(password.isEmpty()){
                editTextpassword.setError("Password is required!");
                editTextpassword.requestFocus();
                return;
            }
            if(password.length() < 6){
                editTextpassword.setError("Minimum password length is 6 characters");
                editTextpassword.requestFocus();
                return;
            }
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        user = FirebaseAuth.getInstance().getCurrentUser();
                        reference = FirebaseDatabase.getInstance().getReference("Users");
                        userID = user.getUid();
                        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                User userProfile = snapshot.getValue(User.class);
                                String role = userProfile.role;
                                if (role.equals("Customer")){
                                    startActivity(new Intent(MainActivity.this, userActivity.class));
                                }
                                else if (role.equals("Employee")){
                                    startActivity(new Intent(MainActivity.this, EmployeeWelcomeActivity.class));
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(MainActivity.this,"Failed to login please check your credentials!", Toast.LENGTH_LONG).show();
                            }
                        });
                        //redirect to user profile
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Failed to login please check your credentials!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }





    }
}