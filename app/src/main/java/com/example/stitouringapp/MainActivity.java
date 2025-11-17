package com.example.stitouringapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText studentAccountInput, passwordInput;
    TextView loginButton, createAccount, forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Adjust system window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Views
        studentAccountInput = findViewById(R.id.StudentAccountInput);
        passwordInput = findViewById(R.id.PasswordInput);
        loginButton = findViewById(R.id.LoginButton);
        createAccount = findViewById(R.id.CreateAccount);
        forgetPassword = findViewById(R.id.ForgetPassword);

        // LOGIN BUTTON ACTION
        loginButton.setOnClickListener(v -> {
            String student = studentAccountInput.getText().toString().trim();
            String pass = passwordInput.getText().toString().trim();

            if (student.isEmpty() || pass.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        // CREATE ACCOUNT ACTION
        createAccount.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateAccount.class);
            startActivity(intent);
        });

        // FORGOT PASSWORD ACTION
        forgetPassword.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
            startActivity(intent);
        });
    }
}
