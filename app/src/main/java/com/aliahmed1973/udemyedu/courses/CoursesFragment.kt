package com.aliahmed1973.udemyedu.courses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.aliahmed1973.udemyedu.databinding.CoursesFragmentBinding
import com.aliahmed1973.udemyedu.repository.CourseRepository

class CoursesFragment : Fragment() {
    private lateinit var binding: CoursesFragmentBinding
    private val repository = CourseRepository()
    private val coursesViewModel: CoursesViewModel by viewModels {
        CoursesViewModel.Factory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CoursesFragmentBinding.inflate(inflater)
        binding.viewModel = this.coursesViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCourses.adapter = CoursesAdapter()

    }
}