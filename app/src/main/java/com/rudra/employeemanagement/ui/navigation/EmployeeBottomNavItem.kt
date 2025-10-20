package com.rudra.employeemanagement.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.TimeToLeave
import androidx.compose.ui.graphics.vector.ImageVector

sealed class EmployeeBottomNavItem(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    object Dashboard : EmployeeBottomNavItem("employee_dashboard_home", Icons.Filled.Dashboard, "Dashboard")
    object Attendance : EmployeeBottomNavItem("my_attendance", Icons.Filled.Event, "Attendance")
    object Leave : EmployeeBottomNavItem("apply_leave", Icons.Filled.TimeToLeave, "Leave")
    object Salary : EmployeeBottomNavItem("my_salary", Icons.Filled.Payment, "Salary")
    object Profile : EmployeeBottomNavItem("my_profile", Icons.Filled.Person, "Profile")
}