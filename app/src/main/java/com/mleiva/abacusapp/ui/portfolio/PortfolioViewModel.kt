package com.mleiva.abacusapp.ui.portfolio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.abacusapp.data.repository.PortfolioRepository
import com.mleiva.abacusapp.model.CashBalance
import com.mleiva.abacusapp.model.Performance
import com.mleiva.abacusapp.model.Position
import com.mleiva.abacusapp.model.Returns
import kotlinx.coroutines.launch
import kotlin.contracts.ExperimentalContracts

/***
 * Project: Abacus App
 * From: com.mleiva.abacusapp.ui.portfolio
 * Creted by: Marcelo Leiva on 23-07-2025 at 9:57
 ***/
class PortfolioViewModel : ViewModel() {

 private val repository = PortfolioRepository()

 var portfolioValue by mutableStateOf<String?>(null)
  private set

 @OptIn(ExperimentalContracts::class)
 var returns by mutableStateOf<Returns?>(null)
  private set

 var cashBalance = mutableStateOf<CashBalance?>(null)
  private set

 var performance by mutableStateOf<List<Performance>>(emptyList())
  private set

 var positions by mutableStateOf<List<Position>>(emptyList())
  private set

 var error by mutableStateOf<String?>(null)
  private set

 init {
  fetchPortfolio()
 }

 private fun fetchPortfolio() {
  viewModelScope.launch {
   try {
    val value = repository.getPortfolioValue()
    val ret = repository.getReturns()
    val cashBal = repository.getCashBalance()
    val per = repository.getPerformance()
    val pos = repository.getPositions()

    portfolioValue = value.value
    returns = ret
    cashBalance = mutableStateOf(cashBal)
    performance = per
    positions = pos
   } catch (e: Exception) {
    error = e.localizedMessage ?: "Error desconocido"
   }
  }
 }
}



