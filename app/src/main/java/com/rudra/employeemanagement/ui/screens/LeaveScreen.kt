package com.rudra.employeemanagement.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rudra.employeemanagement.data.model.Leave
import com.rudra.employeemanagement.ui.viewmodel.LeaveViewModel

@Composable
fun LeaveScreen(navController: NavController, viewModel: LeaveViewModel = hiltViewModel()) {
    val leaveRequests by viewModel.leaveRequests.collectAsState()

    LazyColumn {
        items(leaveRequests) { leave ->
            LeaveListItem(leave = leave, viewModel = viewModel)
        }
    }
}

@Composable
fun LeaveListItem(leave: Leave, viewModel: LeaveViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Employee ID: ${leave.employeeId}")
        Text(text = "Type: ${leave.leaveType}")
        Text(text = "From: ${leave.startDate} to ${leave.endDate}")
        Text(text = "Status: ${leave.status}")
        Row {
            Button(onClick = { viewModel.approveLeaveRequest(leave.id) }) {
                Text("Approve")
            }
            Button(onClick = { viewModel.rejectLeaveRequest(leave.id) }) {
                Text("Reject")
            }
        }
    }
}