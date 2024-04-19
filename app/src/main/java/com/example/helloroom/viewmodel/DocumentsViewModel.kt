package com.example.helloroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloroom.model.Document
import com.example.helloroom.repository.DocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(
    private val documentRepository: DocumentRepository,
): ViewModel() {
    val documents = documentRepository.docuemnts.shareIn(viewModelScope, SharingStarted.Eagerly)
    fun insertDocuments(documents: List<Document>) = viewModelScope.launch {
        documentRepository.insertDocuments(documents)
    }
}
