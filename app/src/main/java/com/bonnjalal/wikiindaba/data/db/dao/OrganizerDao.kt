package com.bonnjalal.wikiindaba.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bonnjalal.wikiindaba.data.db.CacheEntity.AttendeeCacheEntity
import com.bonnjalal.wikiindaba.data.db.CacheEntity.OrganizerCacheEntity

@Dao
interface OrganizerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (organizerEntity: OrganizerCacheEntity): Long

    @Query(value="SELECT * FROM organizers")
    suspend fun get(): List<OrganizerCacheEntity>
}