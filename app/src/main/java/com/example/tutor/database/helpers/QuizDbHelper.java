package com.example.tutor.database.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tutor.database.contracts.QuizContract;
import com.example.tutor.quiz.QuizQuestions;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Quiz.db";
    private static final int DATABASE_VERSION = 1;

    // commands
    final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " + QuizContract.QuestionsTable.TABLE_NAME +
            " (" + QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT NOT NULL, " +
            QuizContract.QuestionsTable.COLUMN_OPTIONA + " TEXT NOT NULL, " +
            QuizContract.QuestionsTable.COLUMN_OPTIONB + " TEXT NOT NULL, " +
            QuizContract.QuestionsTable.COLUMN_OPTIONC + " TEXT NOT NULL, " +
            QuizContract.QuestionsTable.COLUMN_OPTIOND + " TEXT NOT NULL, " +
            QuizContract.QuestionsTable.COLUMN_ANSWER_NUMBER + " INTEGER NOT NULL " +
            ")";

    final String SQL_DROP_QUESTIONS_TABLE = "DROP TABLE IF EXISTS " +
            QuizContract.QuestionsTable.TABLE_NAME;

    final String SQL_SELECT_FROM_QUESTIONS_TABLE = "SELECT * FROM " +
            QuizContract.QuestionsTable.TABLE_NAME;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_QUESTIONS_TABLE);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        QuizQuestions quizQuestionOne = new QuizQuestions("A is correct",
                "A", "B", "C", "D", 1);
        addQuestions(quizQuestionOne);
        QuizQuestions quizQuestionTwo = new QuizQuestions("B is correct",
                "A", "B", "C", "D", 2);
        addQuestions(quizQuestionTwo);
        QuizQuestions quizQuestionThree = new QuizQuestions("C is correct",
                "A", "B", "C", "D", 3);
        addQuestions(quizQuestionThree);
        QuizQuestions quizQuestionFour = new QuizQuestions("D is correct",
                "A", "B", "C", "D", 4);
        addQuestions(quizQuestionFour);
    }

    private void addQuestions(QuizQuestions questions) {
        ContentValues values = new ContentValues();
        values.put(QuizContract.QuestionsTable.COLUMN_QUESTION, questions.getQuestion());
        values.put(QuizContract.QuestionsTable.COLUMN_OPTIONA, questions.getOptionA());
        values.put(QuizContract.QuestionsTable.COLUMN_OPTIONB, questions.getOptionB());
        values.put(QuizContract.QuestionsTable.COLUMN_OPTIONC, questions.getOptionC());
        values.put(QuizContract.QuestionsTable.COLUMN_OPTIOND, questions.getOptionD());
        values.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NUMBER, questions.getAnswer());

        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, values);
    }

    public List<QuizQuestions> getAllQuestions() {
        List<QuizQuestions> quizQuestionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL_SELECT_FROM_QUESTIONS_TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                QuizQuestions quizQuestions = new QuizQuestions();
                quizQuestions.setQuestion(cursor.getString(cursor.getColumnIndexOrThrow(
                        QuizContract.QuestionsTable.COLUMN_QUESTION))
                );
                quizQuestions.setOptionA(cursor.getString(cursor.getColumnIndexOrThrow(
                        QuizContract.QuestionsTable.COLUMN_OPTIONA))
                );
                quizQuestions.setOptionB(cursor.getString(cursor.getColumnIndexOrThrow(
                        QuizContract.QuestionsTable.COLUMN_OPTIONB))
                );
                quizQuestions.setOptionC(cursor.getString(cursor.getColumnIndexOrThrow(
                        QuizContract.QuestionsTable.COLUMN_OPTIONC))
                );
                quizQuestions.setOptionD(cursor.getString(cursor.getColumnIndexOrThrow(
                        QuizContract.QuestionsTable.COLUMN_OPTIOND))
                );
                quizQuestions.setAnswer(cursor.getInt(cursor.getColumnIndexOrThrow(
                        QuizContract.QuestionsTable.COLUMN_ANSWER_NUMBER))
                );

                quizQuestionsList.add(quizQuestions);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return quizQuestionsList;
    }
}
