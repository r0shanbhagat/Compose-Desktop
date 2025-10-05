package com.myapp.ui.feature.homescreen

import com.myapp.util.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeScreenViewModel : ViewModel() {
    companion object {
        const val INIT_WELCOME_MSG = "Hello World!"
    }

    private val _welcomeText = MutableStateFlow(INIT_WELCOME_MSG)
    val welcomeText: StateFlow<String> = _welcomeText

    fun onClickMeClicked() {

    }
}