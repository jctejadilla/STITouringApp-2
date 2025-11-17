package com.example.stitouringapp;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info);

        String text = "Our goal to create a <font color='#FFA500'>Virtual 360° Digital Tour</font> of <font color='#00BCD4'>STI Dasmariñas</font> that allows students and visitors to easily explore the campus, including classrooms, hallways, bathrooms, the canteen, clinic, library, laboratories, offices, and other key facilities. This project aims to help new students, parents, and guests familiarize themselves with the school’s layout, making it easier to locate rooms and essential services while providing an immersive and accessible digital experience.";
        Spanned styledText = Html.fromHtml(text);

        TextView textView = findViewById(R.id.textView2);
        textView.setText(styledText);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button goBackButton = findViewById(R.id.button);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}