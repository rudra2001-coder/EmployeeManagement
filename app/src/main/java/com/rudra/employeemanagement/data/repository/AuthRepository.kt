package com.rudra.employeemanagement.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser> = withContext(Dispatchers.IO) {
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = auth.signInWithCredential(credential).await()
            val user = authResult.user ?: throw Exception("No user")
            // ensure admins doc exists
            val adminDoc = firestore.collection("admins").document(user.uid)
            adminDoc.set(mapOf("uid" to user.uid, "email" to user.email, "displayName" to user.displayName), SetOptions.merge()).await()
            Result.success(user)
        } catch (e: Exception) { Result.failure(e) }
    }

    fun signOut() {
        auth.signOut()
    }
}
