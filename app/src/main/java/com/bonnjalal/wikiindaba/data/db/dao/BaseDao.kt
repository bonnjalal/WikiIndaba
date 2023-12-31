package com.bonnjalal.wikiindaba.data.db.dao

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update

@Dao
abstract class BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(obj: List<T>): List<Long>

    @Update
    abstract suspend fun update(obj: T)

    @Update
    abstract suspend fun update(obj: List<T>)

    @Transaction
    open suspend fun insertOrUpdate(obj: T) {
        val id = insert(obj)
        if (id == -1L) update(obj)
    }

    @Transaction
    open suspend fun insertOrUpdate(objList: List<T>) {
        val insertResult = insert(objList)
        Log.e("BaseDao", "long list = $insertResult")
        val updateList = mutableListOf<T>()

        for (i in insertResult.indices) {
            if (insertResult[i] == -1L) updateList.add(objList[i])
        }

        if (!updateList.isEmpty()) update(updateList)
    }
}