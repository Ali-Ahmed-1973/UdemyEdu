package com.aliahmed1973.udemyedu.repository

import android.util.Log
import com.aliahmed1973.udemyedu.model.Course
import com.aliahmed1973.udemyedu.network.CourseApi
import com.aliahmed1973.udemyedu.network.asCourseModel

private const val TAG = "CourseRepository"
class CourseRepository {
    suspend fun getCoursesFromServer():List<Course>
    {
        return try {
            CourseApi.service.getCourses().asCourseModel()
        }catch (e:Exception)
        {
            Log.e(TAG, "getCoursesFromServer: "+e.message )
            emptyList()
        }
    }
}