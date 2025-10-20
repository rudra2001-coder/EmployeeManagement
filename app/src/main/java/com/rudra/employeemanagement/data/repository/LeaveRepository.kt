package com.rudra.employeemanagement.data.repository

import com.rudra.employeemanagement.data.model.Leave
import kotlinx.coroutines.flow.Flow

interface LeaveRepository {

    suspend fun getLeaveRequests(): Flow<List<Leave>>

    suspend fun addLeaveRequest(leave: Leave)

    suspend fun approveLeaveRequest(leaveId: String)

    suspend fun rejectLeaveRequest(leaveId: String)
}