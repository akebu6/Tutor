package com.example.tutor.home.fragments.category

class CategoryViewModel() {
    private lateinit var documentId: String
    private lateinit var name: String
    private var numberOfQuizzes: Int? = null

    constructor(documentId: String, name: String, numberOfQuizzes: Int) : this() {
        this.documentId = documentId
        this.name = name
        this.numberOfQuizzes = numberOfQuizzes
    }

    fun getName(): String {
        return name
    }

    fun getNumberOfQuizzes(): Int? {
        return numberOfQuizzes
    }
}
