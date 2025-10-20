package com.rudra.employeemanagement.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.employeemanagement.data.model.Attendance
import com.rudra.employeemanagement.data.repository.AttendanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val repository: AttendanceRepository
) : ViewModel() {

    private val _attendanceList = MutableStateFlow<List<Attendance>>(emptyList())
    val attendanceList: StateFlow<List<Attendance>> = _attendanceList

    init {
        getAttendance()
    }

    private fun getAttendance() {
        viewModelScope.launch {
            repository.getAttendance().collect {
                _attendanceList.value = it
            }
        }
    }

    fun addAttendance(attendance: Attendance) {
        viewModelScope.launch {
            repository.addAttendance(attendance)
            getAttendance() // Refresh the list
        }
    }
}