package com.aliahmed1973.udemyedu.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.aliahmed1973.udemyedu.database.*
import com.aliahmed1973.udemyedu.model.Course
import com.aliahmed1973.udemyedu.model.CourseNote
import com.aliahmed1973.udemyedu.model.Review
import com.aliahmed1973.udemyedu.network.CourseApi
import com.aliahmed1973.udemyedu.network.asCourseModel
import com.aliahmed1973.udemyedu.network.asReviewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "CourseRepository"
class CourseRepository(private val database: CourseDatabase) {
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

    suspend fun insertCourseToMylist(course: Course) {
        withContext(Dispatchers.IO)
        {
            try {
                database.courseDao.insertCourse(course.asDatabaseCourse())
                database.courseDao.insertCourseInstructor(course.instructor[0].asDBCourseInstructor(course.id))
            } catch (e: Exception) {
                Log.e(TAG, "insertCourseToMylist: ${e.message}")
            }

        }
    }

    suspend fun insertCourseInstructorToMylist(course: Course) {
        withContext(Dispatchers.IO)
        {
            try {
                database.courseDao.insertCourseInstructor(course.instructor[0].asDBCourseInstructor(course.id))
            } catch (e: Exception) {
                Log.e(TAG, "insertCourseInstructorToMylist: ${e.message}")
            }

        }
    }

    suspend fun insertNoteToMylistCourse(courseNote: CourseNote, courseId:Int)
    {
        withContext(Dispatchers.IO)
        {
            try {
                database.courseDao.insertCourseNote(courseNote.asDBNote(courseId))
            }catch (e: Exception) {
                Log.e(TAG, "insertNotesToCourse: ${e.message}")
            }
        }

    }

    fun getMyCourseslist():LiveData<List<Course>>
    {
        return Transformations.map(database.courseDao.getCourses()){
            it.asCourseModel()
        }
    }

    fun getMyCourseById(id:Int):LiveData<Course?>
    {
        return Transformations.map(database.courseDao.getCourseByID(id)){
            it?.asCourseModel()
        }
    }

    fun getNotesById(id:Int):LiveData<List<CourseNote?>>
    {
        return Transformations.map(database.courseDao.getNotesByCourseId(id)){
            it.asNotesModel()
        }
    }



    suspend fun deleteCourseFromList(course: Course)
    {
        withContext(Dispatchers.IO)
        {
            try {
                database.courseDao.deleteCourse(course.asDatabaseCourse())
                database.courseDao.deleteCourseInstructor(course.instructor[0].asDBCourseInstructor(course.id))
                course.courseNote?.asDBNotes(course.id)
                    ?.let {
                        database.courseDao.deleteCourseNotes(it) }
            }catch (e:Exception)
            {
                Log.e(TAG, "deleteCourseFromList: ${e.message}")
            }
        }
    }
}