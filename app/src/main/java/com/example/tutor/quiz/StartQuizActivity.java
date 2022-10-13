package com.example.tutor.quiz;

import static com.example.tutor.database.DatabaseQuery.loadQuestions;
import static com.example.tutor.database.DatabaseQuery.mCategoryViewModelList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutor.R;
import com.example.tutor.database.DatabaseQuery;
import com.example.tutor.database.OnCompleteListener;

public class StartQuizActivity extends AppCompatActivity {
    Button startQuizButton;
    TextView quizTime, bestScore, numberOfQuestions, categoryName;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        initialiseVariables();

        loadQuestions(new OnCompleteListener() {
            @Override
            public void onSuccess() {
                setData();
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public void setData() {
        categoryName.setText(mCategoryViewModelList.get(DatabaseQuery.selectedItemIndex).getName());
        numberOfQuestions.setText(String.valueOf(DatabaseQuery.questionsList.size()));
//        bestScore.setText(String.valueOf(DatabaseQuery.quizList.get(DatabaseQuery.selectedItemIndex).getTopScore));
//        quizTime.setText(String.valueOf(DatabaseQuery.quizList.get(DatabaseQuery.selectedItemIndex).getTime()));
    }

    private void initialiseVariables() {
        categoryName = findViewById(R.id.subjectName);
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

        backButton.setOnClickListener(v -> StartQuizActivity.this.finish());
    }
}