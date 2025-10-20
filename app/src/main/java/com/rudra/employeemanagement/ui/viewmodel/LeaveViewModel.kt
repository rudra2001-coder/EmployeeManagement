package com.rudra.employeemanagement.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.employeemanagement.data.model.Leave
import com.rudra.employeemanagement.data.repository.LeaveRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaveViewModel @Inject constructor(
    private val repository: LeaveRepository
) : ViewModel() {

    private val _leaveRequests = MutableStateFlow<List<Leave>>(emptyList())
    val leaveRequests: StateFlow<List<Leave>> = _leaveRequests

    init {
        getLeaveRequests()
    }

    private fun getLeaveRequests() {
        viewModelScope.launch {
            repository.getLeaveRequests().collect {
                _leaveRequests.value = it
            }
        }
    }

    fun approveLeaveRequest(leaveId: String) {
        viewModelScope.launch {
            repository.approveLeaveRequest(leaveId)
            getLeaveRequests() // Refresh the list
        }
    }

    fun rejectLeaveRequest(leaveId: String) {
        viewModelScope.launch {
            repository.rejectLeaveRequest(leaveId)
            getLeaveRequests() // Refresh the list
        }
    }
}