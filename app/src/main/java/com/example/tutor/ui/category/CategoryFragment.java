package com.example.tutor.ui.category;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tutor.database.OnCompleteListener;
import com.example.tutor.R;
import com.example.tutor.database.DatabaseQuery;

public class CategoryFragment extends Fragment {
    private GridView mGridView;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_category, container, false);
       mGridView = view.findViewById(R.id.grid_view);

       DatabaseQuery.loadCategories(new OnCompleteListener() {
              @Override
              public void onSuccess() {
//                CategoryAdapter categoryAdapter = new CategoryAdapter();
//                mGridView.setAdapter(categoryAdapter);
              }

              @Override
              public void onFailure() {

              }
         });

       return view;
    }
}