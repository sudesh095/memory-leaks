package com.codexorbit.oom_memoryleaks.presentation.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.codexorbit.oom_memoryleaks.utils.AppConstants
import com.codexorbit.oom_memoryleaks.presentation.views.LeakNavHost
import com.codexorbit.oom_memoryleaks.ui.theme.OOMMemoryLeaksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        askNotificationPermission()
        setContent {
            OOMMemoryLeaksTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    Column(Modifier.Companion.padding(innerPadding).padding(16.dp)) {
                        LeakNavHost(navController, onLaunchActivity = { type, forLeak ->
                            when (type) {
                                AppConstants.LEAKY_CONTEXT -> {
                                    startActivity(
                                        ContextLeakActivity.getInstance(
                                            this@MainActivity,
                                            forLeak
                                        )
                                    )
                                }

                                AppConstants.LEAKY_VIEW -> {
                                    startActivity(
                                        ViewLeakActivity.getInstance(
                                            this@MainActivity,
                                            forLeak
                                        )
                                    )
                                }

                                AppConstants.LEAKY_HANDLER -> {
                                    startActivity(
                                        HandlerLeakActivity.getInstance(
                                            this@MainActivity,
                                            forLeak
                                        )
                                    )
                                }

                                AppConstants.LEAKY_COROUTINE_ACTIVITY -> {
                                    startActivity(
                                        CoroutineLeakActivity.getInstance(
                                            this@MainActivity,
                                            forLeak
                                        )
                                    )
                                }

                                AppConstants.LEAKY_RECEIVER -> {
                                    startActivity(
                                        ReceiverLeakActivity.getInstance(
                                            this@MainActivity,
                                            forLeak
                                        )
                                    )
                                }


                                else -> {

                                }
                            }
                        })
                    }
                }
            }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission granted → you can start your foreground service safely
            } else {
                Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}