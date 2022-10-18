package com.example.tutor.quiz;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tutor.MainActivity;
import com.example.tutor.R;
import com.example.tutor.database.helpers.QuizDbHelper;
import com.example.tutor.ui.category.CategoryFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class QuestionsActivity extends AppCompatActivity {
    private static final long TIMER_COUNTDOWN_MILLIS = 50000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_SECONDS_LEFT = "keySecondsLeft";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    public static final String EXTRA_HIGH_SCORE = "extraHighScore";

    private Button submitButton;
    private RadioButton optionA, optionB, optionC, optionD;
    private RadioGroup radioGroup;
    private TextView questions, totalQuestions, timer, quizScore, levelDifficulty, mCategoryName;

    private ColorStateList textColorDefaultButton;
    private ColorStateList textColorDefaultTimer;

    private CountDownTimer countDownTimer;
    private long timeLeft;

    private ArrayList<QuizQuestions> quizQuestionList;
    private int questionTotalCounter;
    private QuizQuestions currentQuestion;
    private int questionCounter;

    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        initializeVariables();

        textColorDefaultButton = optionA.getTextColors();
        textColorDefaultTimer = timer.getTextColors();

        Intent intent = getIntent();
        int categoryID = intent.getIntExtra(CategoryFragment.EXTRA_CATEGORY_ID, 0);
        String categoryName = intent.getStringExtra(CategoryFragment.EXTRA_CATEGORY_NAME);
        String difficulty = intent.getStringExtra(CategoryFragment.EXTRA_DIFFICULTY);

        mCategoryName.setText("Category: " + categoryName);
        levelDifficulty.setText("Level: " + difficulty);

        if (savedInstanceState == null) {
            QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
            quizQuestionList = dbHelper.getQuestions(categoryID, difficulty);

            questionTotalCounter = quizQuestionList.size();
            Collections.shuffle(quizQuestionList);

            showNextQuestion();
        } else {
            quizQuestionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);

            if (quizQuestionList == null) {
                finish();
            }

            questionTotalCounter = quizQuestionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = quizQuestionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeft = savedInstanceState.getLong(KEY_SECONDS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if (!answered) {
                startCountDownTimer();
            } else {
                updateCountDownText();
                showCorrectAnswer();
            }
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (optionA.isChecked() || optionB.isChecked() ||
                            optionC.isChecked() || optionD.isChecked()
                    ) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuestionsActivity.this, "Please select an answer",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                   showNextQuestion();
                }
            }
        });
    }

    public void initializeVariables() {
        submitButton = findViewById(R.id.submit);
        ImageView prevButton = findViewById(R.id.previous_button);
        optionA = findViewById(R.id.option_A);
        optionB = findViewById(R.id.option_B);
        optionC = findViewById(R.id.option_C);
        optionD = findViewById(R.id.option_D);
        questions = findViewById(R.id.question);
        totalQuestions = findViewById(R.id.total_questions);
        timer = findViewById(R.id.timer);
        radioGroup = findViewById(R.id.radioGroup);
        quizScore = findViewById(R.id.quiz_score);
        levelDifficulty = findViewById(R.id.difficulty_level);
        mCategoryName = findViewById(R.id.category_name);

        prevButton.setOnClickListener(v -> {
            Intent intent = new Intent(QuestionsActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void showNextQuestion() {
        optionA.setTextColor(textColorDefaultButton);
        optionB.setTextColor(textColorDefaultButton);
        optionC.setTextColor(textColorDefaultButton);
        optionD.setTextColor(textColorDefaultButton);
        radioGroup.clearCheck();

        if (questionCounter < questionTotalCounter) {
            currentQuestion = quizQuestionList.get(questionCounter);
            questions.setText(currentQuestion.getQuestion());
            optionA.setText(currentQuestion.getOptionA());
            optionB.setText(currentQuestion.getOptionB());
            optionC.setText(currentQuestion.getOptionC());
            optionD.setText(currentQuestion.getOptionD());

            questionCounter++;
            totalQuestions.setText("Question: " + questionCounter + "/" + questionTotalCounter);
            answered = false;
            submitButton.setText("Confirm");

            timeLeft = TIMER_COUNTDOWN_MILLIS;
            startCountDownTimer();

        } else {
            finishQuiz();
        }
    }

    private void checkAnswer() {
        answered = true;

        countDownTimer.cancel();

        RadioButton radioButtonSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answer = radioGroup.indexOfChild(radioButtonSelected) + 1;

        if (answer == currentQuestion.getAnswer()) {
            score++;
            quizScore.setText("Score: " + score);
        }

        showCorrectAnswer();

    }

    private void showCorrectAnswer() {
        optionA.setTextColor(Color.RED);
        optionB.setTextColor(Color.RED);
        optionC.setTextColor(Color.RED);
        optionD.setTextColor(Color.RED);

        switch (currentQuestion.getAnswer()) {
            case 1:
                optionA.setTextColor(Color.GREEN);
                questions.setText("A is correct");
                break;
            case 2:
                optionB.setTextColor(Color.GREEN);
                questions.setText("B is correct");
                break;
            case 3:
                optionC.setTextColor(Color.GREEN);
                questions.setText("C is correct");
                break;
            case 4:
                optionD.setTextColor(Color.GREEN);
                questions.setText("D is correct");
                break;
        }

        if (questionCounter < questionTotalCounter) {
            submitButton.setText("Next");
        } else {
            submitButton.setText("Submit");
            submitButton.setOnClickListener(v -> {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            });
        }
    }

    private void startCountDownTimer() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeft = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeft / 1000) / 60;
        int seconds = (int) (timeLeft / 1000) % 60;

        String timeFormatted = String.format(
                Locale.getDefault(),
                "%02d:%02d",
                minutes,
                seconds
        );

        timer.setText(timeFormatted);

        if (timeLeft < 10000) {
            timer.setTextColor(Color.RED);
        } else {
            timer.setTextColor(textColorDefaultTimer);
        }
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_HIGH_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_SECONDS_LEFT, timeLeft);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_COUNT, quizQuestionList);
    }
}
