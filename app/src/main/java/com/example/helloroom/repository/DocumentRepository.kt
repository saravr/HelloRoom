package com.example.helloroom.repository

import com.example.helloroom.dao.DocumentsDao
import com.example.helloroom.dao.SavedDao
import com.example.helloroom.entity.DocumentEntity
import com.example.helloroom.entity.SavedEntity
import com.example.helloroom.service.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class DocumentRepository @Inject constructor(
    private val documentsDao: DocumentsDao,
    private val savedDao: SavedDao,
    private val apiService: APIService,
) {
    private val scope = CoroutineScope(Dispatchers.IO)
    init {
        scope.launch {
            refresh()
        }
    }
    val documents = documentsDao
        .getDocuments()
        .map { entityList -> entityList.map { it.fromEntity() } }
        .stateIn(scope, SharingStarted.Eagerly, emptyList())

    val saved = savedDao.getSaved()
        .map { entityList -> entityList.map { it.fromEntity() } }
        .stateIn(scope, SharingStarted.Eagerly, emptyList())

    suspend fun refresh() {
        refreshSaved()
        refreshDocuments()
    }

    private suspend fun refreshSaved() {
        val savedFromAPI = apiService.getSaved()
        val savedEntities = savedFromAPI.map { SavedEntity.toEntity(it) }
        savedDao.insertSaved(savedEntities)
    }

    private suspend fun refreshDocuments() {
        val documentsFromAPI = apiService.getDocuments()
        val documentEntities = documentsFromAPI.map { DocumentEntity.toEntity(it) }
        documentsDao.insertDocuments(documentEntities)
    }

    suspend fun saveDocument(documentId: Int, state: Boolean): Int {
        val result = apiService.saveDocument(documentId, state)
        if (result > -1) {
            savedDao.updateSaved(documentId, state)
        }
        return result
    }
}
