package com.bonnjalal.wikiindaba.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bonnjalal.wikiindaba.common.util.IndabaTypeConverters
import com.bonnjalal.wikiindaba.data.db.cache_entity.AttendanceCacheEntity
import com.bonnjalal.wikiindaba.data.db.cache_entity.AttendeeCacheEntity
import com.bonnjalal.wikiindaba.data.db.cache_entity.OrganizerCacheEntity
import com.bonnjalal.wikiindaba.data.db.cache_entity.ProgramCacheEntity
import com.bonnjalal.wikiindaba.data.db.dao.AttendanceDao
import com.bonnjalal.wikiindaba.data.online.online_entity.AttendeeOnlineEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.OrganizerOnlineEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.ProgramOnlineEntity
import com.bonnjalal.wikiindaba.data.db.dao.AttendeeDao
import com.bonnjalal.wikiindaba.data.db.dao.OrganizerDao
import com.bonnjalal.wikiindaba.data.db.dao.ProgramDao

@TypeConverters(value = [IndabaTypeConverters::class])
@Database(entities = [AttendeeCacheEntity::class , OrganizerCacheEntity::class, ProgramCacheEntity::class, AttendanceCacheEntity::class], version = 6)
abstract class EventDatabase : RoomDatabase() {
    abstract fun attendeeDao() : AttendeeDao
    abstract fun organizerDao() : OrganizerDao
    abstract fun programDao() : ProgramDao
    abstract fun attendanceDao() : AttendanceDao


    companion object {
        val DATABASE_NAME = "event_db"
    }
}