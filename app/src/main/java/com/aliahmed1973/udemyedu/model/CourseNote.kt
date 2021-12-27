package com.aliahmed1973.udemyedu.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseNote(
    val id:Int,
    val noteText:String
):Parcelable
