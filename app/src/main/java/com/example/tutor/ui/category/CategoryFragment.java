package com.example.tutor.ui.category;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.example.tutor.R;
import com.example.tutor.database.DatabaseQuery;
import com.example.tutor.database.OnCompleteListener;
import com.example.tutor.quiz.QuestionsActivity;

public class CategoryFragment extends Fragment {
    private Button startButton;

    public CategoryFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        startButton = view.findViewById(R.id.startQuiz);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuestionsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}