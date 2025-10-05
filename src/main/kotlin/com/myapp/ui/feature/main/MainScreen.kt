package com.myapp.ui.feature.main

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.myapp.ui.component.BaseScreen
import com.myapp.ui.component.ContentToolbarView
import org.koin.compose.koinInject

class MainScreen : BaseScreen<MainViewModel>() {

    @Composable
    override fun getViewModel(): MainViewModel = koinInject()

    @Composable
    override fun contentView() {
        ContentToolbarView(
            title = "Home3",
            surfaceColor = MaterialTheme.colors.background,
            content = {
                // ProductListScreen()
            })
    }
}
