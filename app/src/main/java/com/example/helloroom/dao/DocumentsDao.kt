package com.example.helloroom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.helloroom.entity.DocumentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DocumentsDao {
    @Query("SELECT * FROM Documents")
    fun getDocuments(): Flow<List<DocumentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocuments(documentEntities: List<DocumentEntity>)
}
