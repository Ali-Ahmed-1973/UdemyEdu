package com.aliahmed1973.udemyedu.mylistcoursedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aliahmed1973.udemyedu.databinding.MylistDetailsItemBinding
import com.aliahmed1973.udemyedu.model.CourseNote

class MyListCourseDetailsAdapter:ListAdapter<CourseNote,MyListCourseDetailsAdapter.NoteViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<CourseNote>(){
        override fun areItemsTheSame(oldItem: CourseNote, newItem: CourseNote): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: CourseNote, newItem: CourseNote): Boolean {
            return oldItem == newItem
        }

    }
    inner class NoteViewHolder(private var binding:MylistDetailsItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(noteCourse: CourseNote?) {
            binding.tvNote.text=noteCourse?.noteText
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyListCourseDetailsAdapter.NoteViewHolder {
        return NoteViewHolder(MylistDetailsItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: MyListCourseDetailsAdapter.NoteViewHolder,
        position: Int
    ) {
        val noteCourse = getItem(position)
        holder.bind(noteCourse)
    }


}