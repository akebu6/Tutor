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
        // science questions
        QuizQuestions scienceQuizQ1Easy = new QuizQuestions("Which of the following " +
                "diseases is transmitted mainly through sexual intercourse?",
                "Malaria", "Syphilis", "Typhoid", "Scabies", 1,
                QuizQuestions.DIFFICULTY_LEVEL_EASY, Category.SCIENCE);
        addQuestions(scienceQuizQ1Easy);
        QuizQuestions scienceQuizQ2Easy = new QuizQuestions("Which one of the following " +
                "changes is only associated with puberty in females?",
                "Breasts grow", "Voice becomes deeper",
                "Hair grows in the armpits", "Start having wet dreams", 1,
                QuizQuestions.DIFFICULTY_LEVEL_EASY, Category.SCIENCE);
        addQuestions(scienceQuizQ2Easy);
        QuizQuestions scienceQuizQ3Easy = new QuizQuestions( "The law that states that mass " +
                "is a substance before a chemical reaction is equal to the total mass of the " +
                "substances that are produced is called the law of .... ",
                "conservation of energy", "conservation of matter",
                "conversion of matter", "conversion of energy", 1,
                QuizQuestions.DIFFICULTY_LEVEL_EASY, Category.SCIENCE);
        addQuestions(scienceQuizQ3Easy);

        // science hard questions
        QuizQuestions scienceQuizQ4Hard = new QuizQuestions("The term \"Geotropism\" refers" +
                " to the movement of a part of a plant in response to what?",
                "chemicals", "gravity", "light", "water", 1,
                QuizQuestions.DIFFICULTY_LEVEL_HARD, Category.SCIENCE);
        addQuestions(scienceQuizQ4Hard);

        // math questions
        QuizQuestions mathQuizQ1Easy = new QuizQuestions("What is 357 861 correct to 3 significant figure?",
                "360 000", "350 000", "358 000", "357 000", 4,
                QuizQuestions.DIFFICULTY_LEVEL_EASY, Category.MATH);
        addQuestions(mathQuizQ1Easy);
        QuizQuestions mathQuizQ2Easy = new QuizQuestions("What is the longest side of a right angled triangle?",
                "Hypotenuse", "Adjacent", "Base", "Radius", 1,
                QuizQuestions.DIFFICULTY_LEVEL_EASY, Category.MATH);
        addQuestions(mathQuizQ2Easy);

        // math medium questions
        QuizQuestions mathQuizQ3Medium = new QuizQuestions("Find the missing terms in multiple of 3: 3, 6, 9, __, 15",
                "2", "6", "9", "12", 4,
                QuizQuestions.DIFFICULTY_LEVEL_MEDIUM, Category.MATH);
        addQuestions(mathQuizQ3Medium);
        QuizQuestions mathQuizQ4Medium = new QuizQuestions("What is the square root of 81?",
                "10", "9", "8", "1", 2,
                QuizQuestions.DIFFICULTY_LEVEL_MEDIUM, Category.MATH);
        addQuestions(mathQuizQ4Medium);

        // math hard questions
        QuizQuestions mathQuizQ5Hard = new QuizQuestions("50 times of 8 is equal to:",
                "800", "400", "40", "4", 2,
                QuizQuestions.DIFFICULTY_LEVEL_HARD, Category.MATH);
        addQuestions(mathQuizQ5Hard);

        // english medium questions
        QuizQuestions englishQuizQ1Medium = new QuizQuestions("Your cousin tells you that " +
                "she has failed the Grade 12 examinations. What would you say?",
                "I am sorry to hear that.", "Is that so? Condolences.",
                "Maybe you did not study hard enough.", "That is very regrettable.", 1,
                QuizQuestions.DIFFICULTY_LEVEL_MEDIUM, Category.ENGLISH);
        addQuestions(englishQuizQ1Medium);
        QuizQuestions englishQuizQ2Medium = new QuizQuestions( "You accidentally knock a " +
                "cup of coffee from your friend's hand. What do you say?",
                "Are you all right?", "I am sorry.", "Take care.", "Watch out!", 1,
                QuizQuestions.DIFFICULTY_LEVEL_MEDIUM, Category.ENGLISH);
        addQuestions(englishQuizQ2Medium);
        QuizQuestions englishQuizQ3Medium = new QuizQuestions( "Your friend asks for a pen " +
                "from you and it is not the first time. She is in the habit of losing pens. What would you say? Why do you ...",
                "always lose pen", "always lose pens", "lose pens", "lose a pen", 2,
                QuizQuestions.DIFFICULTY_LEVEL_MEDIUM, Category.ENGLISH);
        addQuestions(englishQuizQ3Medium);
        QuizQuestions englishQuizQ4Medium = new QuizQuestions("You cannot run the 2 800 " +
                "metres race. What would you say? Sir, I .... run the 2 800 metres race.",
                "am able not to", "am cannot", "am not able to", "cannot be able to", 3,
                QuizQuestions.DIFFICULTY_LEVEL_MEDIUM, Category.ENGLISH);
        addQuestions(englishQuizQ4Medium);
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
