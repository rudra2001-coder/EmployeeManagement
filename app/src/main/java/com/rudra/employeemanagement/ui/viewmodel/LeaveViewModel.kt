package com.rudra.employeemanagement.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.rudra.employeemanagement.data.model.Leave
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaveViewModel @Inject constructor() : ViewModel() {

    private val _leaveList = MutableStateFlow<List<Leave>>(emptyList())
    val leaveList: StateFlow<List<Leave>> = _leaveList

    private val firestore = FirebaseFirestore.getInstance()

    init {
        getLeaveApplications()
    }

    private fun getLeaveApplications() {
        viewModelScope.launch {
            firestore.collection("leave").addSnapshotListener { snapshot, _ ->
                snapshot?.let {
                    _leaveList.value = it.toObjects(Leave::class.java)
                }
            }
        }
    }

    fun updateLeaveStatus(leaveId: String, status: String) {
        viewModelScope.launch {
            firestore.collection("leave").document(leaveId).update("status", status)
        }
    }

    fun applyForLeave(employeeId: String, startDate: String, endDate: String, reason: String) {
        viewModelScope.launch {
            val leave = Leave(
                id = firestore.collection("leave").document().id,
                employeeId = employeeId,
                startDate = startDate,
                endDate = endDate,
                reason = reason,
                status = "Pending"
            )
            firestore.collection("leave").document(leave.id).set(leave)
        }
    }
}