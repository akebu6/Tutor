package com.example.tutor.home.fragments.category

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.tutor.R

class CategoryAdapterList(private var list: List<CategoryViewModel>) : BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any {
        return Any()
    }

    override fun getItemId(position: Int): Long = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mView: View =
            convertView ?: View.inflate(parent?.context, R.layout.category_list_item, null)
        val categoryName: TextView = mView.findViewById(R.id.category_name)
        val categoryNumberOfQuizzes: TextView = mView.findViewById(R.id.number_of_quizzes)

        categoryName.text = list[position].getName()
        categoryNumberOfQuizzes.text = list[position].getNumberOfQuizzes().toString()

        return mView
    }
}
