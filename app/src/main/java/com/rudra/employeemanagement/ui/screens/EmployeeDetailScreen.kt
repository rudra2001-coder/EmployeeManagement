package com.rudra.employeemanagement.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.rudra.employeemanagement.viewmodels.EmployeeDetailViewModel

@Composable
fun EmployeeDetailScreen(
    navController: NavController,
    viewModel: EmployeeDetailViewModel = hiltViewModel()
) {
    val employee by viewModel.employee.collectAsState()

    var name by remember(employee) { mutableStateOf(employee?.name ?: "") }
    var email by remember(employee) { mutableStateOf(employee?.email ?: "") }
    var phone by remember(employee) { mutableStateOf(employee?.phone ?: "") }
    var department by remember(employee) { mutableStateOf(employee?.department ?: "") }

    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { viewModel.uploadProfilePhoto(it) }
    }

    Scaffold {
        Column(
            modifier = Modifier.padding(it).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (employee != null) {
                Image(
                    painter = rememberAsyncImagePainter(employee?.profileUrl),
                    contentDescription = "Profile Photo",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
                Spacer(Modifier.height(16.dp))
                Button(onClick = { imagePickerLauncher.launch("image/*") }) {
                    Text("Change Photo")
                }
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = department,
                    onValueChange = { department = it },
                    label = { Text("Department") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = { 
                        viewModel.updateEmployee(name, email, phone, department) 
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Changes")
                }
            } else {
                Text("Loading employee details...")
            }
        }
    }
}
