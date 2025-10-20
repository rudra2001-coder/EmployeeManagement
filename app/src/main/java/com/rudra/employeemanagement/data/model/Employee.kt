package com.rudra.employeemanagement.data.model

data class Employee(
    val id: String = "",
    val name: String = "",
    val position: String = "",
    val department: String = "",
    val salary: Double = 0.0,
    val joinDate: String = "",
    val photoUrl: String = ""
)