package com.rudra.employeemanagement.data.repository

import com.rudra.employeemanagement.data.model.Attendance
import kotlinx.coroutines.flow.Flow

interface AttendanceRepository {

    suspend fun getAttendance(): Flow<List<Attendance>>

    suspend fun addAttendance(attendance: Attendance)
}