package com.example.tutor.database;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;

import com.example.tutor.ui.category.CategoryViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseQuery {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<CategoryViewModel> mCategoryViewModelList = new ArrayList<>();

//    private List<QuestionModel> quizModelList;

//    public loadQuestions(OnCompleteListener onCompleteListener) {
//        db.collection("QUESTIONS").whereEqualTo("")
//    }

    public static void loadCategories(OnCompleteListener onCompleteListener) {
        mCategoryViewModelList.clear();
        db.collection("QUIZ").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Map<String, QueryDocumentSnapshot> docList = new ArrayMap<>();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    docList.put(documentSnapshot.getId(), documentSnapshot);
                }
                QueryDocumentSnapshot catListDoc = docList.get("Categories");
                long count = (long) catListDoc.get("COUNT");
                for (int i = 1; i <= count; i++) {
                    String categoryId = catListDoc.getString("CAT" + i + "_ID");
                    QueryDocumentSnapshot catDoc = docList.get(categoryId);
                    String categoryName = catDoc.getString("NAME");
                    mCategoryViewModelList.add(new CategoryViewModel(categoryId, categoryName));
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

}
