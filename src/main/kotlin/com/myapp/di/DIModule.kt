package com.myapp.di

import com.myapp.ui.feature.homescreen.HomeScreenViewModel
import com.myapp.ui.feature.main.MainViewModel
import com.myapp.ui.feature.notification.NotificationViewModel
import com.myapp.ui.feature.product.ProductViewModel
import com.myapp.ui.feature.splash.SplashViewModel
import org.koin.dsl.module


val appModule = module {
    single { SplashViewModel() }
    single { MainViewModel() }
    single { HomeScreenViewModel() }
    single { NotificationViewModel() }
    single { ProductViewModel(get()) }
}
