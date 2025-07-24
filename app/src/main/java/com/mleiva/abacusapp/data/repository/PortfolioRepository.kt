package com.mleiva.abacusapp.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mleiva.abacusapp.BuildConfig.API_BASE_URL
import com.mleiva.abacusapp.BuildConfig.API_TOKEN
import com.mleiva.abacusapp.data.remote.ApiService
import com.mleiva.abacusapp.data.remote.RetrofitClient
import com.mleiva.abacusapp.model.Asset
import com.mleiva.abacusapp.model.CashBalance
import com.mleiva.abacusapp.model.Performance
import com.mleiva.abacusapp.model.PortfolioValue
import com.mleiva.abacusapp.model.Position
import com.mleiva.abacusapp.model.Returns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

/***
 * Project: Abacus App
 * From: com.mleiva.abacusapp.data.repository
 * Creted by: Marcelo Leiva on 23-07-2025 at 9:49
 ***/
class PortfolioRepository(
 private val api: ApiService = RetrofitClient.api
) {

 private val client = OkHttpClient()

 suspend fun getPortfolioValue(currency: String = "USD"): PortfolioValue {
  return api.getPortfolioValue(currency)
 }

 suspend fun getReturns(currency: String = "USD"): Returns {
  return api.getReturns(currency)
 }

 suspend fun getPositions(currency: String = "USD"): List<Position> {
  return api.getPositions(currency)
 }

 suspend fun getPerformance(currency: String = "USD"): List<Performance> {
  return api.getPerformance(currency)
 }

 suspend fun getCashBalance(): CashBalance {
  return api.getCashBalance()
 }

 suspend fun searchAssets(query: String): List<Asset> {
  val url = "${API_BASE_URL}assets/search/?query=${query}"
  val request = Request.Builder()
   .url(url)
   .addHeader("Authorization", "$API_TOKEN")
   .build()

  return withContext(Dispatchers.IO) {
   client.newCall(request).execute().use { response ->
    if (!response.isSuccessful) return@use emptyList()
    val body = response.body?.string()
    Gson().fromJson(body, object : TypeToken<List<Asset>>() {}.type)
   }
  }
 }


}

