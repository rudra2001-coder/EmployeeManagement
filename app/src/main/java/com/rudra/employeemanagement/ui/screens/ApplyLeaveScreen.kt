package com.rudra.employeemanagement.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.rudra.employeemanagement.data.model.Leave
import com.rudra.employeemanagement.ui.viewmodel.LeaveViewModel

@Composable
fun ApplyLeaveScreen(navController: NavController, viewModel: LeaveViewModel = hiltViewModel()) {
    val leaveType = remember { mutableStateOf("") }
    val startDate = remember { mutableStateOf("") }
    val endDate = remember { mutableStateOf("") }
    val reason = remember { mutableStateOf("") }
    val currentUser = FirebaseAuth.getInstance().currentUser

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = leaveType.value, onValueChange = { leaveType.value = it }, label = { Text("Leave Type") })
        OutlinedTextField(value = startDate.value, onValueChange = { startDate.value = it }, label = { Text("Start Date") })
        OutlinedTextField(value = endDate.value, onValueChange = { endDate.value = it }, label = { Text("End Date") })
        OutlinedTextField(value = reason.value, onValueChange = { reason.value = it }, label = { Text("Reason") })
        Button(onClick = {
            val leave = Leave(
                employeeId = currentUser?.uid ?: "",
                leaveType = leaveType.value,
                startDate = startDate.value,
                endDate = endDate.value,
                reason = reason.value,
                status = "Pending"
            )
            // TODO: Create addLeaveRequest function in LeaveViewModel
            // viewModel.addLeaveRequest(leave)
            navController.popBackStack()
        }) {
            Text("Apply")
        }
    }
}