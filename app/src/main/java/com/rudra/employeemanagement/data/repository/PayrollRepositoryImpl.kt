package com.rudra.employeemanagement.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.rudra.employeemanagement.data.model.Payroll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class PayrollRepositoryImpl : PayrollRepository {

    private val db = FirebaseFirestore.getInstance()
    private val payrollCollection = db.collection("payroll")

    override suspend fun getPayrolls(): Flow<List<Payroll>> = flow {
        val snapshot = payrollCollection.get().await()
        val payrollList = snapshot.toObjects(Payroll::class.java)
        emit(payrollList)
    }

    override suspend fun generatePayroll(payroll: Payroll) {
        payrollCollection.add(payroll).await()
    }
}