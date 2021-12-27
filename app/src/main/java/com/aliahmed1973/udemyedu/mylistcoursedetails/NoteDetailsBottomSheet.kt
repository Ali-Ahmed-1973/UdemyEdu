package com.aliahmed1973.udemyedu.mylistcoursedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aliahmed1973.udemyedu.databinding.NoteDetailsBottomSheetBinding
import com.aliahmed1973.udemyedu.model.CourseNote
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NoteDetailsBottomSheet(private val viewModel: MyListCourseDetailsViewModel,
                             private val adapter:MyListCourseDetailsAdapter):BottomSheetDialogFragment() {

    private lateinit var binding:NoteDetailsBottomSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NoteDetailsBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            val newNote = CourseNote(noteText = binding.etNoteText.text.toString())
            viewModel.addNewNote(newNote)
            adapter.notifyDataSetChanged()
            this.dismiss()
        }
    }

    companion object {
        const val TAG = "NoteDetailsBottomSheet"
    }
}