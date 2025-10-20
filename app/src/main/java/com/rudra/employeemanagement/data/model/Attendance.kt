package com.rudra.employeemanagement.data.model

data class Attendance(
    val id: String = "",
    val employeeId: String = "",
    val date: String = "",
    val checkIn: String = "",
    val checkOut: String = ""
)