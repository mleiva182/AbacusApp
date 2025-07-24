package com.mleiva.abacusapp.model

/***
 * Project: Abacus App
 * From: com.mleiva.abacusapp.model
 * Creted by: Marcelo Leiva on 23-07-2025 at 11:40
 ***/
data class Asset(
 val ticker: String,
 val name: String,
 val currency: String,
 val is_favorite: Boolean,
 val fractionable: Boolean
)
