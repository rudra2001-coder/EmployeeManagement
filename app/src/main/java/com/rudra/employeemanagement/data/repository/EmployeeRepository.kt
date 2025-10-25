package com.rudra.employeemanagement.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.rudra.employeemanagement.data.models.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val employees = firestore.collection("employees")

    suspend fun createEmployee(employee: Employee) = withContext(Dispatchers.IO) {
        employees.document(employee.employeeId).set(employee).await()
    }

    suspend fun getEmployee(employeeId: String): Employee? = withContext(Dispatchers.IO) {
        val snap = employees.document(employeeId).get().await()
        if (snap.exists()) snap.toObject(Employee::class.java) else null
    }

    fun listEmployeesLive(): Flow<List<Employee>> = callbackFlow {
        val listener = employees.orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snap, _ ->
                val list = snap?.documents?.mapNotNull { it.toObject(Employee::class.java) } ?: emptyList()
                trySend(list)
            }
        awaitClose { listener.remove() }
    }

    suspend fun deleteEmployee(employeeId: String) = withContext(Dispatchers.IO) {
        employees.document(employeeId).delete().await()
    }
}
