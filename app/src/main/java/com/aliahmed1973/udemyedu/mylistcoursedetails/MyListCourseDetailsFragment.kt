package com.aliahmed1973.udemyedu.mylistcoursedetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.aliahmed1973.udemyedu.R
import com.aliahmed1973.udemyedu.database.getDatabase
import com.aliahmed1973.udemyedu.databinding.MyCoursesListFragmentBinding
import com.aliahmed1973.udemyedu.databinding.MyListCourseDetailsFragmentBinding
import com.aliahmed1973.udemyedu.repository.CourseRepository
import com.google.android.material.snackbar.Snackbar

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
        itemTouchHelper.attachToRecyclerView(binding.rvCourseNotes)
        noteDetailsBottomSheet= NoteDetailsBottomSheet(viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val course = MyListCourseDetailsFragmentArgs.fromBundle(requireArguments()).listCourse
        viewModel.setCourseDetails(course)

        viewModel.courseNotes.observe(viewLifecycleOwner){
                adapter.submitList(it)
            viewModel.courseNotesList= it!!
        }

        binding.fabAddNote.setOnClickListener {
            noteDetailsBottomSheet.show(this.childFragmentManager, NoteDetailsBottomSheet.TAG)
        }
        
        adapter.getCourseNote {
            viewModel.currentCourseNote=it
            noteDetailsBottomSheet.show(this.childFragmentManager, NoteDetailsBottomSheet.TAG)
        }

    }
    private val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            TODO("Not yet implemented")
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val deletedNotePos = viewHolder.adapterPosition
            viewModel.deleteNote(deletedNotePos)
            Snackbar.make(binding.rvCourseNotes, "Deleted", Snackbar.LENGTH_LONG).show()
        }

    }
    )


}