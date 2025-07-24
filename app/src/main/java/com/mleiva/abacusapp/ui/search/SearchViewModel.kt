package com.mleiva.abacusapp.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.abacusapp.data.repository.PortfolioRepository
import com.mleiva.abacusapp.model.Asset
import kotlinx.coroutines.launch

/***
 * Project: Abacus App
 * From: com.mleiva.abacusapp.ui.search
 * Creted by: Marcelo Leiva on 23-07-2025 at 14:49
 ***/
class SearchViewModel : ViewModel() {

 private val repository = PortfolioRepository()

 var query by mutableStateOf("")
 var results by mutableStateOf<List<Asset>>(emptyList())

 fun onQueryChange(newQuery: String) {
  query = newQuery
  searchAssets()
 }

 private fun searchAssets() {
  viewModelScope.launch {
   results = repository.searchAssets(query)
  }
 }
}
