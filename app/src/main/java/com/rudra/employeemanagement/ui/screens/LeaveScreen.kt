package com.rudra.employeemanagement.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
    val leaveList by viewModel.leaveList.collectAsState()

    LazyColumn {
        items(leaveList) { leave ->
            LeaveListItem(leave = leave, viewModel = viewModel)
        }
    }
}

@Composable
fun LeaveListItem(leave: Leave, viewModel: LeaveViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Employee ID: ${leave.employeeId}")
        Text(text = "Start Date: ${leave.startDate}")
        Text(text = "End Date: ${leave.endDate}")
        Text(text = "Reason: ${leave.reason}")
        Text(text = "Status: ${leave.status}")
        Row {
            Button(onClick = { viewModel.updateLeaveStatus(leave.id, "Approved") }) {
                Text("Approve")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.updateLeaveStatus(leave.id, "Rejected") }) {
                Text("Reject")
            }
        }
    }
}