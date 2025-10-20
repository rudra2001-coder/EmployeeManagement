package com.rudra.employeemanagement.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rudra.employeemanagement.data.model.Attendance
import com.rudra.employeemanagement.ui.viewmodel.AttendanceViewModel

@Composable
fun AttendanceScreen(navController: NavController, viewModel: AttendanceViewModel = hiltViewModel()) {
    val attendanceList by viewModel.attendanceList.collectAsState()

    LazyColumn {
        items(attendanceList) { attendance ->
            AttendanceListItem(attendance = attendance)
        }
    }
}

@Composable
fun AttendanceListItem(attendance: Attendance) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Employee ID: ${attendance.employeeId}")
        Text(text = "Date: ${attendance.date}")
        Text(text = "Check-in: ${attendance.checkIn}")
        Text(text = "Check-out: ${attendance.checkOut}")
    }
}