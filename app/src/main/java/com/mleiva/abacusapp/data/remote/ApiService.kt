package com.mleiva.abacusapp.data.remote

import com.mleiva.abacusapp.model.Asset
import com.mleiva.abacusapp.model.CashBalance
import com.mleiva.abacusapp.model.Performance
import com.mleiva.abacusapp.model.PortfolioValue
import com.mleiva.abacusapp.model.Position
import com.mleiva.abacusapp.model.Returns
import retrofit2.http.GET
import retrofit2.http.Query

/***
 * Project: Abacus App
 * From: com.mleiva.abacusapp.data.remote
 * Creted by: Marcelo Leiva on 23-07-2025 at 9:38
 ***/
interface ApiService {

  @GET("portfolios/value/")
  suspend fun getPortfolioValue(@Query("currency") currency: String = "USD"): PortfolioValue

  @GET("portfolios/performance/ytd/")
  suspend fun getPerformance(@Query("currency") currency: String = "USD"): List<Performance>

  @GET("accounts/cash-balance/")
  suspend fun getCashBalance(): CashBalance

  @GET("portfolios/returns/ytd/")
  suspend fun getReturns(@Query("currency") currency: String = "USD"): Returns

  @GET("portfolios/positions/")
  suspend fun getPositions(@Query("currency") currency: String = "USD"): List<Position>

  @GET("assets/search/")
  suspend fun searchAssets(@Query("query") query: String): List<Asset>
 }

