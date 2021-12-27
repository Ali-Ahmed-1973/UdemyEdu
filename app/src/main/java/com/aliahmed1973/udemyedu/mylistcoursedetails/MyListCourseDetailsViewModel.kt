package com.aliahmed1973.udemyedu.mylistcoursedetails

import android.util.Log
import androidx.lifecycle.*
import com.aliahmed1973.udemyedu.model.Course
import com.aliahmed1973.udemyedu.model.CourseNote
import com.aliahmed1973.udemyedu.repository.CourseRepository
import kotlinx.coroutines.launch

private const val TAG = "MyListCourseDetailsView"
class MyListCourseDetailsViewModel(private val repository: CourseRepository) : ViewModel() {

    private val _courseDetails = MutableLiveData<Course>()
    val courseDetails: LiveData<Course>
        get() = _courseDetails

    private val _courseNotes =MutableLiveData<List<CourseNote?>?>()
    val courseNotes:LiveData<List<CourseNote?>?>
        get() = _courseNotes

    private var _mutableCourseNotes = mutableListOf<CourseNote?>()


    fun setCourseDetails(course: Course)
    {
        _courseDetails.value = course
        _courseNotes.value= course.courseNote
        course.courseNote?.let { _mutableCourseNotes.addAll(it) }
    }

    fun addNewNote(courseNote: CourseNote)
    {
        viewModelScope.launch {
              repository.insertNoteToMylistCourse(courseNote,_courseDetails.value!!.id)
        }
        _mutableCourseNotes.add(courseNote)
        _courseNotes.value = _mutableCourseNotes
        Log.d(TAG, "addNewNote: _mutableCourseNotes: $_mutableCourseNotes")
        Log.d(TAG, "addNewNote: _courseNotes: ${_courseNotes.value}")
    }
    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: CourseRepository): ViewModelProvider.NewInstanceFactory()
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (MyListCourseDetailsViewModel(repository) as T)
        }
    }
}