package com.aliahmed1973.udemyedu.mylist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aliahmed1973.udemyedu.R
import com.aliahmed1973.udemyedu.database.getDatabase
import com.aliahmed1973.udemyedu.databinding.MyCoursesListFragmentBinding
import com.aliahmed1973.udemyedu.repository.CourseRepository

class MyCoursesListFragment : Fragment() {
    private lateinit var binding:MyCoursesListFragmentBinding
    private val database by lazy{ getDatabase(this.requireContext()) }
    private val repository by lazy{CourseRepository(database)}
    private val viewModel: MyCoursesListViewModel by viewModels {
    MyCoursesListViewModel.Factory(repository)
    }
    private lateinit var adapter :MyCourseListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= MyCoursesListFragmentBinding.inflate(inflater)
        binding.lifecycleOwner=this
        binding.viewModel=viewModel
        adapter= MyCourseListAdapter(viewModel)
        binding.rvMylist.adapter=adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.myListCourses.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        adapter.getCourse {
            this.findNavController().navigate(MyCoursesListFragmentDirections.actionMyCoursesListFragmentToMyListCourseDetailsFragment(it))
        }
    }
}