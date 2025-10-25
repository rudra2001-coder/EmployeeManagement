package com.rudra.employeemanagement.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object AdminDashboard : Screen("admin_dashboard")
    object EmployeeDashboard : Screen("employee_dashboard/{employeeId}") {
        fun createRoute(employeeId: String) = "employee_dashboard/$employeeId"
    }
    object GeneratePayroll : Screen("generate_payroll")
    object EmployeeDetail : Screen("employee_detail/{employeeId}") {
        fun createRoute(employeeId: String) = "employee_detail/$employeeId"
    }
}
