package com.aliahmed1973.udemyedu.repository

import android.util.Log
import com.aliahmed1973.udemyedu.model.Course
import com.aliahmed1973.udemyedu.model.Review
import com.aliahmed1973.udemyedu.network.CourseApi
import com.aliahmed1973.udemyedu.network.asCourseModel
import com.aliahmed1973.udemyedu.network.asReviewModel

private const val TAG = "CourseRepository"
class CourseRepository {
    var count =0
    suspend fun getCoursesFromServer(page:Int):List<Course>
    {
        return try {
          val networkresponse=  CourseApi.service.getCourses(page)
            count= networkresponse.count
            networkresponse.asCourseModel()
        }catch (e:Exception)
        {
            Log.e(TAG, "getCoursesFromServer: "+e.message )
            emptyList()
        }
    }

    suspend fun getCourseReviewFromServer(courseId:Int):List<Review>
    {
        return try {
            val networkResponse = CourseApi.service.getReviews(courseId)
            Log.d(TAG, "getCourseReviewFromServer: "+"${networkResponse}")
            networkResponse.asReviewModel()
        }catch (e:Exception)
        {
            Log.e(TAG, "getCourseReviewFromServer: "+e.message )
            emptyList()
        }
    }
}