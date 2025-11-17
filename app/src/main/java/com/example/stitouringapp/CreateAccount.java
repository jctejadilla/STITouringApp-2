package com.example.stitouringapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class CreateAccount extends AppCompatActivity {
    EditText etUsername, etStudentAccount, etPassword;
    TextView btnSubmit;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        etUsername = findViewById(R.id.etUsername);
        etStudentAccount = findViewById(R.id.etStudentAccount);
        etPassword = findViewById(R.id.etPassword);
        btnSubmit = findViewById(R.id.btnSubmit);

        mAuth = FirebaseAuth.getInstance();

        btnSubmit.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String studentAcc = etStudentAccount.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || studentAcc.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
            } else {

                registerUser(username, studentAcc, password);

            }
        });
    }

    private void registerUser(String username, String studentAcc, String password) {
        mAuth.createUserWithEmailAndPassword(studentAcc, password)
                .addOnCompleteListener(CreateAccount.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CreateAccount.this, "Registration successful", Toast.LENGTH_SHORT).show();

                            // Optional: send email verification
                            //mAuth.getCurrentUser().sendEmailVerification();

                            Intent intent = new Intent(CreateAccount.this, MainActivity2.class);
                            intent.putExtra("username", username);
                            startActivity(intent);
                            finish();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(CreateAccount.this, "Email already exists", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(CreateAccount.this, "Registration failed: " +
                                        task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
}
