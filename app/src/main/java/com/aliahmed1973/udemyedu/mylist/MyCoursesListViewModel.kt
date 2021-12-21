package com.aliahmed1973.udemyedu.mylist

import androidx.lifecycle.*
import com.aliahmed1973.udemyedu.model.Course
import com.aliahmed1973.udemyedu.repository.CourseRepository
import kotlinx.coroutines.launch

class MyCoursesListViewModel(private val repository: CourseRepository) : ViewModel() {

    var myListCourses:LiveData<List<Course>> = repository.getMyCourseslist()


    fun removeCourseFromList(course: Course)
    {
        viewModelScope.launch {
            repository.deleteCourseFromList(course)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: CourseRepository): ViewModelProvider.NewInstanceFactory()
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (MyCoursesListViewModel(repository) as T)
        }
    }
}