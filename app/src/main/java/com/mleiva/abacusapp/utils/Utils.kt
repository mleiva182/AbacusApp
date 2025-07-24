package com.mleiva.abacusapp.utils

import java.text.NumberFormat
import java.util.Currency

/***
 * Project: Abacus App
 * From: com.mleiva.abacusapp.utils
 * Creted by: Marcelo Leiva on 24-07-2025 at 10:29
 ***/
class Utils {
    companion object {

        fun formatCurrency(amount: Double, currencyCode: String = "USD"): String {
            val format = NumberFormat.getCurrencyInstance()
            format.currency = Currency.getInstance(currencyCode)
            return format.format(amount)
        }
    }
}