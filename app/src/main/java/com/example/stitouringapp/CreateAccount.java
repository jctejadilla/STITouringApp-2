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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {
    EditText etUsername, etStudentAccount, etPassword;
    TextView btnSubmit;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        etUsername = findViewById(R.id.etUsername);
        etStudentAccount = findViewById(R.id.etStudentAccount);
        etPassword = findViewById(R.id.etPassword);
        btnSubmit = findViewById(R.id.btnSubmit);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

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
                            // User registration successful, now store user data in Firestore
                            String userId = mAuth.getCurrentUser().getUid();
                            Map<String, Object> user = new HashMap<>();
                            user.put("username", username);
                            user.put("email", studentAcc);

                            db.collection("users").document(userId)
                                    .set(user)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(CreateAccount.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(CreateAccount.this, MainActivity2.class);
                                        startActivity(intent);
                                        finish();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(CreateAccount.this, "Error saving user data: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    });
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
