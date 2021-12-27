package com.aliahmed1973.udemyedu.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class CourseNote(
    val id:String = UUID.randomUUID().toString(),
    val noteText:String
):Parcelable
