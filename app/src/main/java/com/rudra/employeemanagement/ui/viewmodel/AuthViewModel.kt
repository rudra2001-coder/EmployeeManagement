package com.rudra.employeemanagement.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {

    private val _loginState = MutableStateFlow<Result<AuthResult>?> (null)
    val loginState: StateFlow<Result<AuthResult>?> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
                _loginState.value = Result.success(result)
            } catch (e: Exception) {
                _loginState.value = Result.failure(e)
            }
        }
    }
}