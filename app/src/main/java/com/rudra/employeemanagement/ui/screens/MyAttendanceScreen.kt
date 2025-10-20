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
import com.google.firebase.auth.FirebaseAuth
import com.rudra.employeemanagement.data.model.Attendance
import com.rudra.employeemanagement.ui.viewmodel.AttendanceViewModel

@Composable
fun MyAttendanceScreen(navController: NavController, viewModel: AttendanceViewModel = hiltViewModel()) {
    val attendanceList by viewModel.attendanceList.collectAsState()
    val currentUser = FirebaseAuth.getInstance().currentUser

    val myAttendance = attendanceList.filter { it.employeeId == currentUser?.uid }

    LazyColumn {
        items(myAttendance) { attendance ->
            MyAttendanceListItem(attendance = attendance)
        }
    }
}

@Composable
fun MyAttendanceListItem(attendance: Attendance) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Date: ${attendance.date}")
        Text(text = "Check-in: ${attendance.checkIn}")
        Text(text = "Check-out: ${attendance.checkOut}")
    }
}