package com.rudra.employeemanagement.viewmodels

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.rudra.employeemanagement.R
import com.rudra.employeemanagement.ui.navigation.Screen

class LoginViewModel : ViewModel() {

    var employeeId by mutableStateOf("")
    var employeePassword by mutableStateOf("")

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }

    fun handleGoogleSignInResult(data: Intent?, navController: NavHostController) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(com.google.android.gms.common.api.ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential).addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    navController.navigate(Screen.AdminDashboard.route)
                }
            }
        } catch (e: Exception) {
            // Handle exception
        }
    }

    fun employeeLogin(navController: NavHostController) {
        firestore.collection("employees")
            .document(employeeId)
            .get()
            .addOnSuccessListener { doc ->
                val password = doc.getString("password")
                if (password == employeePassword) {
                    navController.navigate(Screen.EmployeeDashboard.createRoute(employeeId))
                }
            }
    }
}
