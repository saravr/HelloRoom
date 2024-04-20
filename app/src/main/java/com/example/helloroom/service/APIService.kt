package com.example.helloroom.service

import com.example.helloroom.model.Document
import com.example.helloroom.model.Saved
import kotlinx.coroutines.delay
import javax.inject.Inject

class APIService @Inject constructor() {
    private val documents = listOf(
        Document(1, "A story book", "Joe Black", 121),
        Document(2, "Impressions", "Clark F", 520),
        Document(3, "Wild cats", "J. Brown", 347),
        Document(4, "Bob's memoirs", "Kalki K", 98),
        Document(5, "Travel guide", "Sandilyan B", 155),
        Document(6, "A billionaire's musings", "Tom B", 458),
        Document(7, "Watch tower", "Jack Ryan", 819),
        Document(8, "Deception Point", "Dan Brown", 1029),
        Document(9, "Jurassic Park", "Michael Crichton", 672),
        Document(10, "European travels", "Rick Steves", 309),
    )

    private val saved = mutableListOf(
        Saved(1, false),
        Saved(2, false),
        Saved(3, false),
        Saved(4, false),
        Saved(5, false),
        Saved(6, false),
        Saved(7, false),
        Saved(8, false),
        Saved(9, false),
        Saved(10, false),
    )

    suspend fun getDocuments(): List<Document> {
        delay(5000L)
        return documents
    }

    suspend fun getSaved(): List<Saved> {
        delay(500L)
        return saved
    }

    suspend fun saveDocument(documentId: Int, state: Boolean): Int {
        delay(1000L)
        val index = saved.indexOfFirst { it.documentId == documentId }
        if (index > -1 && index < saved.size) {
            saved[index] = saved[index].copy(saved = state)
        }
        return index
    }
}