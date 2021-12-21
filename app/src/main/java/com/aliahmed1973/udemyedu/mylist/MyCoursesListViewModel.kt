package com.aliahmed1973.udemyedu.mylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliahmed1973.udemyedu.model.Course
import com.aliahmed1973.udemyedu.repository.CourseRepository

class MyCoursesListViewModel(private val repository: CourseRepository) : ViewModel() {

    var myListCourses:LiveData<List<Course>> = repository.getMyCourseslist()


    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: CourseRepository): ViewModelProvider.NewInstanceFactory()
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (MyCoursesListViewModel(repository) as T)
        }
    }
}