package com.example.tutor.quiz;

import androidx.annotation.NonNull;

public class Category {
    public static final int MATH = 1;
    public static final int SCIENCE = 2;
    public static final int ENGLISH = 3;
    public static final int COMPUTER_STUDIES = 4;
    public static final int RELIGIOUS_EDUCATION = 5;
    public static final int BUSINESS_STUDIES = 6;
    public static final int SOCIAL_STUDIES = 7;

    private int id;
    private String name;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
