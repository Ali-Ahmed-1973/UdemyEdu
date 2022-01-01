package com.aliahmed1973.udemyedu.mylistcoursedetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.aliahmed1973.udemyedu.R
import com.aliahmed1973.udemyedu.database.getDatabase
import com.aliahmed1973.udemyedu.databinding.MyCoursesListFragmentBinding
import com.aliahmed1973.udemyedu.databinding.MyListCourseDetailsFragmentBinding
import com.aliahmed1973.udemyedu.repository.CourseRepository

private const val TAG = "MyListCourseDetailsFrag"
class MyListCourseDetailsFragment : Fragment() {
    private lateinit var binding: MyListCourseDetailsFragmentBinding
    private val database by lazy{ getDatabase(this.requireContext()) }
    private val repository by lazy{ CourseRepository(database) }
    private val viewModel: MyListCourseDetailsViewModel by viewModels{
        MyListCourseDetailsViewModel.Factory(repository)
    }
    private lateinit var adapter: MyListCourseDetailsAdapter
    private lateinit var noteDetailsBottomSheet: NoteDetailsBottomSheet
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     binding = MyListCourseDetailsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner=this
        binding.viewModel=viewModel
        adapter=MyListCourseDetailsAdapter()
        binding.rvCourseNotes.adapter=adapter
        noteDetailsBottomSheet= NoteDetailsBottomSheet(viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val course = MyListCourseDetailsFragmentArgs.fromBundle(requireArguments()).listCourse
        viewModel.setCourseDetails(course)

        viewModel.courseNotes.observe(viewLifecycleOwner){
            Log.d(TAG, "onViewCreated: $it")
            it?.let {
                adapter.submitList(it)
            }
        }

        binding.fabAddNote.setOnClickListener {
            noteDetailsBottomSheet.show(this.childFragmentManager, NoteDetailsBottomSheet.TAG)
        }
        
        adapter.getCourseNote {
            viewModel.currentCourseNote=it
            noteDetailsBottomSheet.show(this.childFragmentManager, NoteDetailsBottomSheet.TAG)
        }
    }


}