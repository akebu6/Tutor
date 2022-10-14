package com.example.tutor.database;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;

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

    public static void loadCategories(OnCompleteListener onCompleteListener) {
        mCategoryViewModelList.clear();
        db.collection("QUIZ").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
            Map<String, QueryDocumentSnapshot> docList = new ArrayMap<>();
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                docList.put(documentSnapshot.getId(), documentSnapshot);
            }

            QueryDocumentSnapshot catListDoc = docList.get("CATEGORIES");

            long count = catListDoc.getLong("COUNT");

            for (int i = 1; i <= count; i++) {
                String categoryId = catListDoc.getString("CAT" + String.valueOf(i) + "_ID");
                QueryDocumentSnapshot catDoc = docList.get(categoryId);
                String categoryName = " ";
                if (catDoc != null) {
                    categoryName = catDoc.getString("CATEGORY_NAME");
                }
                mCategoryViewModelList.add(new CategoryViewModel(categoryId, categoryName));
            }
            onCompleteListener.onSuccess();

        }).addOnFailureListener(e -> e.printStackTrace());
    }
}
