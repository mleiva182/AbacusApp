package com.mleiva.abacusapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

/***
 * Project: Abacus App
 * From: com.mleiva.abacusapp.ui.navigation
 * Creted by: Marcelo Leiva on 24-07-2025 at 10:07
 ***/
data class NavigationItem(
 val title: String,
 val icon: ImageVector,
 val route: String
)

val navigationItems = listOf(
 NavigationItem(
  title = "Portafolio",
  icon = Icons.Default.Home,
  route = Screen.Portfolio.rout
 ),
 NavigationItem(
  title = "Buscar",
  icon = Icons.Default.Search,
  route = Screen.Search.rout
 )
)

sealed class Screen(val rout: String) {
 object Portfolio: Screen("portfolio_screen")
 object Search: Screen("search_screen")
}