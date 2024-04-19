package com.example.helloroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
)
