package com.example.tutor.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutor.MainActivity;
import com.example.tutor.R;

public class StartQuizActivity extends AppCompatActivity {
    Button startQuizButton;
    TextView quizTime, bestScore, numberOfQuestions;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        initialiseVariables();
    }

    public void setData() {

    }

    private void initialiseVariables() {
        startQuizButton = findViewById(R.id.startQuizButton);
        backButton = findViewById(R.id.back_button);
        quizTime = findViewById(R.id.quiz_time);
        bestScore = findViewById(R.id.best_score);
        numberOfQuestions = findViewById(R.id.number_of_questions);

        startQuizButton.setOnClickListener(v -> {
            Intent intent = new Intent(StartQuizActivity.this, QuestionsActivity.class);
            startActivity(intent);
            finish();
        });

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(StartQuizActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}