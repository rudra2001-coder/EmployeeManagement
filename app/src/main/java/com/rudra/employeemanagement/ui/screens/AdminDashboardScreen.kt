package com.rudra.employeemanagement.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rudra.employeemanagement.ui.navigation.BottomNavItem

@Composable
fun AdminDashboardScreen(navController: NavController) {
    val bottomNavItems = listOf(
        BottomNavItem.Dashboard,
        BottomNavItem.Employees,
        BottomNavItem.Attendance,
        BottomNavItem.Shifts,
        BottomNavItem.Payroll,
        BottomNavItem.Reports,
        BottomNavItem.Leave
    )
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = { bottomNavController.navigate(item.route) },
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Dashboard.route) { AdminDashboardHomeScreen(bottomNavController) }
            composable(BottomNavItem.Employees.route) { EmployeeListScreen(navController) }
            composable(BottomNavItem.Attendance.route) { AttendanceScreen(navController) }
            composable(BottomNavItem.Shifts.route) { ShiftsScreen() }
            composable(BottomNavItem.Payroll.route) { PayrollScreen(navController) }
            composable(BottomNavItem.Reports.route) { ReportsScreen() }
            composable(BottomNavItem.Leave.route) { LeaveScreen(navController) }
        }
    }
}