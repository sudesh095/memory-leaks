package com.codexorbit.oom_memoryleaks.presentation.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codexorbit.oom_memoryleaks.utils.AppConstants

@Composable
fun LeakNavHost(navController: NavHostController, onLaunchActivity:(String, Boolean) -> Unit = {_,_ ->}) {
    NavHost(navController, startDestination = AppConstants.HOME) {
        // Home Screen
        composable(AppConstants.HOME) {
            HomeScreen(
                onClick = {
                    navController.navigate(it)
                },
                onLaunchActivity = { type, forLeak ->
                    onLaunchActivity(type, forLeak)
                })
        }

        // Leaky screens
        composable(AppConstants.LEAKY_OBSERVER) { LeakyObserverScreen { navController.popBackStack() } }
        composable(AppConstants.LEAKY_COROUTINE) { LeakyCoroutineScreen { navController.popBackStack() } }

        // Fixed screens
        composable(AppConstants.FIXED_OBSERVER) { FixedObserverScreen { navController.popBackStack() } }
        composable(AppConstants.FIXED_COROUTINE) { FixedCoroutineScreen { navController.popBackStack() } }
    }
}