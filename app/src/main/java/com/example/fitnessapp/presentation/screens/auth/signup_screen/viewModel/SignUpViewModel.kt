package com.example.fitnessapp.presentation.screens.auth.signup_screen.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.domain.repo.FirebaseRepository
import com.example.fitnessapp.presentation.screens.muscle_screen.viewModel.MuscleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _state = MutableStateFlow<SignUpState>(SignUpState.UnAuthenticated)
    val state = _state.asStateFlow()
    // for invalid inputs
    private val _invalidElements = MutableStateFlow<Map<String, String>>(emptyMap())
    val invalidElements = _invalidElements.asStateFlow()

    // for user info
    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun createUser() {
        val errors = mutableMapOf<String, String>()

        if (!_userName.value.matches("^[a-zA-Z0-9._]{3,20}$".toRegex())) {
            errors["userName"] = "Username cannot be empty"
        }

        if (!_email.value.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}\$".toRegex())) {
            errors["email"] = "Invalid email format"
        }

        if (_password.value.length < 6) {
            errors["password"] = "Password must be at least 6 characters"
        }

        if (errors.isEmpty()) {
            _invalidElements.value = emptyMap()

            viewModelScope.launch {
                _state.value = SignUpState.Loading

                try {
                    firebaseRepository.createUserEmailAndPassword(_email.value, _password.value,)
                    firebaseRepository.saveUserData(mapOf("email" to _email.value, "userName" to _userName.value))

                    _state.value = SignUpState.Authenticated
                    Log.d("Al-qiran", "From block")

                } catch (e: Exception) {
                    _state.value = SignUpState.Error(e.message.toString())
                    if (e is java.net.UnknownHostException ||
                        e is java.net.SocketTimeoutException ||
                        e is java.io.IOException) {
                        _state.value = SignUpState.Error("Network error. Please check your internet connection.")

                    } else {
                        _state.value = SignUpState.Error("Invalid email or password.")
                    }
                }
            }
        } else {
            _invalidElements.value = errors
            _state.value = SignUpState.InvalidInput(errors)
        }
    }

    fun onUserNameChange(userName: String) {
        _userName.value = userName
    }

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

}
