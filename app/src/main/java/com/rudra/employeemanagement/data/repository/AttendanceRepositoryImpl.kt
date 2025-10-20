package com.rudra.employeemanagement.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.rudra.employeemanagement.data.model.Attendance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AttendanceRepositoryImpl : AttendanceRepository {

    private val db = FirebaseFirestore.getInstance()
    private val attendanceCollection = db.collection("attendance")

    override suspend fun getAttendance(): Flow<List<Attendance>> = flow {
        val snapshot = attendanceCollection.get().await()
        val attendanceList = snapshot.toObjects(Attendance::class.java)
        emit(attendanceList)
    }

    override suspend fun addAttendance(attendance: Attendance) {
        attendanceCollection.add(attendance).await()
    }
}