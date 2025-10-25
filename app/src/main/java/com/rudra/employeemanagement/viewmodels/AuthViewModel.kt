package com.rudra.employeemanagement.viewmodels

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.rudra.employeemanagement.R
import com.rudra.employeemanagement.data.repository.AuthRepository
import com.rudra.employeemanagement.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val errorMessage: String? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val savedState: SavedStateHandle
): ViewModel() {
    var employeeId by mutableStateOf("")
    var employeePassword by mutableStateOf("")

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }

    fun onGoogleSignInResult(idToken: String, navController: NavHostController) {
        viewModelScope.launch {
            val result = authRepository.signInWithGoogle(idToken)
            result.onSuccess {
                navController.navigate(Screen.AdminDashboard.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }.onFailure { e ->
                _uiState.update { it.copy(errorMessage = e.localizedMessage ?: "Sign in failed") }
            }
        }
    }

    fun employeeLogin(navController: NavHostController) {
        viewModelScope.launch {
            // query employee doc; verify bcrypt
            // on success navigate to employee dashboard route: Screen.EmployeeDashboard.createRoute(employeeId)
        }
    }
}
