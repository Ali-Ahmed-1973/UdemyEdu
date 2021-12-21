package com.aliahmed1973.udemyedu

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aliahmed1973.udemyedu.coursedetails.ReviewAdapter
import com.aliahmed1973.udemyedu.courses.CoursesAdapter
import com.aliahmed1973.udemyedu.model.Course
import com.aliahmed1973.udemyedu.model.Review

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Course>?) {
    val adapter = recyclerView.adapter as CoursesAdapter
    adapter.submitList(data)
}

@BindingAdapter("listReviewData")
fun bindReviewRecyclerView(recyclerView: RecyclerView, data: List<Review>?) {
    val adapter = recyclerView.adapter as ReviewAdapter
    adapter.submitList(data)
}

@BindingAdapter("imgUrl")
fun bindImage(imageView: ImageView,imgUrl:String)
{
    imageView.load(imgUrl)
}