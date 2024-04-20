package com.example.helloroom.domain

import com.example.helloroom.model.Document
import com.example.helloroom.repository.DocumentRepository
import com.example.helloroom.repository.SavedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class GetDocumentsUseCase @Inject constructor(
    private val documentRepository: DocumentRepository,
    private val savedRepository: SavedRepository,
) {
    private val _documents = MutableStateFlow<List<Document>>(emptyList())
    val documents = _documents.asStateFlow()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        coroutineScope.launch {
            val combinedFlow = combine(
                documentRepository.documents,
                savedRepository.saved,
            ) { d, s ->
                Timber.e("++++ VM: TRANSFORM ${d.size} / ${s.size}")
                Pair(d, s)
            }

            combinedFlow.collectLatest { (documentsList, savedList) ->
                Timber.e("++++ VM: COLLECT: DOCS ${documentsList.size}, SAVED ${savedList.size}")
                val newList = documentsList.map {
                    savedList.firstOrNull { saved -> saved.documentId == it.id }?.let { saved ->
                        it.copy(saved = saved.saved)
                    } ?: run {
                        it
                    }
                }
                _documents.emit(newList)
            }
        }
    }

    suspend fun refresh() {
        savedRepository.refresh()
        documentRepository.refresh()
    }
}
