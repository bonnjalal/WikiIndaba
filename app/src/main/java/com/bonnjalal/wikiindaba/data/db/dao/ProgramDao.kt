package com.bonnjalal.wikiindaba.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bonnjalal.wikiindaba.data.db.CacheEntity.AttendeeCacheEntity
import com.bonnjalal.wikiindaba.data.db.CacheEntity.ProgramCacheEntity

@Dao
interface ProgramDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (attendeeEntity: ProgramCacheEntity): Long

    @Query(value="SELECT * FROM program")
    suspend fun get(): List<ProgramCacheEntity>
}