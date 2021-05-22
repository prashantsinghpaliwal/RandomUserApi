package com.bigsteptech.deazzle.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDao {


    @Query("SELECT * FROM profile")
    fun getAll(): LiveData<List<Profile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(profiles: List<Profile>)


    @Query("DELETE FROM profile")
    fun deleteAll()

    @Query("UPDATE profile SET likeStatus = :likeStatus WHERE id LIKE :id ")
    suspend fun updateItem(id: Long, likeStatus: Int)

}