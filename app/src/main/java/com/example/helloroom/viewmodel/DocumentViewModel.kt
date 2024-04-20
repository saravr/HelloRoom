package com.example.helloroom.viewmodel

import androidx.lifecycle.ViewModel
import com.example.helloroom.domain.GetDocumentsUseCase
//import com.example.helloroom.domain.UpdateSavedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DocumentViewModel @Inject constructor(
    private val getDocumentsUseCase: GetDocumentsUseCase,
    //private val updateSavedUseCase: UpdateSavedUseCase,
): ViewModel() {
    val documents = getDocumentsUseCase.documents

    suspend fun refresh() = {} //documentRepository.refresh()

    suspend fun saveDocument(documentId: Int, state: Boolean): Int = getDocumentsUseCase.saveDocument(documentId, state)
}
