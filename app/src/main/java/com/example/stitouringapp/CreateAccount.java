package com.example.stitouringapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccount extends AppCompatActivity {
    EditText etUsername, etStudentAccount, etPassword;
    TextView btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        etUsername = findViewById(R.id.etUsername);
        etStudentAccount = findViewById(R.id.etStudentAccount);
        etPassword = findViewById(R.id.etPassword);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String studentAcc = etStudentAccount.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || studentAcc.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(CreateAccount.this, OTP.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}
