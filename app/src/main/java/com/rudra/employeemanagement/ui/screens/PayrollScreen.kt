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
import com.rudra.employeemanagement.data.model.Payroll
import com.rudra.employeemanagement.ui.viewmodel.PayrollViewModel

@Composable
fun PayrollScreen(navController: NavController, viewModel: PayrollViewModel = hiltViewModel()) {
    val payrolls by viewModel.payrolls.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("generate_payroll") }) {
                Icon(Icons.Default.Add, contentDescription = "Generate Payroll")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(payrolls) { payroll ->
                PayrollListItem(payroll = payroll)
            }
        }
    }
}

@Composable
fun PayrollListItem(payroll: Payroll) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Employee ID: ${payroll.employeeId}")
        Text(text = "Month: ${payroll.month}")
        Text(text = "Net Pay: ${payroll.netPay}")
    }
}