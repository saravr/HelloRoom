package com.example.helloroom.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.helloroom.model.Document
import com.example.helloroom.model.Saved

@Entity(tableName = "Saved")
data class SavedEntity(
    @PrimaryKey
    @ColumnInfo("document_id")
    val documentId: Int,
    @ColumnInfo("saved")
    val saved: Boolean,
) {
    fun fromEntity(): Saved {
        return Saved(documentId, saved)
    }

    companion object {
        fun toEntity(saved: Saved): SavedEntity {
            return SavedEntity(saved.documentId, saved.saved)
        }
    }
}
