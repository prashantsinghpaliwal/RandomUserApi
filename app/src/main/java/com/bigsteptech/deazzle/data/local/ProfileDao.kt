package com.bigsteptech.deazzle.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {


    @Query("SELECT * FROM profile")
    fun getAll(): Flow<List<Profile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(profiles: List<Profile>)


    @Query("DELETE FROM profile")
    fun deleteAll()

    @Query("UPDATE profile SET likeStatus = :likeStatus WHERE id LIKE :id ")
    suspend fun updateItem(id: Long, likeStatus: Int)

}