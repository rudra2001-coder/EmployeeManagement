package com.rudra.employeemanagement.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.rudra.employeemanagement.data.model.Salary
import com.rudra.employeemanagement.ui.viewmodel.SalaryViewModel

@Composable
fun MySalaryScreen(navController: NavController, viewModel: SalaryViewModel = hiltViewModel()) {
    val salaryList by viewModel.salaryList.collectAsState()
    val currentUser = FirebaseAuth.getInstance().currentUser

    LaunchedEffect(currentUser) {
        currentUser?.uid?.let {
            viewModel.getSalaryForEmployee(it)
        }
    }

    LazyColumn {
        items(salaryList) { salary ->
            MySalaryListItem(salary = salary)
        }
    }
}

@Composable
fun MySalaryListItem(salary: Salary) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Date: ${salary.date}")
        Text(text = "Amount: ${salary.amount}")
    }
}