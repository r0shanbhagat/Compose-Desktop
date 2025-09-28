package com.myapp.ui.feature.login

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.myapp.di.AppComponent
import com.myapp.ui.feature.homescreen.HomeScreen
import javax.inject.Inject


class LoginScreen @Inject constructor(
    private val appComponent: AppComponent
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        LoginScreenContent(onLoginSuccess = {
            // Navigate to MainScreen on successful login
            navigator.push(HomeScreen(appComponent))
        })
    }
}


