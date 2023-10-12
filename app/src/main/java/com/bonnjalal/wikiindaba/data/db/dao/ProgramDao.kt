package com.bonnjalal.wikiindaba.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bonnjalal.wikiindaba.data.db.cache_entity.ProgramCacheEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.ProgramOnlineEntity

@Dao
interface ProgramDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (attendeeEntity: ProgramCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (attendeeEntity: List<ProgramCacheEntity>): Long

    @Query(value="SELECT * FROM program")
    suspend fun get(): List<ProgramCacheEntity>
}