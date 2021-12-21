package com.aliahmed1973.udemyedu.mylist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aliahmed1973.udemyedu.R

class MyCoursesListFragment : Fragment() {

    private lateinit var viewModel: MyCoursesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_courses_list_fragment, container, false)
    }

}