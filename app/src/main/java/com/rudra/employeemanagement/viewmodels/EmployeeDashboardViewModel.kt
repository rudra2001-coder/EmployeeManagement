package com.rudra.employeemanagement.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rudra.employeemanagement.data.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmployeeDashboardViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    // Employee-specific features can be added here
}
