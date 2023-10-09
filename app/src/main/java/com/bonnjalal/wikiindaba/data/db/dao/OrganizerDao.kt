package com.bonnjalal.wikiindaba.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bonnjalal.wikiindaba.data.online.online_entity.OrganizerOnlineEntity

@Dao
interface OrganizerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (organizerEntity: OrganizerOnlineEntity): Long

    @Query(value="SELECT * FROM organizers")
    suspend fun get(): List<OrganizerOnlineEntity>
}