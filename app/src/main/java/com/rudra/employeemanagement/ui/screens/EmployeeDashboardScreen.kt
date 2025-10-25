package com.rudra.employeemanagement.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rudra.employeemanagement.viewmodels.EmployeeDashboardViewModel

@Composable
fun EmployeeDashboardScreen(
    navController: NavController,
    viewModel: EmployeeDashboardViewModel = hiltViewModel()
) {
    Scaffold {
        Column(modifier = Modifier.padding(it).padding(16.dp)) {
            Text("Welcome!")
            // Add other employee features here
        }
    }
}
