package com.example.helloroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloroom.model.Document
import com.example.helloroom.repository.DocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DocumentViewModel @Inject constructor(
    private val documentRepository: DocumentRepository,
): ViewModel() {
    private val _documents = MutableStateFlow<List<Document>>(emptyList())
    val documents = _documents.asStateFlow()

    init {
        viewModelScope.launch {
            val combinedFlow = combine(
                documentRepository.documents,
                documentRepository.saved,
            ) { d, s -> Pair(d, s) }

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

    suspend fun refresh() = documentRepository.refresh()

    suspend fun saveDocument(documentId: Int, state: Boolean): Int = documentRepository.saveDocument(documentId, state)
}
