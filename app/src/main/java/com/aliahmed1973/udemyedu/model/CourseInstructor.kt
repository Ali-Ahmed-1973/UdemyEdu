package com.aliahmed1973.udemyedu.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CourseInstructor(
    val name: String,
    val jopTitle: String,
    val instructorImage: String,
    val url: String
):Parcelable {
}