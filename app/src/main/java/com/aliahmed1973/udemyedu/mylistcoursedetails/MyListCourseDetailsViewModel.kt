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


    lateinit var courseNotes:LiveData<List<CourseNote?>?>


    var currentCourseNote:CourseNote? = null

    fun setCourseDetails(course: Course)
    {
        _courseDetails.value = course
        viewModelScope.launch {
            courseNotes=  repository.getNotesById(course.id)
        }


    }

    fun addNewNote(courseNote: CourseNote)
    {
            viewModelScope.launch {
                repository.insertNoteToMylistCourse(courseNote,_courseDetails.value!!.id)
            }
    }

    fun updateNote(courseNote: CourseNote)
    {
        viewModelScope.launch {
            repository.UpdateOldNote(courseNote,_courseDetails.value!!.id)
        }
    }
    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: CourseRepository): ViewModelProvider.NewInstanceFactory()
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (MyListCourseDetailsViewModel(repository) as T)
        }
    }
}