package com.example.tutor.quiz;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutor.MainActivity;
import com.example.tutor.R;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener {
    private Button submitButton, optionA, optionB, optionC, optionD;
    private ImageView prevButton;
    private TextView questions, totalQuestions;

    private int score = 0;
    private int currentQuestion = Questions.questions.length;
    private int questionIndex = 0;
    private String selectedOption = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        initializeVariables();
        loadNewQuestions();

    }

    public void loadNewQuestions() {
        if (questionIndex == currentQuestion) {
            finishQuiz();
            return;
        }

        questions.setText(Questions.questions[questionIndex]);
        optionA.setText(Questions.choices[questionIndex][0]);
        optionB.setText(Questions.choices[questionIndex][1]);
        optionC.setText(Questions.choices[questionIndex][2]);
        optionD.setText(Questions.choices[questionIndex][3]);
    }

    public void finishQuiz() {
        String quizStatus;
        if (score >= currentQuestion * 0.60) {
            quizStatus = "Excellent";
        } else {
            quizStatus = "Try Again!";
        }

        new AlertDialog.Builder(this)
                .setTitle(quizStatus)
                .setMessage("Your Score: " + score + " out of " + currentQuestion)
                .setPositiveButton("Restart", (dialogInterface, i) -> restart())
                .setCancelable(false)
                .show();
    }

    public void restart() {
        score = 0;
        questionIndex = 0;
        loadNewQuestions();
    }

    public void initializeVariables() {
        submitButton = findViewById(R.id.submit);
        prevButton = findViewById(R.id.previous_button);
        optionA = findViewById(R.id.option_A);
        optionB = findViewById(R.id.option_B);
        optionC = findViewById(R.id.option_C);
        optionD = findViewById(R.id.option_D);
        questions = findViewById(R.id.question);
        totalQuestions = findViewById(R.id.total_questions);

        prevButton.setOnClickListener(v -> {
            Intent intent = new Intent(QuestionsActivity.this, MainActivity.class);
            startActivity(intent);
        });

        submitButton.setOnClickListener(this);
        optionA.setOnClickListener(this);
        optionB.setOnClickListener(this);
        optionC.setOnClickListener(this);
        optionD.setOnClickListener(this);

        totalQuestions.setText("Total Questions: " + currentQuestion);

    }

    public void onClick(View view) {
        optionA.setBackgroundColor(Color.BLUE);
        optionB.setBackgroundColor(Color.BLUE);
        optionC.setBackgroundColor(Color.BLUE);
        optionD.setBackgroundColor(Color.BLUE);

        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.submit) {
            if (selectedOption.equals(Questions.correctAnswers[questionIndex])) {
                score++;
            }
            questionIndex++;
            loadNewQuestions();

        } else {
            selectedOption = clickedButton.getText().toString();
            clickedButton.setBackgroundResource(R.color.green);
        }
    }
}