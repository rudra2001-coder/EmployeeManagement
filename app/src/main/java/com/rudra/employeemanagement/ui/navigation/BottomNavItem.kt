package com.rudra.employeemanagement.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.TimeToLeave
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    object Dashboard : BottomNavItem("admin_dashboard_home", Icons.Filled.Dashboard, "Dashboard")
    object Employees : BottomNavItem("employee_list", Icons.Filled.People, "Employees")
    object Attendance : BottomNavItem("attendance", Icons.Filled.Event, "Attendance")
    object Shifts : BottomNavItem("shifts", Icons.Filled.Schedule, "Shifts")
    object Payroll : BottomNavItem("payroll", Icons.Filled.Payment, "Payroll")
    object Reports : BottomNavItem("reports", Icons.Filled.Assessment, "Reports")
    object Leave : BottomNavItem("leave", Icons.Filled.TimeToLeave, "Leave")
}