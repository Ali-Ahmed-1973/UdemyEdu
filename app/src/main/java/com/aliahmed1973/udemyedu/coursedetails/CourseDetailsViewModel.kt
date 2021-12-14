package com.aliahmed1973.udemyedu.coursedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliahmed1973.udemyedu.model.Course
import com.aliahmed1973.udemyedu.repository.CourseRepository

class CourseDetailsViewModel(repository: CourseRepository) : ViewModel() {

    private val _courseDetails = MutableLiveData<Course>()
    val courseDetails:LiveData<Course>
    get() = _courseDetails

    fun setCourseDetails(course: Course)
    {
        _courseDetails.value=course
    }
    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: CourseRepository): ViewModelProvider.NewInstanceFactory()
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (CourseDetailsViewModel(repository) as T)
        }
    }
}