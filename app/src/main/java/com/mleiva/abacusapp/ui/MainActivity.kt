package com.mleiva.abacusapp.ui

import PortfolioScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.mleiva.abacusapp.ui.navigation.BottomNavigationBar
import com.mleiva.abacusapp.ui.navigation.Screen
import com.mleiva.abacusapp.ui.search.SearchScreen
import com.mleiva.abacusapp.ui.theme.AbacusAppTheme

/***
 * Project: Abacus App
 * From: com.mleiva.abacusapp.ui
 * Creted by: Marcelo Leiva on 23-07-2025 at 10:01
 ***/
class MainActivity : ComponentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  enableEdgeToEdge()
   setContent {
       AbacusAppTheme(
           darkTheme = true
       ) {
           Surface(
               modifier = Modifier.fillMaxSize(),
               color = MaterialTheme.colorScheme.background
           ) {
               MyPortfolioApp(Modifier.padding())
           }
         }
      }
  }
}

@Composable
fun MyPortfolioApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->

        val graph =
            navController.createGraph(startDestination = Screen.Portfolio.rout) {
                composable(route = Screen.Portfolio.rout) {
                    PortfolioScreen(onSearchClick = {
                        navController.navigate("search")
                    })
                }
                composable(route = Screen.Search.rout) {
                    SearchScreen(onBack = {
                        navController.popBackStack()
                    })
                }
            }
        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(innerPadding)
        )

    }
}