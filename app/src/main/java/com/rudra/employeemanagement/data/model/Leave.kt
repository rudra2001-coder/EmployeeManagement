package com.rudra.employeemanagement.data.model

data class Leave(
    val id: String = "",
    val employeeId: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val reason: String = "",
    val status: String = ""
)