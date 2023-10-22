package com.bonnjalal.wikiindaba.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bonnjalal.wikiindaba.data.db.cache_entity.AttendeeCacheEntity

@Dao
abstract class AttendeeDao: BaseDao<AttendeeCacheEntity>() {

    //    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert (attendeeEntity: AttendeeCacheEntity): Long
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert (attendeeEntity: List<AttendeeCacheEntity>): List<Long>
//
    @Query(value="SELECT * FROM attendees")
    abstract suspend fun get(): List<AttendeeCacheEntity>

}