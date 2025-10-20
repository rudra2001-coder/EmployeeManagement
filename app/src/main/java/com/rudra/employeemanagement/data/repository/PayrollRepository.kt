package com.rudra.employeemanagement.data.repository

import com.rudra.employeemanagement.data.model.Payroll
import kotlinx.coroutines.flow.Flow

interface PayrollRepository {

    suspend fun getPayrolls(): Flow<List<Payroll>>

    suspend fun generatePayroll(payroll: Payroll)
}