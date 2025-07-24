package com.mleiva.abacusapp.data.remote

import com.mleiva.abacusapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/***
 * Project: Abacus App
 * From: com.mleiva.abacusapp.data.remote
 * Creted by: Marcelo Leiva on 23-07-2025 at 11:33
 ***/
object RetrofitClient {

 private val logging = HttpLoggingInterceptor().apply {
  level = HttpLoggingInterceptor.Level.BODY
 }

 private val okHttpClient = OkHttpClient.Builder()
  .addInterceptor(logging)
  .addInterceptor { chain ->
   val request = chain.request().newBuilder()
    .addHeader("Authorization", BuildConfig.API_TOKEN)
    .build()
   chain.proceed(request)
  }
  .build()

 val api: ApiService by lazy {
  Retrofit.Builder()
   .baseUrl(BuildConfig.API_BASE_URL)
   .client(okHttpClient)
   .addConverterFactory(GsonConverterFactory.create())
   .build()
   .create(ApiService::class.java)
 }
}
