package com.rudra.employeemanagement.data.repository

import com.rudra.employeemanagement.data.model.Employee
import kotlinx.coroutines.flow.Flow

interface EmployeeRepository {

    suspend fun getEmployees(): Flow<List<Employee>>

    suspend fun addEmployee(employee: Employee)

    suspend fun updateEmployee(employee: Employee)

    suspend fun deleteEmployee(employeeId: String)
}