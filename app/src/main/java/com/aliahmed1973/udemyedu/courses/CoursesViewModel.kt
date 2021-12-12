package com.aliahmed1973.udemyedu.courses

import androidx.lifecycle.*
import com.aliahmed1973.udemyedu.model.Course
import com.aliahmed1973.udemyedu.repository.CourseRepository
import kotlinx.coroutines.launch

class CoursesViewModel(private val courseRepository: CourseRepository) : ViewModel() {

private val _courses = MutableLiveData<List<Course>>()
    val courses :LiveData<List<Course>>
        get() =_courses

    init {
    viewModelScope.launch {
       _courses.value= courseRepository.getCoursesFromServer()
    }

}


    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: CourseRepository): ViewModelProvider.NewInstanceFactory()
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (CoursesViewModel(repository) as T)
        }
    }
}

