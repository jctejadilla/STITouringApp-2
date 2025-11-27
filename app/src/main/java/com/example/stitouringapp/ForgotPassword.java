package com.example.stitouringapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText email;
    private Button submitButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Initialize components
        email = findViewById(R.id.email);
        submitButton = findViewById(R.id.submitButton);
        mAuth = FirebaseAuth.getInstance();

        // Handle Submit button click
        submitButton.setOnClickListener(v -> {
            String emailInput = email.getText().toString().trim();

            if (emailInput.isEmpty()) {
                Toast.makeText(this, "Please enter your registered email address.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Send a password reset email to the user
            mAuth.sendPasswordResetEmail(emailInput)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgotPassword.this, "Password reset email sent. Please check your inbox.", Toast.LENGTH_LONG).show();
                        // Go back to the main login activity
                        Intent intent = new Intent(ForgotPassword.this, MainActivity2.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // If the email does not exist in Firebase, an error will be thrown.
                        Toast.makeText(ForgotPassword.this, "Failed to send reset email. Please ensure the email is correct and registered.", Toast.LENGTH_LONG).show();
                    }
                });
        });
    }
}
