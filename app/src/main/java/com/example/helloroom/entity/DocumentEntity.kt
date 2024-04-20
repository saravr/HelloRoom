package com.example.helloroom.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.helloroom.model.Document

@Entity(tableName = "Documents")
data class DocumentEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("author")
    val author: String,
    @ColumnInfo("page_count")
    val pageCount: Int,
    @ColumnInfo("saved")
    val saved: Boolean,
) {
    fun fromEntity(): Document {
        return Document(id, title, author, pageCount, saved)
    }

    companion object {
        fun toEntity(document: Document): DocumentEntity {
            return DocumentEntity(
                document.id, document.title, document.author, document.pageCount, document.saved
            )
        }
    }
}
