package com.example.tutor.quiz;

import android.os.Parcel;
import android.os.Parcelable;

public class QuizQuestions implements Parcelable {
    public static final String DIFFICULTY_LEVEL_EASY = "Easy";
    public static final String DIFFICULTY_LEVEL_MEDIUM = "Medium";
    public static final String DIFFICULTY_LEVEL_HARD = "Hard";

    private int id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private int answer;
    private String difficultyLevel;
    private int categoryID;

    public QuizQuestions() {}

    public QuizQuestions(
            String question,
            String optionA,
            String optionB,
            String optionC,
            String optionD,
            int answer,
            String difficultyLevel,
            int categoryID
    ) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
        this.difficultyLevel = difficultyLevel;
        this.categoryID = categoryID;
    }

    protected QuizQuestions(Parcel in) {
        id = in.readInt();
        question = in.readString();
        optionA = in.readString();
        optionB = in.readString();
        optionC = in.readString();
        optionD = in.readString();
        answer = in.readInt();
        difficultyLevel = in.readString();
        categoryID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question);
        dest.writeString(optionA);
        dest.writeString(optionB);
        dest.writeString(optionC);
        dest.writeString(optionD);
        dest.writeInt(answer);
        dest.writeString(difficultyLevel);
        dest.writeInt(categoryID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuizQuestions> CREATOR = new Creator<QuizQuestions>() {
        @Override
        public QuizQuestions createFromParcel(Parcel in) {
            return new QuizQuestions(in);
        }

        @Override
        public QuizQuestions[] newArray(int size) {
            return new QuizQuestions[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public static final String[] getAllDifficultyLevels() {
        return new String[] {
                DIFFICULTY_LEVEL_EASY,
                DIFFICULTY_LEVEL_MEDIUM,
                DIFFICULTY_LEVEL_HARD
        };
    }
}
