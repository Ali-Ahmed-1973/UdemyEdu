package com.aliahmed1973.udemyedu.coursedetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.aliahmed1973.udemyedu.R
import com.aliahmed1973.udemyedu.database.getDatabase
import com.aliahmed1973.udemyedu.databinding.CourseDetailsFragmentBinding
import com.aliahmed1973.udemyedu.repository.CourseRepository

class CourseDetailsFragment : Fragment() {
    private lateinit var binding:CourseDetailsFragmentBinding
    private val database by lazy{ getDatabase(this.requireContext()) }
    private val repository by lazy{CourseRepository(database)}
    private val viewModel: CourseDetailsViewModel by viewModels{
        CourseDetailsViewModel.Factory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= CourseDetailsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner=this
        binding.viewModel=viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val course = CourseDetailsFragmentArgs.fromBundle(requireArguments()).courseDetails
        viewModel.checkCourseInDatabase(course)
        viewModel.databaseCourse.observe(viewLifecycleOwner){
            viewModel.setCourseDetails(it)
        }
        binding.rvCourseReviews.adapter= ReviewAdapter()
        binding.imageViewMarkIcon.setOnClickListener {
            viewModel.addOrRemoveCourseFromList()
        }
    }
}