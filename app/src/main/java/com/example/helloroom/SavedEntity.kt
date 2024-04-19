package com.example.helloroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Saved")
data class SavedEntity(
    @PrimaryKey
    @ColumnInfo("document_id")
    val documentId: Int,
    @ColumnInfo("saved")
    val saved: Boolean,
)
