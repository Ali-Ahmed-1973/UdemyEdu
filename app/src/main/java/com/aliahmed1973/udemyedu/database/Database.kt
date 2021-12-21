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


    @Insert
    fun insertCourse(course: DatabaseMylistCourse)

    @Insert
    fun insertCourseInstructor(CourseInstructor: DatabaseCourseInstructor)

    @Delete
    fun deleteCourse(course: DatabaseMylistCourse)

    @Delete
    fun deleteCourseInstructor(course: DatabaseCourseInstructor)
}

@Database(entities = [DatabaseMylistCourse::class,DatabaseCourseInstructor::class],version=1)
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