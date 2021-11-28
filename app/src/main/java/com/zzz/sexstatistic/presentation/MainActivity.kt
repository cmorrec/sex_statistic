package com.zzz.sexstatistic.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zzz.sexstatistic.presentation.theme.SexStatisticTheme
import com.zzz.sexstatistic.presentation.auth.AuthScreen
import com.zzz.sexstatistic.presentation.calendar.CalendarScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SexStatisticTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.MAIN,
                    ) {
                        composable(route = Routes.SIGN_IN) {
                            AuthScreen(navController = navController)
                        }
                        composable(route = Routes.MAIN) {
                            CalendarScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
