package com.zzz.sexstatistic.presentation

import com.zzz.sexstatistic.presentation.navigation.Menu
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zzz.sexstatistic.presentation.theme.SexStatisticTheme
import com.zzz.sexstatistic.presentation.auth.AuthScreen
import com.zzz.sexstatistic.presentation.main.CalendarScreen
import com.zzz.sexstatistic.presentation.navigation.NavBar
import com.zzz.sexstatistic.presentation.sexEdit.SexEdit
import dagger.hilt.android.AndroidEntryPoint

val SCREEN_ROUTES_WITHOUT_NAVBAR = listOf(Routes.SIGN_IN, Routes.SIGN_UP)

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SexStatisticTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
                    val scope = rememberCoroutineScope()
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            if (navBackStackEntry?.destination?.route !in SCREEN_ROUTES_WITHOUT_NAVBAR) {
                                NavBar(scope = scope, scaffoldState = scaffoldState)
                            }
                        },
                        drawerContent = {
                            Menu(scope = scope, scaffoldState = scaffoldState, navController = navController)
                        },
                    ) {
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
                            composable(route = "/sex/{sexId}/edit") { backStackEntry ->
                                SexEdit(sexId = backStackEntry.arguments?.getString("sexId"))
                            }
                            composable(route = Routes.NEX_SEX) {
                                SexEdit(sexId = null)
                            }
                        }
                    }
                }
            }
        }
    }
}
