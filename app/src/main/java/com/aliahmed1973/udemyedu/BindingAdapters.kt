package com.aliahmed1973.udemyedu

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aliahmed1973.udemyedu.courses.CoursesAdapter
import com.aliahmed1973.udemyedu.model.Course

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,data:List<Course>?)
{
val adapter = recyclerView.adapter as CoursesAdapter
    adapter.submitList(data)
}