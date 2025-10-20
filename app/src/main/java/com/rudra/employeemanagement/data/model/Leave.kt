package com.rudra.employeemanagement.data.model

data class Leave(
    val id: String = "",
    val employeeId: String = "",
    val leaveType: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val status: String = "",
    val reason: String = ""
)