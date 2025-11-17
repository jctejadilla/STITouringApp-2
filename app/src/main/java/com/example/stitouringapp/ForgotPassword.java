package com.example.stitouringapp;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    private EditText email, password, confirmPassword;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Initialize components
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        submitButton = findViewById(R.id.submitButton);

        // Handle Submit button click
        submitButton.setOnClickListener(v -> {
            String emailInput = email.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();
            String confirmInput = confirmPassword.getText().toString().trim();

            // Simple validation
            if (emailInput.isEmpty() || passwordInput.isEmpty() || confirmInput.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            } else if (!passwordInput.equals(confirmInput)) {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            } else {
                // Simulate successful password reset
                Toast.makeText(this, "Password reset successful!", Toast.LENGTH_SHORT).show();

                // Go to OTP or MainActivity2 (you can choose which)
                Intent intent = new Intent(ForgotPassword.this, OTP.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
