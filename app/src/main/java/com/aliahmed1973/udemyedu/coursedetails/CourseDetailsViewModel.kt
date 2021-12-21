package com.aliahmed1973.udemyedu.coursedetails

import android.util.Log
import androidx.lifecycle.*
import com.aliahmed1973.udemyedu.model.Course
import com.aliahmed1973.udemyedu.model.Review
import com.aliahmed1973.udemyedu.repository.CourseRepository
import kotlinx.coroutines.launch

private const val TAG = "CourseDetailsViewModel"
class CourseDetailsViewModel(private val repository: CourseRepository) : ViewModel() {

    private val _courseDetails = MutableLiveData<Course>()
    val courseDetails:LiveData<Course>
    get() = _courseDetails

    private val _courseReview =MutableLiveData<List<Review>>()
    val courseReview:LiveData<List<Review>>
    get() = _courseReview

    fun setCourseDetails(course: Course)
    {
        _courseDetails.value=course
        viewModelScope.launch {
            _courseReview.value= repository.getCourseReviewFromServer(course.id)
        }

    }
    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: CourseRepository): ViewModelProvider.NewInstanceFactory()
    {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (CourseDetailsViewModel(repository) as T)
        }
    }
}