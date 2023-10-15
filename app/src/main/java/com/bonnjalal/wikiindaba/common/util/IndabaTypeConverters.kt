package com.bonnjalal.wikiindaba.common.util

import androidx.room.TypeConverter
import com.bonnjalal.wikiindaba.presentation.model.Attendance
import com.google.gson.Gson
import java.util.Date

class IndabaTypeConverters {

    @TypeConverter
    fun fromDateToLong(date: Date): Long {
        return date.time
    }
    @TypeConverter
    fun fromLongToDate(date: Long): Date {
        return Date(date)
    }
    @TypeConverter
    fun fromClothingToJSON(clothinglist: Attendance): String {
        return Gson().toJson(clothinglist)
    }
    @TypeConverter
    fun fromJSONToAttendance(json: String): Attendance {
        return Gson().fromJson(json,Attendance::class.java)
    }
}