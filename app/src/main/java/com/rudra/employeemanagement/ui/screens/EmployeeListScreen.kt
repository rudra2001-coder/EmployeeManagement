package com.rudra.employeemanagement.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rudra.employeemanagement.data.model.Employee
import com.rudra.employeemanagement.ui.viewmodel.EmployeeViewModel

@Composable
fun EmployeeListScreen(navController: NavController, viewModel: EmployeeViewModel = hiltViewModel()) {
    val employees by viewModel.employees.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add_employee") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Employee")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(employees) { employee ->
                EmployeeListItem(employee = employee)
            }
        }
    }
}

@Composable
fun EmployeeListItem(employee: Employee) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = employee.name)
        Text(text = employee.position)
    }
}