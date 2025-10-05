package com.myapp.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import com.myapp.util.ViewModel

/**
 * @Details :BaseScreen
 * @Author Roshan Bhagat
 */
abstract class BaseScreen<T : ViewModel> : Screen {
    lateinit var viewModel: T

    @Composable
    override fun Content() {
        viewModel = getViewModel()

        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) {
            viewModel.init(scope)
        }
        contentView()
    }


    @Composable
    protected abstract fun getViewModel(): T

    @Composable
    abstract fun contentView()
}