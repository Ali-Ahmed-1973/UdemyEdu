package com.aliahmed1973.udemyedu.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Course(
                   val id:Int,

                   val title:String,

                   val url:String,

                   val isPaid:Boolean,

                   val price:String,

                   val courseImage:String,

                   val publishedTitle:String,

                   val headLine:String):Parcelable
