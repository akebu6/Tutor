package com.example.tutor.ui.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tutor.R;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private List<CategoryViewModel> mCategoryViewModelList;

    public CategoryAdapter(List<CategoryViewModel> categoryViewModelList) {
        mCategoryViewModelList = categoryViewModelList;
    }

    @Override
    public int getCount() {
        return mCategoryViewModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewObject;
        if (convertView == null ) {
            viewObject = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.category_list, parent, false
                    );
        } else {
            viewObject = convertView;
        }

        TextView textView = viewObject.findViewById(R.id.category_name);
        textView.setText(mCategoryViewModelList.get(position).getName());

        return viewObject;
    }
}
