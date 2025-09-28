package com.myapp.ui.feature.main

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import com.myapp.di.AppComponent
import com.myapp.ui.component.ContentView
import com.myapp.ui.feature.passcode.PasscodeScreen
import javax.inject.Inject

class MainScreen(
    val appComponent: AppComponent,
) : Screen {
    @Inject
    lateinit var viewModel: MainViewModel

    init {
        appComponent.inject(this)
    }

    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) {
            viewModel.init(scope)
        }
        ContentView(
            title = "Home3",
            surfaceColor = MaterialTheme.colors.background, content = {
                PasscodeScreen(appComponent)
            })
    }
}

