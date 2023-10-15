package com.bonnjalal.wikiindaba.data.db.cache_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bonnjalal.wikiindaba.presentation.model.Attendance

@Entity(tableName = "attendance")
data class AttendanceCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var programId: String,

    @ColumnInfo(name = "attendance")
    var name: Attendance,

)
