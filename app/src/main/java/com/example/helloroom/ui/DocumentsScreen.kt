package com.example.helloroom.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.helloroom.viewmodel.DocumentViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun DocumentsScreen() {
    val documentViewModel: DocumentViewModel = hiltViewModel()
    val documents = documentViewModel.documents.collectAsStateWithLifecycle(initialValue = emptyList())
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(
            modifier = Modifier
                .size(64.dp)
                .padding(vertical = 10.dp)
                .clickable {
                    scope.launch {
                        documentViewModel.refresh()
                    }
                },
            imageVector = Icons.Filled.Refresh,
            contentDescription = "Refresh"
        )
        Divider()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            items(documents.value) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 10.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(it.title, style = MaterialTheme.typography.titleMedium)
                        Text(it.author, style = MaterialTheme.typography.titleSmall)
                        Text(
                            it.pageCount.toString(),
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.DarkGray
                        )
                    }
                    Timber.e("++++ SAVED: ${it.title}, saved ${it.saved}")
                    Icon(
                        modifier = Modifier.clickable {
                            scope.launch {
                                val result: Int = documentViewModel.saveDocument(it.id, !it.saved)
                                Timber.e("++++ RESULT $result")
                                if (result == -1) {
                                    Toast.makeText(context, "Failed to save/unsave", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        imageVector = if (it.saved) Icons.Outlined.Bookmark else Icons.Default.Save,
                        contentDescription = ""
                    )
                }
                Divider(color = Color.LightGray)
            }
        }
    }
}
