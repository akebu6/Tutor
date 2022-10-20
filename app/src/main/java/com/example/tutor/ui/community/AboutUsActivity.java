package com.example.tutor.ui.community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.tutor.MainActivity;
import com.example.tutor.R;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ImageView previousButton = findViewById(R.id.previous_button);

        previousButton.setOnClickListener(v -> {
            Intent intent = new Intent(AboutUsActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }
}