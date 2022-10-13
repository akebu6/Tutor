package com.example.tutor.quiz;

public class QuestionsViewModel {
    private String question;
    private String optionA;
    private String optionB;
    private String OptionC;
    private String optionD;
    private int correctAnswer;

    public QuestionsViewModel(
            String question,
            String optionA,
            String optionB,
            String optionC,
            String optionD,
            int correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        OptionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
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
        return OptionC;
    }

    public void setOptionC(String optionC) {
        OptionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
