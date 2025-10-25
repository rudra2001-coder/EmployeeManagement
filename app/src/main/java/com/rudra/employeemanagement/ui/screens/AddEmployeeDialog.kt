package com.rudra.employeemanagement.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rudra.employeemanagement.viewmodels.AdminViewModel

@Composable
fun AddEmployeeDialog(
    viewModel: AdminViewModel,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Employee") },
        text = {
            Column {
                if (viewModel.generatedPassword == null) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") }
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") }
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Phone") }
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = department,
                        onValueChange = { department = it },
                        label = { Text("Department") }
                    )
                } else {
                    Text("Employee created successfully!")
                    Spacer(Modifier.height(8.dp))
                    Text("Password: ${viewModel.generatedPassword}")
                }
            }
        },
        confirmButton = {
            if (viewModel.generatedPassword == null) {
                Button(
                    onClick = {
                        viewModel.createEmployee(
                            name = name,
                            email = email,
                            phone = phone,
                            department = department
                        )
                    }
                ) {
                    Text("Generate Credentials")
                }
            } else {
                Button(
                    onClick = {
                        sendEmail(context, email, viewModel.generatedPassword!!)
                        viewModel.clearGeneratedPassword()
                        onDismiss()
                    }
                ) {
                    Text("Send Email & Close")
                }
            }
        },
        dismissButton = {
            Button(onClick = {
                viewModel.clearGeneratedPassword()
                onDismiss()
            }) {
                Text("Cancel")
            }
        }
    )
}

private fun sendEmail(context: Context, email: String, password: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "message/rfc822"
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        putExtra(Intent.EXTRA_SUBJECT, "Your New Employee Credentials")
        putExtra(Intent.EXTRA_TEXT, "Welcome to the team! Your password is: $password")
    }
    context.startActivity(Intent.createChooser(intent, "Send Email"))
}
