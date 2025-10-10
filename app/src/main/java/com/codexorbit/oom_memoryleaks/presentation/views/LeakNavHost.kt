package com.codexorbit.oom_memoryleaks.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codexorbit.oom_memoryleaks.utils.AppConstants

@Composable
fun LeakNavHost(navController: NavHostController, onLaunchActivity:(String, Boolean) -> Unit = {_,_ ->}) {
    NavHost(modifier = Modifier.fillMaxSize().background(Color.White), navController = navController, startDestination = AppConstants.HOME) {
        // Home Screen
        composable(AppConstants.HOME) {
            HomeScreen(
                onClick = {
                    checkAndProceed(navController,it)
                })
        }


        composable(AppConstants.MEMORY_LEAK) {
            MemoryLeakScreen(
                onClick = {
                    checkAndProceed(navController,it)
                },
                onLaunchActivity = { type, forLeak ->
                    onLaunchActivity(type, forLeak)
                })
        }

        composable(AppConstants.JENKY_SCREEN) {
            JenkyScreen(
                onClick = {
                    checkAndProceed(navController,it)
                })
        }

        composable(AppConstants.JENKY_LIST_SCREEN) {
            JankyListScreen(
                onClick = {
                    checkAndProceed(navController,it)
                })
        }

        composable(AppConstants.SMOOTH_LIST_SCREEN) {
            SmoothListScreen(
                onClick = {
                    checkAndProceed(navController,it)
                })
        }


        composable(AppConstants.CODE_SAMPLE) { CodeSampleScreen {  checkAndProceed(navController,it) } }

        // Leaky screens
        composable(AppConstants.LEAKY_OBSERVER) { LeakyObserverScreen { checkAndProceed(navController,it) } }
        composable(AppConstants.LEAKY_COROUTINE) { LeakyCoroutineScreen { checkAndProceed(navController,it) } }

        // Fixed screens
        composable(AppConstants.FIXED_OBSERVER) { FixedObserverScreen { checkAndProceed(navController,it) } }
        composable(AppConstants.FIXED_COROUTINE) { FixedCoroutineScreen { checkAndProceed(navController,it) } }
    }
}

fun checkAndProceed(navController: NavHostController,route: String){
    if(route == AppConstants.BACK){
        navController.popBackStack()
    }else {
        navController.navigate(route)
    }
}