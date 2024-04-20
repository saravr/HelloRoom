package com.example.helloroom.model

data class Document(
    val id: Int,
    val title: String,
    val author: String,
    val pageCount: Int,
    val saved: Boolean = false,
)