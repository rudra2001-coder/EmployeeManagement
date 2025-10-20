package com.rudra.employeemate.data.repository

import com.rudra.employeemate.data.model.Attendance
import kotlinx.coroutines.flow.Flow

interface AttendanceRepository {

    suspend fun getAttendance(): Flow<List<Attendance>>

    suspend fun addAttendance(attendance: Attendance)
}