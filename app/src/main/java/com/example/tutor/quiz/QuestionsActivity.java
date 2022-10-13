package com.example.tutor.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tutor.R;
import com.example.tutor.database.DatabaseQuery;

public class QuestionsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView numOfQuestions, timeLeft;
    private Button reviewButton, submitButton;
    private ImageButton nextButton, prevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        initialseVariables();

        QuestionsAdapter adapter = new QuestionsAdapter(DatabaseQuery.questionsList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void initialseVariables() {
        recyclerView = findViewById(R.id.question_recycler_view);
        numOfQuestions = findViewById(R.id.num_of_questions);
        timeLeft = findViewById(R.id.time_left);
        reviewButton = findViewById(R.id.review);
        submitButton = findViewById(R.id.submit);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.previous_button);
    }
}