package com.example.fitnessapp.presentation.screens.user_data_package.viewModel

sealed class UserDataState() {
    data object Idle: UserDataState()
    data object Loading: UserDataState()
    data object Success: UserDataState()
    data class Error(val error: String): UserDataState()

}