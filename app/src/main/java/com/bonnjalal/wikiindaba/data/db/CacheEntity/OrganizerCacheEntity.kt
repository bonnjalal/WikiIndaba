package com.bonnjalal.wikiindaba.data.db.CacheEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "organizers")
data class OrganizerCacheEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "room")
    var room: String,

    @ColumnInfo(name = "phone")
    var phoneNumber: String,

    @ColumnInfo(name = "image")
    var image: String

)
