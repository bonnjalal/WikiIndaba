package com.bonnjalal.wikiindaba.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bonnjalal.wikiindaba.data.db.cache_entity.OrganizerCacheEntity
import com.bonnjalal.wikiindaba.data.online.online_entity.OrganizerOnlineEntity

@Dao
interface OrganizerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (organizerEntity: OrganizerCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (organizerEntity: List<OrganizerCacheEntity>)

    @Query(value="SELECT * FROM organizers")
    suspend fun get(): List<OrganizerCacheEntity>
}