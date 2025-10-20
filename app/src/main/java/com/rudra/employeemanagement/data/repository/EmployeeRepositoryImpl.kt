package com.rudra.employeemanagement.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.rudra.employeemanagement.data.model.Employee
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class EmployeeRepositoryImpl : EmployeeRepository {

    private val db = FirebaseFirestore.getInstance()
    private val employeeCollection = db.collection("employees")

    override suspend fun getEmployees(): Flow<List<Employee>> = flow {
        val snapshot = employeeCollection.get().await()
        val employees = snapshot.toObjects(Employee::class.java)
        emit(employees)
    }

    override suspend fun addEmployee(employee: Employee) {
        employeeCollection.add(employee).await()
    }

    override suspend fun updateEmployee(employee: Employee) {
        employeeCollection.document(employee.id).set(employee).await()
    }

    override suspend fun deleteEmployee(employeeId: String) {
        employeeCollection.document(employeeId).delete().await()
    }
}