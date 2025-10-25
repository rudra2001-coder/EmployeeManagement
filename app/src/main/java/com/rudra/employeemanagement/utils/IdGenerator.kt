package com.rudra.employeemanagement.utils

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class IdGenerator(private val firestore: FirebaseFirestore) {

    private val counterDoc = firestore.collection("meta").document("employeeCounter")

    suspend fun generateNextId(): String = withContext(Dispatchers.IO) {
        val newId = firestore.runTransaction { tx ->
            val snapshot = tx.get(counterDoc)
            val current = snapshot.getLong("count") ?: 0L
            val next = current + 1L
            tx.set(counterDoc, mapOf("count" to next), SetOptions.merge())
            next
        }.await()
        val formatted = String.format("EMP-%04d", newId)
        formatted
    }
}
