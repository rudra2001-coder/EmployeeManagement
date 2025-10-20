package com.rudra.employeemanagement.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.employeemanagement.data.model.Payroll
import com.rudra.employeemanagement.data.repository.PayrollRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PayrollViewModel @Inject constructor(
    private val repository: PayrollRepository
) : ViewModel() {

    private val _payrolls = MutableStateFlow<List<Payroll>>(emptyList())
    val payrolls: StateFlow<List<Payroll>> = _payrolls

    init {
        getPayrolls()
    }

    private fun getPayrolls() {
        viewModelScope.launch {
            repository.getPayrolls().collect {
                _payrolls.value = it
            }
        }
    }

    fun generatePayroll(payroll: Payroll) {
        viewModelScope.launch {
            repository.generatePayroll(payroll)
            getPayrolls() // Refresh the list
        }
    }
}