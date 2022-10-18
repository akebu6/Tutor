package com.example.tutor.database.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tutor.database.contracts.QuizContract;
import com.example.tutor.quiz.Category;
import com.example.tutor.quiz.Questions;
import com.example.tutor.quiz.QuizQuestions;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Quiz.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    // commands
    final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
            QuizContract.CategoriesTable.TABLE_NAME + " (" +
            QuizContract.CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuizContract.CategoriesTable.COLUMN_NAME + " TEXT NOT NULL" +
            ")";

    final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " + QuizContract.QuestionsTable.TABLE_NAME +
            " (" + QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT NOT NULL, " +
            QuizContract.QuestionsTable.COLUMN_OPTIONA + " TEXT NOT NULL, " +
            QuizContract.QuestionsTable.COLUMN_OPTIONB + " TEXT NOT NULL, " +
            QuizContract.QuestionsTable.COLUMN_OPTIONC + " TEXT NOT NULL, " +
            QuizContract.QuestionsTable.COLUMN_OPTIOND + " TEXT NOT NULL, " +
            QuizContract.QuestionsTable.COLUMN_ANSWER_NUMBER + " INTEGER NOT NULL, " +
            QuizContract.QuestionsTable.COLUMN_DIFFICULTY_LEVEL + " TEXT NOT NULL, " +
            QuizContract.QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + QuizContract.QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
            QuizContract.CategoriesTable.TABLE_NAME + "(" + QuizContract.CategoriesTable._ID + ")" +
            "ON DELETE CASCADE" +
            ")";

    final String SQL_DROP_CATEGORIES_TABLE = "DROP TABLE IF EXISTS " +
            QuizContract.CategoriesTable.TABLE_NAME;

    final String SQL_DROP_QUESTIONS_TABLE = "DROP TABLE IF EXISTS " +
            QuizContract.QuestionsTable.TABLE_NAME;

    final String SQL_SELECT_FROM_QUESTIONS_TABLE = "SELECT * FROM " +
            QuizContract.QuestionsTable.TABLE_NAME;

    final String SQL_SELECT_FROM_CATEGORIES_TABLE = "SELECT * FROM " +
            QuizContract.CategoriesTable.TABLE_NAME;

    final String SQL_SELECT_FROM_QUESTIONS_TABLE_WITH_DIFFICULTY_LEVEL_AND_CATEGORY =
            QuizContract.QuestionsTable.COLUMN_CATEGORY_ID + " = ? " + " AND " +
                    QuizContract.QuestionsTable.COLUMN_DIFFICULTY_LEVEL + " = ? ";

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_CATEGORIES_TABLE);
        db.execSQL(SQL_DROP_QUESTIONS_TABLE);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category categoryOne = new Category("MATH");
        addCategory(categoryOne);
        Category categoryTwo = new Category("SCIENCE");
        addCategory(categoryTwo);
        Category categoryThree = new Category("ENGLISH");
        addCategory(categoryThree);
        Category categoryFour = new Category("COMPUTER_STUDIES");
        addCategory(categoryFour);
        Category categoryFive = new Category("RELIGIOUS_EDUCATION");
        addCategory(categoryFive);
        Category categorySix = new Category("BUSINESS_STUDIES");
        addCategory(categorySix);
        Category categorySeven = new Category("SOCIAL_STUDIES");
        addCategory(categorySeven);
    }

    private void addCategory(Category category) {
        ContentValues values = new ContentValues();
        values.put(QuizContract.CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(QuizContract.CategoriesTable.TABLE_NAME, null, values);
    }

    private void fillQuestionsTable() {
        QuizQuestions quizQuestionOne = new QuizQuestions("Math, Easy: A is correct",
                "A", "B", "C", "D", 1,
                QuizQuestions.DIFFICULTY_LEVEL_EASY, Category.MATH);
        addQuestions(quizQuestionOne);
        QuizQuestions quizQuestionTwo = new QuizQuestions("Science, Medium: B is correct",
                "A", "B", "C", "D", 2,
                QuizQuestions.DIFFICULTY_LEVEL_MEDIUM, Category.SCIENCE);
        addQuestions(quizQuestionTwo);
        QuizQuestions quizQuestionThree = new QuizQuestions("English, Hard: C is correct",
                "A", "B", "C", "D", 3,
                QuizQuestions.DIFFICULTY_LEVEL_HARD, Category.ENGLISH);
        addQuestions(quizQuestionThree);
        QuizQuestions quizQuestionFour = new QuizQuestions("Computer Studies, Hard: D is correct",
                "A", "B", "C", "D", 4,
                QuizQuestions.DIFFICULTY_LEVEL_HARD, Category.COMPUTER_STUDIES);
        addQuestions(quizQuestionFour);
        QuizQuestions quizQuestionFive = new QuizQuestions("Religious Ed, Easy: D is correct",
                "A", "B", "C", "D", 4,
                QuizQuestions.DIFFICULTY_LEVEL_EASY, Category.RELIGIOUS_EDUCATION);
        addQuestions(quizQuestionFive);
        QuizQuestions quizQuestionSix = new QuizQuestions("Business Studies, Medium: D is correct",
                "A", "B", "C", "D", 4,
                QuizQuestions.DIFFICULTY_LEVEL_MEDIUM, Category.BUSINESS_STUDIES);
        addQuestions(quizQuestionSix);
        QuizQuestions quizQuestionSeven = new QuizQuestions("Social Studies, Hard: D is correct",
                "A", "B", "C", "D", 4,
                QuizQuestions.DIFFICULTY_LEVEL_HARD, Category.SOCIAL_STUDIES);
        addQuestions(quizQuestionSeven);
    }

    private void addQuestions(QuizQuestions questions) {
        ContentValues values = new ContentValues();
        values.put(QuizContract.QuestionsTable.COLUMN_QUESTION, questions.getQuestion());
        values.put(QuizContract.QuestionsTable.COLUMN_OPTIONA, questions.getOptionA());
        values.put(QuizContract.QuestionsTable.COLUMN_OPTIONB, questions.getOptionB());
        values.put(QuizContract.QuestionsTable.COLUMN_OPTIONC, questions.getOptionC());
        values.put(QuizContract.QuestionsTable.COLUMN_OPTIOND, questions.getOptionD());
        values.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NUMBER, questions.getAnswer());
        values.put(QuizContract.QuestionsTable.COLUMN_DIFFICULTY_LEVEL, questions.getDifficultyLevel());
        values.put(QuizContract.QuestionsTable.COLUMN_CATEGORY_ID, questions.getCategoryID());

        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, values);
    }

    public ArrayList<QuizQuestions> getAllQuestions() {
        ArrayList<QuizQuestions> quizQuestionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL_SELECT_FROM_QUESTIONS_TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                QuizQuestions quizQuestions = new QuizQuestions();
                quizQuestions.setId(cursor.getInt(cursor.getColumnIndexOrThrow(
                        QuizContract.QuestionsTable._ID))
                );
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
                quizQuestions.setDifficultyLevel(cursor.getString(cursor.getColumnIndexOrThrow(
                        QuizContract.QuestionsTable.COLUMN_DIFFICULTY_LEVEL))
                );
                quizQuestions.setCategoryID(cursor.getInt(cursor.getColumnIndexOrThrow(
                        QuizContract.QuestionsTable.COLUMN_CATEGORY_ID))
                );

                quizQuestionsList.add(quizQuestions);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return quizQuestionsList;
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL_SELECT_FROM_CATEGORIES_TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(cursor.getInt(cursor.getColumnIndexOrThrow(
                        QuizContract.CategoriesTable._ID))
                );
                category.setName(cursor.getString(cursor.getColumnIndexOrThrow(
                        QuizContract.CategoriesTable.COLUMN_NAME))
                );
                categoryList.add(category);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return categoryList;
    }

    public ArrayList<QuizQuestions> getQuestions(int categoryID, String difficultyLevel) {
        ArrayList<QuizQuestions> quizQuestionsList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[] {String.valueOf(categoryID), difficultyLevel};

        Cursor cursor = db.query(
                QuizContract.QuestionsTable.TABLE_NAME,
                null,
                SQL_SELECT_FROM_QUESTIONS_TABLE_WITH_DIFFICULTY_LEVEL_AND_CATEGORY,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                QuizQuestions quizQuestions = new QuizQuestions();
                quizQuestions.setId(cursor.getInt(cursor.getColumnIndexOrThrow(
                        QuizContract.QuestionsTable._ID))
                );
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
                quizQuestions.setDifficultyLevel(cursor.getString(cursor.getColumnIndexOrThrow(
                        QuizContract.QuestionsTable.COLUMN_DIFFICULTY_LEVEL))
                );
                quizQuestions.setCategoryID(cursor.getInt(cursor.getColumnIndexOrThrow(
                        QuizContract.QuestionsTable.COLUMN_CATEGORY_ID))
                );

                quizQuestionsList.add(quizQuestions);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return quizQuestionsList;
    }
}
