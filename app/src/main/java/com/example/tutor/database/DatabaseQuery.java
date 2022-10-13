package com.example.tutor.database;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;

import com.example.tutor.quiz.QuestionsViewModel;
import com.example.tutor.ui.category.CategoryViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseQuery {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static List<CategoryViewModel> mCategoryViewModelList = new ArrayList<>();
    public static int selectedItemIndex = 0;
    public static List<QuestionsViewModel> questionsList = new ArrayList<>();

    public static void loadQuestions(OnCompleteListener onCompleteListener) {
        mCategoryViewModelList.clear();
        db.collection("QUESTIONS")
                .whereEqualTo("CATEGORIES", mCategoryViewModelList.get(selectedItemIndex)
                        .getDocumentID())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot document : queryDocumentSnapshots) {
                            questionsList.add(new QuestionsViewModel(
                                document.getString("QUESTIONS"),
                                document.getString(("A")),
                                document.getString("B"),
                                document.getString("C"),
                                document.getString("D"),
                                document.getLong("ANSWER").intValue()
                            ));
                        }
                        onCompleteListener.onSuccess();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        onCompleteListener.onFailure();
                    }
                });
    }

    public static void loadCategories(OnCompleteListener onCompleteListener) {
        mCategoryViewModelList.clear();
        db.collection("QUIZ").get().addOnSuccessListener(queryDocumentSnapshots -> {
            Map<String, QueryDocumentSnapshot> docList = new ArrayMap<>();
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                docList.put(documentSnapshot.getId(), documentSnapshot);
            }
            QueryDocumentSnapshot catListDoc = docList.get("Categories");
            long count = 0;
            if (catListDoc != null) {
                count = (long) catListDoc.get("COUNT");
            }
            for (int i = 1; i <= count; i++) {
                String categoryId = catListDoc.getString("CAT" + i + "_ID");
                QueryDocumentSnapshot catDoc = docList.get(categoryId);
                String categoryName = null;
                if (catDoc != null) {
                    categoryName = catDoc.getString("NAME");
                }
                mCategoryViewModelList.add(new CategoryViewModel(categoryId, categoryName));
            }

            onCompleteListener.onSuccess();
        }).addOnFailureListener(e -> onCompleteListener.onFailure());
    }
}
