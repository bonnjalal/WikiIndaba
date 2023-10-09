package com.bonnjalal.wikiindaba.data.db.cache_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "program")
data class ProgramCacheEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "authors")
    var authors: String,

    @ColumnInfo(name = "date")
    var date: Double,

    @ColumnInfo("time")
    var time: Double,

    @ColumnInfo(name = "hallN")
    var hallNumber: String,

    @ColumnInfo(name = "hall_responsible")
    var hallResponsible: String,

    @ColumnInfo(name = "resp_image")
    var respImage: String
)
