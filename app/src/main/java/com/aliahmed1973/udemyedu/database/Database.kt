package com.aliahmed1973.udemyedu.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.aliahmed1973.udemyedu.model.Course

@Dao
interface CourseDao{
    @Transaction
    @Query("SELECT * FROM mylist_courses")
    fun getCourses(): LiveData<List<DBCourseWithInstructor>>

    @Transaction
    @Query("SELECT * FROM mylist_courses WHERE id = :id")
    fun getCourseByID(id:Int):LiveData<DBCourseWithInstructor?>

//    @Query("SELECT * FROM DatabaseCourseNote WHERE mylistCourseId = :id")
//    fun getNotesByCourseId(id:Int):LiveData<List<DatabaseCourseNote>>


    @Insert
    fun insertCourse(course: DatabaseMylistCourse)

    @Insert
    fun insertCourseInstructor(CourseInstructor: DatabaseCourseInstructor)

    @Insert
    fun insertCourseNote(Note: DatabaseCourseNote)

    @Delete
    fun deleteCourse(course: DatabaseMylistCourse)

    @Delete
    fun deleteCourseInstructor(course: DatabaseCourseInstructor)

    @Delete
    fun deleteCourseNotes(Notes: List<DatabaseCourseNote?>)
}

@Database(entities = [DatabaseMylistCourse::class,DatabaseCourseInstructor::class,DatabaseCourseNote::class],version=1)
abstract class CourseDatabase:RoomDatabase(){
    abstract val courseDao:CourseDao
}

private lateinit var INSTANCE: CourseDatabase

fun getDatabase(context: Context): CourseDatabase {
    if (!::INSTANCE.isInitialized) {
        synchronized(CourseDatabase::class.java)
        {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                CourseDatabase::class.java,
                "courses"
            ).build()
        }
    }
    return INSTANCE
}