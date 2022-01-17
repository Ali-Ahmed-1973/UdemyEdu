package com.aliahmed1973.udemyedu.courses

import android.util.Log
import androidx.lifecycle.*
import com.aliahmed1973.udemyedu.model.Course
import com.aliahmed1973.udemyedu.repository.CourseRepository
import kotlinx.coroutines.launch

private const val TAG = "CoursesViewModel"
class CoursesViewModel(private val courseRepository: CourseRepository) : ViewModel() {
private var _pageNum = MutableLiveData<Int>(1)
    val pageNum:LiveData<Int>
        get() = _pageNum

    var count=0

private val _courses = MutableLiveData<List<Course>>()
    val courses :LiveData<List<Course>>
        get() =_courses

    init {

        viewModelScope.launch {
           val job= launch {
                _courses.value = courseRepository.getCoursesFromServer(_pageNum.value!!)
            }
            job.join()
            count=courseRepository.count

        }
    }

//    fun addNum()
//    {
//        if (_pageNum.value!! <100) {
//            _pageNum.value=_pageNum.value!!+1
//        }
//    }

//    fun getCoursesList(pn:Int)
//    {
//        viewModelScope.launch {
//            _courses.value = courseRepository.getCoursesFromServer(pn)
//        }
//    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: CourseRepository): ViewModelProvider.NewInstanceFactory()
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (CoursesViewModel(repository) as T)
        }
    }
}

