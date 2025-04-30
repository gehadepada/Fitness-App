package com.example.fitnessapp.presentation.viewModels.userData_viewModel

sealed class UserDataState() {
    data object Idle: UserDataState()
    data object Loading: UserDataState()
    data object Success: UserDataState()
    data class Error(val error: String): UserDataState()

}