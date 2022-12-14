package com.example.tutor.ui.category;

public class CategoryViewModel {
    private String documentID;
    private String name;

    public CategoryViewModel(String documentID, String name) {
        this.documentID = documentID;
        this.name = name;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
