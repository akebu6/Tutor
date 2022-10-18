package com.example.tutor.ui.category;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tutor.R;
import com.example.tutor.database.helpers.QuizDbHelper;
import com.example.tutor.quiz.Category;
import com.example.tutor.quiz.QuestionsActivity;
import com.example.tutor.quiz.QuizQuestions;

import java.util.List;

@SuppressWarnings("ALL")
public class CategoryFragment extends Fragment {
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";
    public static final String EXTRA_DIFFICULTY = "extraDifficult";

    public static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private ImageView startButton;
    private Spinner spinnerDifficultyLevel, spinnerCategoryName;

    private TextView textViewHighScore;
    private int highscore;

    public CategoryFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        startButton = view.findViewById(R.id.start_image);
        spinnerDifficultyLevel = view.findViewById(R.id.spinner_difficulty);
        spinnerCategoryName = view.findViewById(R.id.spinner_category);
        textViewHighScore = view.findViewById(R.id.high_score);

        loadCategories();
        loadDifficultyLevel();
        loadHighScore();

        startButton.setOnClickListener(v -> startQuiz());

        return view;
    }

    private void startQuiz() {
        Category selectedCategory = (Category) spinnerCategoryName.getSelectedItem();

        int categoryID = selectedCategory.getId();
        String categoryName = selectedCategory.getName();
        String difficulty = spinnerDifficultyLevel.getSelectedItem().toString();

        Intent intent = new Intent(getActivity(), QuestionsActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryID);
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == getActivity().RESULT_OK) {
                int score = data.getIntExtra(QuestionsActivity.EXTRA_HIGH_SCORE, 0);
                if (score > highscore) {
                    updateHighScore(score);
                }
            }
        }
    }

    private void loadCategories() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getActivity());
        List<Category> categories = dbHelper.getAllCategories();

        ArrayAdapter<Category> adapterCategories = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, categories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoryName.setAdapter(adapterCategories);
    }

    private void loadDifficultyLevel() {
        String[] difficultyLevels = QuizQuestions.getAllDifficultyLevels();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, difficultyLevels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficultyLevel.setAdapter(adapter);
    }

    private void loadHighScore() {
        SharedPreferences prefs = getActivity()
                .getSharedPreferences(SHARED_PREFS, getActivity().MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighScore.setText("Highscore: " + highscore);
    }

    private void updateHighScore(int newHighScore) {
        highscore = newHighScore;
        textViewHighScore.setText("Highscore: " + highscore);

        SharedPreferences prefs = getActivity()
                .getSharedPreferences(SHARED_PREFS, getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
}
