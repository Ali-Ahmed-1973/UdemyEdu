package com.aliahmed1973.udemyedu.coursedetails

import android.util.Log
import androidx.lifecycle.*
import com.aliahmed1973.udemyedu.model.Course
import com.aliahmed1973.udemyedu.model.Review
import com.aliahmed1973.udemyedu.repository.CourseRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val TAG = "CourseDetailsViewModel"
class CourseDetailsViewModel(private val repository: CourseRepository) : ViewModel() {

    private val _courseDetails = MutableLiveData<Course>()
    val courseDetails:LiveData<Course>
    get() = _courseDetails

    private var _courseId = MutableStateFlow(0)


    val courseReview:LiveData<List<Review>> = _courseId.flatMapLatest {
    flow {
       emit(repository.getCourseReviewFromServer(it))
    }
    }.asLiveData()


     val databaseCourse:LiveData<Course?> = _courseId.flatMapLatest {
         repository.getMyCourseById(it)
     }.asLiveData()



    fun checkCourseInDatabase(course: Course)
    {
        _courseDetails.value=course
        _courseId.value = course.id

    }


    fun setCourse(course: Course)
    {
        _courseDetails.value=course
    }

    fun addOrRemoveCourseFromList()
    {
        Log.d(TAG, "setCourseDetails: "+courseDetails.value)
        viewModelScope.launch {
            _courseDetails.value?.let {
                if(it.isAddedToMylist)
                {
                    repository.deleteCourseFromList(it)
                }else
                {
                    it.isAddedToMylist=true
                    repository.insertCourseToMylist(it)
                    repository.insertCourseInstructorToMylist(it)
                }
            }
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