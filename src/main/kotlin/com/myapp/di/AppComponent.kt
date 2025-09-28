package com.myapp.di

import com.myapp.ui.feature.homescreen.HomeScreen
import com.myapp.ui.feature.login.LoginScreen
import com.myapp.ui.feature.main.MainScreen
import com.myapp.ui.feature.splash.SplashScreen
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        // Add your modules here
    ]
)
interface AppComponent {
    fun inject(splashScreen: SplashScreen)
    fun inject(loginScreen: LoginScreen)
    fun inject(homeScreen: HomeScreen)
    fun inject(mainScreen: MainScreen)
}