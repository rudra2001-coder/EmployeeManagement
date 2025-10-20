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
import com.rudra.employeemanagement.data.model.Payroll
import com.rudra.employeemanagement.ui.viewmodel.PayrollViewModel

@Composable
fun GeneratePayrollScreen(navController: NavController, viewModel: PayrollViewModel = hiltViewModel()) {
    val employeeId = remember { mutableStateOf("") }
    val month = remember { mutableStateOf("") }
    val basicSalary = remember { mutableStateOf("") }
    val deductions = remember { mutableStateOf("") }
    val bonus = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = employeeId.value, onValueChange = { employeeId.value = it }, label = { Text("Employee ID") })
        OutlinedTextField(value = month.value, onValueChange = { month.value = it }, label = { Text("Month") })
        OutlinedTextField(value = basicSalary.value, onValueChange = { basicSalary.value = it }, label = { Text("Basic Salary") })
        OutlinedTextField(value = deductions.value, onValueChange = { deductions.value = it }, label = { Text("Deductions") })
        OutlinedTextField(value = bonus.value, onValueChange = { bonus.value = it }, label = { Text("Bonus") })
        Button(onClick = {
            val payroll = Payroll(
                employeeId = employeeId.value,
                month = month.value,
                basicSalary = basicSalary.value.toDouble(),
                deductions = deductions.value.toDouble(),
                bonus = bonus.value.toDouble(),
                netPay = basicSalary.value.toDouble() - deductions.value.toDouble() + bonus.value.toDouble()
            )
            viewModel.generatePayroll(payroll)
            navController.popBackStack()
        }) {
            Text("Generate Payroll")
        }
    }
}