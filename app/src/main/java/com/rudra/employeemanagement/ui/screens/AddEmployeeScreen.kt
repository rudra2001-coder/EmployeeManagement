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
import com.rudra.employeemanagement.data.model.Employee
import com.rudra.employeemanagement.ui.viewmodel.EmployeeViewModel

@Composable
fun AddEmployeeScreen(navController: NavController, viewModel: EmployeeViewModel = hiltViewModel()) {
    val name = remember { mutableStateOf("") }
    val position = remember { mutableStateOf("") }
    val department = remember { mutableStateOf("") }
    val salary = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = name.value, onValueChange = { name.value = it }, label = { Text("Name") })
        OutlinedTextField(value = position.value, onValueChange = { position.value = it }, label = { Text("Position") })
        OutlinedTextField(value = department.value, onValueChange = { department.value = it }, label = { Text("Department") })
        OutlinedTextField(value = salary.value, onValueChange = { salary.value = it }, label = { Text("Salary") })
        Button(onClick = {
            val employee = Employee(
                name = name.value,
                position = position.value,
                department = department.value,
                salary = salary.value.toDouble()
            )
            viewModel.addEmployee(employee)
            navController.popBackStack()
        }) {
            Text("Add Employee")
        }
    }
}