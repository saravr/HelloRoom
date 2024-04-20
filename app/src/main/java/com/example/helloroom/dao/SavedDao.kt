package com.example.helloroom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.helloroom.entity.SavedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedDao {
    @Query("SELECT * FROM Saved")
    fun getSaved(): Flow<List<SavedEntity>>

    @Query("UPDATE Saved SET saved = :saved WHERE document_id = :documentId")
    suspend fun updateSaved(documentId: Int, saved: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSaved(savedEntities: List<SavedEntity>)
}
