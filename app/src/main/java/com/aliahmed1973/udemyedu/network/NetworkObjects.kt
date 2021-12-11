package com.aliahmed1973.udemyedu.network

import com.aliahmed1973.udemyedu.model.Course
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class NetworkCoursesContainer(@Json(name = "results") val courses: List<NetworkCourse>)

@JsonClass(generateAdapter = true)
data class NetworkCourse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "is_paid")
    val isPaid: Boolean,
    @Json(name = "price")
    val price: String,
    @Json(name = "image_480x270")
    val courseImage: String,
    @Json(name = "published_title")
    val publishedTitle: String,
    @Json(name = "headline")
    val headLine: String
)

fun NetworkCoursesContainer.asCourseModel(): List<Course> {
    return courses.map {
        Course(
            id = it.id, title = it.title, url = it.url,
            isPaid = it.isPaid, price = it.price, courseImage = it.courseImage,
            publishedTitle = it.publishedTitle, headLine = it.headLine
        )
    }
}
