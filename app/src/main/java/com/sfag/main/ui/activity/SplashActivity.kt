package com.sfag.main.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sfag.automata.theme.Theme
import com.sfag.automata.ui.activity.SetDefaultSettings
import com.sfag.main.ui.screen.SplashScreen
import com.sfag.shared.ui.navigation.Destinations
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    private var intentToMainActivity: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Theme {
                SetDefaultSettings()
                rememberNavController().apply {
                    NavHost(
                        navController = this,
                        startDestination = Destinations.SPLASH.route
                    ) {
                        composable(Destinations.SPLASH.route) {
                            SplashScreen(lifecycleScope, navigateToNextScreen = ::navigateToMainActivity, ::navigateToMainActivity)
                        }
                    }
                }
            }
        }
    }

    private fun navigateToMainActivity() {
        if (intentToMainActivity == null) intentToMainActivity =
            Intent(this, MainActivity::class.java)
        startActivity(intentToMainActivity)
        finish()
    }
}
