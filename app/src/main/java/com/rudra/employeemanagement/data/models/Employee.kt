package com.rudra.employeemanagement.data.models

import com.google.firebase.Timestamp

data class Employee(
    val employeeId: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val department: String = "",
    val role: String = "employee",
    val passwordHash: String = "",
    val createdBy: String = "",
    val createdAt: Timestamp? = null,
    val profileUrl: String? = null,
    val status: String = "active"
)
