package com.rudra.employeemanagement

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rudra.employeemanagement.ui.navigation.Screen
import com.rudra.employeemanagement.ui.screens.AdminDashboardScreen
import com.rudra.employeemanagement.ui.screens.EmployeeDashboardScreen
import com.rudra.employeemanagement.ui.screens.EmployeeDetailScreen
import com.rudra.employeemanagement.ui.screens.GeneratePayrollScreen
import com.rudra.employeemanagement.ui.screens.LoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.AdminDashboard.route) { AdminDashboardScreen(navController) }
        composable(
            route = Screen.EmployeeDashboard.route,
            arguments = listOf(navArgument("employeeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val employeeId = backStackEntry.arguments?.getString("employeeId")
            EmployeeDashboardScreen(navController, employeeId)
        }
        composable(Screen.GeneratePayroll.route) { GeneratePayrollScreen(navController) }
        composable(
            route = Screen.EmployeeDetail.route,
            arguments = listOf(navArgument("employeeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val employeeId = backStackEntry.arguments?.getString("employeeId")
            EmployeeDetailScreen(navController, employeeId)
        }
    }
}
