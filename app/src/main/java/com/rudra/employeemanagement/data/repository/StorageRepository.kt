package com.rudra.employeemanagement.data.repository

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class StorageRepository @Inject constructor(
    private val storage: FirebaseStorage
) {

    suspend fun uploadProfilePhoto(imageUri: Uri): String? = withContext(Dispatchers.IO) {
        try {
            val storageRef = storage.reference
            val fileRef = storageRef.child("profile_photos/${UUID.randomUUID()}")
            fileRef.putFile(imageUri).await()
            fileRef.downloadUrl.await()?.toString()
        } catch (e: Exception) {
            null
        }
    }
}
