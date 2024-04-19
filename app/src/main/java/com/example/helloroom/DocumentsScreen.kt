package com.example.helloroom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.helloroom.model.Document
import com.example.helloroom.model.Saved
import com.example.helloroom.viewmodel.DocumentsViewModel

@Composable
fun DocumentsScreen() {
    val documentsViewModel: DocumentsViewModel = hiltViewModel()
    val documents = documentsViewModel.documents.collectAsStateWithLifecycle(initialValue = emptyList())

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Button(onClick = {
            seedDB(documentsViewModel)
        }) {
            Text("Seed DB")
        }
        Divider()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
        ) {
            items(documents.value) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(it.title, style = MaterialTheme.typography.titleMedium)
                    Text(it.author, style = MaterialTheme.typography.titleSmall)
                    Text(it.pageCount.toString(), style = MaterialTheme.typography.titleSmall, color = Color.DarkGray)
                }
                Divider(color = Color.LightGray)
            }
        }
    }
}

val documents = listOf(
    Document(1, "A story book", "Job B", 121),
    Document(2, "Impressions", "Clark F", 520),
    Document(3, "Wild cats", "J. Brown", 347),
    Document(4, "Bob's memoirs", "Kalki K", 98),
    Document(5, "Travel guide", "Sandilyan B", 155),
    Document(6, "A billionaire's musings", "Tom B", 458),
    Document(7, "Watch tower", "Jack Ryan", 819),
    Document(8, "Deception Point", "Dan Brown", 1029),
    Document(9, "Jurassic Park", "Michael Crichton", 672),
    Document(10, "European travels", "Rick Steves", 309),
)

val saved = listOf(
    Saved(1, false),
    Saved(2, false),
    Saved(3, false),
    Saved(4, false),
    Saved(5, false),
    Saved(6, false),
    Saved(7, false),
    Saved(8, false),
    Saved(9, false),
    Saved(10, false),
)

private fun seedDB(documentsViewModel: DocumentsViewModel) {
    documentsViewModel.insertDocuments(documents)
}
