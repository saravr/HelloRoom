package com.example.helloroom.viewmodel

import androidx.lifecycle.ViewModel
import com.example.helloroom.domain.GetDocumentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DocumentViewModel @Inject constructor(
    private val getDocumentsUseCase: GetDocumentsUseCase,
): ViewModel() {
    val documents = getDocumentsUseCase.documents

    suspend fun refresh() = getDocumentsUseCase.refresh()
}
