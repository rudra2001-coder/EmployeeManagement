package com.rudra.employeemanagement.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.employeemanagement.data.model.Employee
import com.rudra.employeemanagement.data.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {

    private val _employees = MutableStateFlow<List<Employee>>(emptyList())
    val employees: StateFlow<List<Employee>> = _employees

    init {
        getEmployees()
    }

    private fun getEmployees() {
        viewModelScope.launch {
            repository.getEmployees().collect {
                _employees.value = it
            }
        }
    }

    fun addEmployee(employee: Employee) {
        viewModelScope.launch {
            repository.addEmployee(employee)
            getEmployees() // Refresh the list
        }
    }

    fun updateEmployee(employee: Employee) {
        viewModelScope.launch {
            repository.updateEmployee(employee)
            getEmployees() // Refresh the list
        }
    }

    fun deleteEmployee(employeeId: String) {
        viewModelScope.launch {
            repository.deleteEmployee(employeeId)
            getEmployees() // Refresh the list
        }
    }
}