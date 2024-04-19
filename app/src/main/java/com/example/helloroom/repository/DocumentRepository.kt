package com.example.helloroom.repository

import com.example.helloroom.dao.DocumentsDao
import com.example.helloroom.entity.DocumentEntity
import com.example.helloroom.model.Document
import javax.inject.Inject

class DocumentRepository @Inject constructor(
    private val documentsDao: DocumentsDao,
) {
    suspend fun insertDocuments(documents: List<Document>) {
        val documentEntities = documents.map { DocumentEntity.toEntity(it) }
        documentsDao.insertDocuments(documentEntities)
    }
}
