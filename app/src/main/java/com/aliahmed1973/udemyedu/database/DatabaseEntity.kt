package com.aliahmed1973.udemyedu.database

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.aliahmed1973.udemyedu.model.Course
import com.aliahmed1973.udemyedu.model.CourseInstructor
import java.util.*


@Entity(tableName = "mylist_courses")
data class DatabaseMylistCourse(
    @PrimaryKey val id:Int,

    @ColumnInfo(name = "title")val title: String,

    @ColumnInfo(name = "course_url")val url: String,

    @ColumnInfo(name = "is_paid")val isPaid: Boolean,

    @ColumnInfo(name = "price")val price: String,

    @ColumnInfo(name = "published_title") val publishedTitle: String,

    @ColumnInfo(name = "course_image")val courseImage: String,

    @ColumnInfo(name = "headline")val headLine: String,
)

@Entity
data class DatabaseCourseInstructor(
    val name: String,
    @ColumnInfo(name = "jop_title")val jopTitle: String,
    @PrimaryKey
    @ColumnInfo(name = "instructor_image")val instructorImage: String,
    @ColumnInfo(name = "instructor_url")val url: String,
    val mylistId:Int
)
{

}

data class DBCourseWithInstructor(
    @Embedded val mylistCourse: DatabaseMylistCourse,
    @Relation(
        parentColumn = "id", entityColumn = "mylistId"
    )
    val Instructors:List<DatabaseCourseInstructor>
)

fun Course.asDatabaseCourse(): DatabaseMylistCourse {
   return DatabaseMylistCourse(id=id,
        title=title,
        url=url,
        isPaid=isPaid,
        price=price,
        courseImage=courseImage,
        publishedTitle=publishedTitle,
        headLine=headLine)

}

fun CourseInstructor.asDBCourseInstructor(courseid:Int):DatabaseCourseInstructor{
    return DatabaseCourseInstructor(
        name =name,
            jopTitle = jopTitle,
            instructorImage = instructorImage,
            url = url,
        mylistId = courseid
    )
}

fun List<DBCourseWithInstructor>.asCourseModel():List<Course>{
    return map {
        val instructors= it.Instructors.map {
            CourseInstructor(name =it.name,
            jopTitle = it.jopTitle,
            instructorImage = it.instructorImage,
            url = it.url)
        }
        Course(id=it.mylistCourse.id,
        title = it.mylistCourse.title,
        url=it.mylistCourse.url,
        isPaid = it.mylistCourse.isPaid,
            price = it.mylistCourse.price,
            courseImage = it.mylistCourse.courseImage,
            publishedTitle = it.mylistCourse.publishedTitle,
            headLine = it.mylistCourse.headLine, instructor = instructors)
    }
}













//fun Course.asDatabaseCourse(): DBCourseWithInstructor {
//    val databaseMylistCourse=DatabaseMylistCourse(id=id,
//        title=title,
//        url=url,
//        isPaid=isPaid,
//        price=price,
//        courseImage=courseImage,
//        publishedTitle=publishedTitle,
//        headLine=headLine)
//    val databaseCourseInstructor=instructor.map {
//        DatabaseCourseInstructor(name =it.name,
//            jopTitle = it.jopTitle,
//            instructorImage = it.instructorImage,
//            url = it.url, mylistId = id)
//    }
//    return DBCourseWithInstructor(databaseMylistCourse,databaseCourseInstructor)
//}

//return Course(id=id,
//title=title,
//url=url,
//isPaid=isPaid,price=price,courseImage=courseImage,publishedTitle=publishedTitle,headLine=headLine, instructor = )