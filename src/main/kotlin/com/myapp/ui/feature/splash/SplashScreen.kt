package com.myapp.ui.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.myapp.ui.component.BaseScreen
import com.myapp.util.rememberBitmapResource
import org.koin.compose.koinInject

class SplashScreen(
    private val onSplashFinished: (navigator: Navigator) -> Unit,
) : BaseScreen<SplashViewModel>() {

    @Composable
    override fun getViewModel(): SplashViewModel = koinInject()

    @Composable
    override fun contentView() {
        val isSplashFinished = viewModel.isSplashFinished.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(isSplashFinished.value, navigator) {
            if (isSplashFinished.value) {
                onSplashFinished(navigator)
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberBitmapResource("drawables/logo.png"),
                modifier = Modifier.size(100.dp),
                contentDescription = "Logo"
            )
        }

    }


}