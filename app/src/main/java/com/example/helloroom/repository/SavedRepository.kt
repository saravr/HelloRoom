package com.example.helloroom.repository

import com.example.helloroom.dao.SavedDao
import com.example.helloroom.entity.SavedEntity
import com.example.helloroom.service.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

class SavedRepository @Inject constructor(
    private val savedDao: SavedDao,
    private val apiService: APIService,
) {
    private val scope = CoroutineScope(Dispatchers.IO)

    val saved = savedDao.getSaved()
        .map { entityList ->
            Timber.e("++++ SAVED: MAP saved ent size ${entityList.size}")
            entityList.map { it.fromEntity() }
        }
        .stateIn(scope, SharingStarted.Eagerly, emptyList())

    suspend fun refresh() {
        refreshSaved()
    }

    private suspend fun refreshSaved() {
        val savedFromAPI = apiService.getSaved()
        val savedEntities = savedFromAPI.map { SavedEntity.toEntity(it) }
        savedDao.insertSaved(savedEntities)
    }

    suspend fun saveDocument(documentId: Int, state: Boolean): Int {
        val result = apiService.saveDocument(documentId, state)
        if (result > -1) {
            savedDao.updateSaved(documentId, state)
        }
        return result
    }
}
