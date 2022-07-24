package com.example.byblos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DeleteEmployee extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextemail, editTextpassword;
    private Button deleteUser;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_employee);

        deleteUser = (Button) findViewById(R.id.deletebutton);
        deleteUser.setOnClickListener(this);

        editTextemail = (EditText) findViewById(R.id.emaildelete);
        editTextpassword = (EditText) findViewById(R.id.passworddelete);

        mAuth = FirebaseAuth.getInstance();

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.deletebutton:
                userDelete();
                break;
        }


    }

    private void userDelete() {
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if(email.equals("admin") && password.equals("admin")){

            Toast.makeText(DeleteEmployee.this,"Cannot delete admin user!", Toast.LENGTH_LONG).show();
        }else{
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
                        //redirect to user profile
                        firebaseUser = mAuth.getCurrentUser();
                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(DeleteEmployee.this,"User has been deleted", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }else{
                        Toast.makeText(DeleteEmployee.this,"Could not find specified user ", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }





    }
}