package com.mleiva.abacusapp.model

import kotlin.contracts.ConditionalEffect
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.Returns

/***
 * Project: Abacus App
 * From: com.mleiva.abacusapp.model
 * Creted by: Marcelo Leiva on 23-07-2025 at 11:40
 ***/
data class Returns(
    val return_amount: String,
    val return_percentage: String
)