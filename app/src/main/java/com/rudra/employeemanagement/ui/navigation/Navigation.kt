package com.rudra.employeemanagement.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rudra.employeemanagement.ui.screens.AdminLoginScreen
import com.rudra.employeemanagement.ui.screens.EmployeeLoginScreen
import com.rudra.employeemanagement.ui.screens.LoginScreen
import com.rudra.employeemanagement.ui.screens.AdminDashboardScreen
import com.rudra.employeemanagement.ui.screens.EmployeeDashboardScreen
import com.rudra.employeemanagement.ui.screens.EmployeeListScreen
import com.rudra.employeemanagement.ui.screens.AddEmployeeScreen
import com.rudra.employeemanagement.ui.screens.PayrollScreen
import com.rudra.employeemanagement.ui.screens.GeneratePayrollScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("admin_login") {
            AdminLoginScreen(navController = navController)
        }
        composable("employee_login") {
            EmployeeLoginScreen(navController = navController)
        }
        composable("admin_dashboard") {
            AdminDashboardScreen(navController = navController)
        }
        composable("employee_dashboard") {
            EmployeeDashboardScreen(navController = navController)
        }
        composable("employee_list") {
            EmployeeListScreen(navController = navController)
        }
        composable("add_employee") {
            AddEmployeeScreen(navController = navController)
        }
        composable("payroll") {
            PayrollScreen(navController = navController)
        }
        composable("generate_payroll") {
            GeneratePayrollScreen(navController = navController)
        }
    }
}
