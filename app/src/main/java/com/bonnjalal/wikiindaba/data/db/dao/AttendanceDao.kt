package com.bonnjalal.wikiindaba.data.db.dao

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bonnjalal.wikiindaba.data.db.cache_entity.AttendanceCacheEntity

@Dao
interface AttendanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (attendeeEntity: AttendanceCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (attendeeEntity: List<AttendanceCacheEntity>)

    @Query(value="SELECT * FROM attendance")
    suspend fun get(): List<AttendanceCacheEntity>
    @Query(value="SELECT * FROM attendance where id = :program_id")
    suspend fun getAttendance(program_id:String): AttendanceCacheEntity

}
