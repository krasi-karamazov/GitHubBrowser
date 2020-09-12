package kpk.dev.data.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update

interface BaseDao<T> {
    fun select(id: Long): T
    fun select(): List<T>
    fun search(value: String): List<T>

    @Insert(onConflict = REPLACE)
    fun insertList(item: List<T>)

    @Update(onConflict = REPLACE)
    fun update(item: T)

    @Update(onConflict = REPLACE)
    fun updateList(items: List<T>)

    @Delete
    fun delete(item: T)

    fun deleteRange(firstId: Long, lastId: Long)

    fun truncate()
}