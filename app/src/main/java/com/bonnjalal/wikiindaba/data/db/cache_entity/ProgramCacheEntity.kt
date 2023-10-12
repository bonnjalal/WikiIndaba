package com.bonnjalal.wikiindaba.data.db.cache_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "program")
data class ProgramCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "authors")
    var authors: String,

    @ColumnInfo(name = "start_time")
    var startTime: Long,

    @ColumnInfo("end_time")
    var endTime: Long,

    @ColumnInfo(name = "room")
    var room: String,

    @ColumnInfo(name = "responsible")
    var responsible: String,

    @ColumnInfo(name = "slide")
    var slide: String
)
