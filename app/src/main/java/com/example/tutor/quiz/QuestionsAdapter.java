package com.example.tutor.quiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor.R;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {
    private List<QuestionsViewModel> questionsList;

    public QuestionsAdapter(List<QuestionsViewModel> questionsList) {
        this.questionsList = questionsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.questions_item, parent, false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView question;
        Button optionA, optionB, optionC, optionD;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.question);
            optionA = itemView.findViewById(R.id.option_A);
            optionB = itemView.findViewById(R.id.option_B);
            optionC = itemView.findViewById(R.id.option_C);
            optionD = itemView.findViewById(R.id.option_D);
        }

        private void setData(final int position) {
            question.setText(questionsList.get(position).getQuestion());
            optionA.setText(questionsList.get(position).getOptionA());
            optionB.setText(questionsList.get(position).getOptionB());
            optionC.setText(questionsList.get(position).getOptionC());
            optionD.setText(questionsList.get(position).getOptionD());
        }
    }
}
