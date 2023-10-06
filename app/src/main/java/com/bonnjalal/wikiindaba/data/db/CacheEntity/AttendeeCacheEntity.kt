package com.bonnjalal.wikiindaba.data.db.CacheEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendees")
data class AttendeeCacheEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "affiliate")
    var affiliate: String,

    @ColumnInfo(name = "position")
    var position: String,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "room")
    var room: String,

    @ColumnInfo(name = "phone")
    var phoneNumber: String
)
