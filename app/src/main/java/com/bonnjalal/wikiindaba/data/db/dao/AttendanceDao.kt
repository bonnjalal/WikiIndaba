package com.bonnjalal.wikiindaba.data.db.dao

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bonnjalal.wikiindaba.data.db.cache_entity.AttendanceCacheEntity

@Dao
abstract class AttendanceDao: BaseDao<AttendanceCacheEntity>() {


//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert (attendeeEntity: AttendanceCacheEntity): Long

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert (attendeeEntity: List<AttendanceCacheEntity>)

    @Query(value="SELECT * FROM attendance")
    abstract suspend fun get(): List<AttendanceCacheEntity>
    @Query(value="SELECT * FROM attendance where id = :program_id")
    abstract suspend fun getAttendance(program_id:String): AttendanceCacheEntity

    @Delete
    abstract suspend fun delete(attendance:AttendanceCacheEntity)
}
