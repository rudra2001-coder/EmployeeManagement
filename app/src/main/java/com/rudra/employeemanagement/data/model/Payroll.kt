package com.rudra.employeemanagement.data.model

data class Payroll(
    val id: String = "",
    val employeeId: String = "",
    val month: String = "",
    val basicSalary: Double = 0.0,
    val deductions: Double = 0.0,
    val bonus: Double = 0.0,
    val netPay: Double = 0.0
)