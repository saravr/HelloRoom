package com.example.helloroom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedDao {
    @Query("SELECT * FROM Saved")
    fun getSaved(): Flow<List<SavedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSaved(savedEntities: List<SavedEntity>)
}