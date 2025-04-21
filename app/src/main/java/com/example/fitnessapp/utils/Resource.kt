package com.example.fitnessapp.utils

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    class Loading<T> : Resource<T>()
    data class Error<T>(val message: String, val exception: Throwable? = null, val data: T? = null) : Resource<T>()
}
