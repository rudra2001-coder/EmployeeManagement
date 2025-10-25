package com.rudra.employeemanagement.viewmodels

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.employeemanagement.data.models.Employee
import com.rudra.employeemanagement.data.repository.EmployeeRepository
import com.rudra.employeemanagement.data.repository.StorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeDetailViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val storageRepository: StorageRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val employeeId: String = savedStateHandle.get<String>("employeeId")!!

    private val _employee = MutableStateFlow<Employee?>(null)
    val employee = _employee.asStateFlow()

    init {
        viewModelScope.launch {
            _employee.value = employeeRepository.getEmployee(employeeId)
        }
    }

    fun updateEmployee(name: String, email: String, phone: String, department: String) {
        viewModelScope.launch {
            val currentEmployee = _employee.value ?: return@launch
            val updatedEmployee = currentEmployee.copy(
                name = name,
                email = email,
                phone = phone,
                department = department
            )
            employeeRepository.createEmployee(updatedEmployee) // createEmployee also handles updates
        }
    }

    fun uploadProfilePhoto(imageUri: Uri) {
        viewModelScope.launch {
            val profileUrl = storageRepository.uploadProfilePhoto(imageUri)
            if (profileUrl != null) {
                val currentEmployee = _employee.value ?: return@launch
                val updatedEmployee = currentEmployee.copy(profileUrl = profileUrl)
                employeeRepository.createEmployee(updatedEmployee)
                _employee.value = updatedEmployee // Update local state immediately
            }
        }
    }
}
