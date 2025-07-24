package com.mleiva.abacusapp.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/***
 * Project: Abacus App
 * From: com.mleiva.abacusapp.ui.search
 * Creted by: Marcelo Leiva on 23-07-2025 at 14:53
 ***/
@Composable
fun SearchScreen(onBack: () -> Unit) {
    val viewModel = remember { SearchViewModel() }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            /*IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }*/
            Spacer(modifier = Modifier.width(8.dp))
            Text("Buscar Activos", style = MaterialTheme.typography.titleMedium)
        }

        OutlinedTextField(
            value = viewModel.query,
            onValueChange = viewModel::onQueryChange,
            label = { Text("Buscar por nombre o ticker") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(viewModel.results) { asset ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)) {
                    Text("${asset.name} (${asset.ticker})")
                    Text("Moneda: ${asset.currency}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
