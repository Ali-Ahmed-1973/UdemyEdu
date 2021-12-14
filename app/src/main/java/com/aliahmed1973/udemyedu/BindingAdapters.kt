package com.aliahmed1973.udemyedu

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aliahmed1973.udemyedu.courses.CoursesAdapter
import com.aliahmed1973.udemyedu.model.Course

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Course>?) {
    val adapter = recyclerView.adapter as CoursesAdapter
    adapter.submitList(data)
}

@BindingAdapter("imgUrl")
fun bindImage(imageView: ImageView,imgUrl:String)
{
    imageView.load(imgUrl)
}