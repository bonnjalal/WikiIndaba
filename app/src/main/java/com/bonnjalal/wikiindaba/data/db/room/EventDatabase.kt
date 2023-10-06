package com.bonnjalal.wikiindaba.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bonnjalal.wikiindaba.data.db.CacheEntity.AttendeeCacheEntity
import com.bonnjalal.wikiindaba.data.db.CacheEntity.OrganizerCacheEntity
import com.bonnjalal.wikiindaba.data.db.CacheEntity.ProgramCacheEntity
import com.bonnjalal.wikiindaba.data.db.dao.AttendeeDao
import com.bonnjalal.wikiindaba.data.db.dao.OrganizerDao
import com.bonnjalal.wikiindaba.data.db.dao.ProgramDao

@Database(entities = [AttendeeCacheEntity::class , OrganizerCacheEntity::class, ProgramCacheEntity::class], version = 1)
abstract class EventDatabase : RoomDatabase() {
    abstract fun attendeeDao() : AttendeeDao
    abstract fun organizerDao() : OrganizerDao
    abstract fun programDao() : ProgramDao


    companion object {
        val DATABASE_NAME = "event_db"
    }
}