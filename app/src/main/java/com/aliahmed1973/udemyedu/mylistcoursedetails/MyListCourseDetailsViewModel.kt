package com.aliahmed1973.udemyedu.mylistcoursedetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliahmed1973.udemyedu.model.Course
import com.aliahmed1973.udemyedu.model.CourseNote
import com.aliahmed1973.udemyedu.repository.CourseRepository

class MyListCourseDetailsViewModel(private val repository: CourseRepository) : ViewModel() {

    private val _courseDetails = MutableLiveData<Course>()
    val courseDetails: LiveData<Course>
        get() = _courseDetails

    private val _courseNotes =MutableLiveData<List<CourseNote?>?>()
    val courseNotes:LiveData<List<CourseNote?>?>
        get() = _courseNotes




    fun setCourseDetails(course: Course)
    {
        _courseDetails.value = course
        _courseNotes.value= course.courseNote
    }


    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: CourseRepository): ViewModelProvider.NewInstanceFactory()
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (MyListCourseDetailsViewModel(repository) as T)
        }
    }
}