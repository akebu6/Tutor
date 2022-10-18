package com.example.tutor.database.contracts;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract() {}

    public static class CategoriesTable implements BaseColumns {
        public static final String TABLE_NAME = "categories";
        public static final String COLUMN_NAME = "name";
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTIONA = "optionA";
        public static final String COLUMN_OPTIONB = "optionB";
        public static final String COLUMN_OPTIONC = "optionC";
        public static final String COLUMN_OPTIOND = "optionD";
        public static final String COLUMN_ANSWER_NUMBER = "answer_number";
        public static final String COLUMN_DIFFICULTY_LEVEL = "difficulty_level";
        public static final String COLUMN_CATEGORY_ID = "category_id";
    }
}
