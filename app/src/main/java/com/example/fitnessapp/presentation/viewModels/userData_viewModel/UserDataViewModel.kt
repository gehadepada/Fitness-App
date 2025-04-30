package com.example.fitnessapp.presentation.viewModels.userData_viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.domain.repo.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _userDataState = MutableStateFlow<UserDataState>(UserDataState.Idle)
    val userDataState = _userDataState.asStateFlow()

    fun saveDataToFirestore(
        data: Map<String, Any>
    ) {
        _userDataState.value = UserDataState.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                firebaseRepository.saveUserData(data)
                _userDataState.value = UserDataState.Success
                Log.d("Al-qiran", "Data saved Successfully from viewModel")
            }
        } catch (e: Exception) {
            Log.d("Al-qiran", "Failed from viewModel")
            _userDataState.value = UserDataState.Error(e.message.toString())
        }
    }

    fun resetUserDataState() {
        _userDataState.value = UserDataState.Idle
    }

}