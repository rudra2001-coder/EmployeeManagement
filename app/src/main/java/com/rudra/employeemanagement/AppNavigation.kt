package com.rudra.employeemanagement

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rudra.employeemanagement.ui.screens.AdminDashboardScreen
import com.rudra.employeemanagement.ui.screens.AdminLoginScreen
import com.rudra.employeemanagement.ui.screens.EmployeeDashboardScreen
import com.rudra.employeemanagement.ui.screens.EmployeeLoginScreen
import com.rudra.employeemanagement.ui.screens.GeneratePayrollScreen
import com.rudra.employeemanagement.ui.screens.LoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("admin_login") { AdminLoginScreen(navController) }
        composable("employee_login") { EmployeeLoginScreen(navController) }
        composable("admin_dashboard") { AdminDashboardScreen(navController) }
        composable("employee_dashboard") { EmployeeDashboardScreen(navController) }
        composable("generate_payroll") { GeneratePayrollScreen(navController) }
    }
}