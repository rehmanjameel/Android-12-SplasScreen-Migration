package com.example.migratingandroid_12_splashscreenapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {
    private val _isLoaded = MutableStateFlow(true)
    val isLoading = _isLoaded.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            _isLoaded.value = false
        }
    }
}