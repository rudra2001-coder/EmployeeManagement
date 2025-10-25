package com.rudra.employeemanagement.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.employeemanagement.data.models.Employee
import com.rudra.employeemanagement.data.repository.EmployeeRepository
import com.rudra.employeemanagement.utils.IdGenerator
import com.rudra.employeemanagement.utils.PasswordUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val idGenerator: IdGenerator
) : ViewModel() {

    val employees = employeeRepository.listEmployeesLive()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    var generatedPassword by mutableStateOf<String?>(null)
        private set

    fun createEmployee(name: String, email: String, phone: String, department: String) {
        viewModelScope.launch {
            val employeeId = idGenerator.generateNextId()
            val password = PasswordUtils.generatePassword()
            generatedPassword = password
            val hashedPassword = PasswordUtils.hashPassword(password)

            val newEmployee = Employee(
                employeeId = employeeId,
                name = name,
                email = email,
                phone = phone,
                department = department,
                passwordHash = hashedPassword,
                createdBy = "admin"
            )

            employeeRepository.createEmployee(newEmployee)
        }
    }

    fun clearGeneratedPassword() {
        generatedPassword = null
    }

    fun deleteEmployee(employeeId: String) {
        viewModelScope.launch {
            employeeRepository.deleteEmployee(employeeId)
        }
    }
}
