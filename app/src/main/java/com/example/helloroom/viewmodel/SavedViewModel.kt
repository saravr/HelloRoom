package com.example.helloroom.viewmodel

import androidx.lifecycle.ViewModel
import com.example.helloroom.domain.UpdateSavedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val updateSavedUseCase: UpdateSavedUseCase,
): ViewModel() {
    suspend fun saveDocument(documentId: Int, state: Boolean): Int = updateSavedUseCase.saveDocument(documentId, state)
}