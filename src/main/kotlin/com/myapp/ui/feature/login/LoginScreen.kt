package com.myapp.ui.feature.login

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.myapp.ui.component.BaseScreen
import com.myapp.ui.feature.homescreen.HomeScreen
import com.myapp.util.ViewModel
import org.koin.compose.koinInject


class LoginScreen : BaseScreen<ViewModel>() {

    @Composable
    override fun getViewModel(): ViewModel = koinInject()


    @Composable
    override fun contentView() {
        val navigator = LocalNavigator.currentOrThrow
        LoginScreenContent(onLoginSuccess = {
            // Navigate to MainScreen on successful login
            navigator.push(HomeScreen())
        })
    }
}
