package com.example.service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText Email,Password;
    Button login;
    TextView regi;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        login=findViewById(R.id.lgbtn);
        fAuth=FirebaseAuth.getInstance();
        regi=findViewById(R.id.regbtn);
         login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String email=Email.getText().toString().trim();
                 String password=Password.getText().toString().trim();
                 if(TextUtils.isEmpty(email))
                 {
                     Email.setError("Email is required");
                     return;
                 }
                 if(TextUtils.isEmpty(password))
                 {
                     Password.setError("Password required");
                     return;
                 }
                 if(password.length() < 6)
                 {
                     Password.setError("Password must be 6 characters");
                     return;
                 }

                 //authenticate user
                 fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {

                         if(task.isSuccessful())
                         {
                             Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(getApplicationContext() , MainActivity.class));
                         }else
                             {
                                 Toast.makeText(login.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                             }

                     }
                 });
                 //creating user
                 /*regi.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         startActivity(new Intent(getApplicationContext(),register.class));
                     }
                 });*/

             }
         });
        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),register.class));
            }
        });




    }
}