package com.myapp.ui.feature.homescreen

import com.myapp.data.repo.MyRepo
import com.myapp.util.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class HomeScreenViewModel @Inject constructor(
    private val myRepo: MyRepo,
) : ViewModel() {
    companion object {
        const val INIT_WELCOME_MSG = "Hello World!"
    }

    private val _welcomeText = MutableStateFlow(INIT_WELCOME_MSG)
    val welcomeText: StateFlow<String> = _welcomeText

    fun onClickMeClicked() {
        _welcomeText.value = myRepo.getClickedWelcomeText()
    }
}