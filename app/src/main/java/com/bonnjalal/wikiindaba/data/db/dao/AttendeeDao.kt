package com.bonnjalal.wikiindaba.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bonnjalal.wikiindaba.data.db.cache_entity.AttendeeCacheEntity

@Dao
interface AttendeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (attendeeEntity: AttendeeCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (attendeeEntity: List<AttendeeCacheEntity>): Long

    @Query(value="SELECT * FROM attendees")
    suspend fun get(): List<AttendeeCacheEntity>

    
}