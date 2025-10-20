package com.rudra.employeemanagement.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.rudra.employeemanagement.data.model.Leave
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class LeaveRepositoryImpl : LeaveRepository {

    private val db = FirebaseFirestore.getInstance()
    private val leaveCollection = db.collection("leaves")

    override suspend fun addLeaveRequest(leave: Leave) {
        leaveCollection.add(leave).await()
    }

    override suspend fun getLeaveRequests(): Flow<List<Leave>> = flow {
        val snapshot = leaveCollection.get().await()
        val leaveList = snapshot.toObjects(Leave::class.java)
        emit(leaveList)
    }

    override suspend fun approveLeaveRequest(leaveId: String) {
        leaveCollection.document(leaveId).update("status", "Approved").await()
    }

    override suspend fun rejectLeaveRequest(leaveId: String) {
        leaveCollection.document(leaveId).update("status", "Rejected").await()
    }
}