package com.example.helloroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloroom.model.Document
import com.example.helloroom.repository.DocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(
    private val documentRepository: DocumentRepository,
): ViewModel() {
    fun insertDocuments(documents: List<Document>) = viewModelScope.launch {
        documentRepository.insertDocuments(documents)
    }
}
