package com.rudra.employeemanagement.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.rudra.employeemanagement.data.model.Salary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SalaryViewModel @Inject constructor() : ViewModel() {

    private val _salaryList = MutableStateFlow<List<Salary>>(emptyList())
    val salaryList: StateFlow<List<Salary>> = _salaryList

    private val firestore = FirebaseFirestore.getInstance()

    fun getSalaryForEmployee(employeeId: String) {
        viewModelScope.launch {
            firestore.collection("salary").whereEqualTo("employeeId", employeeId)
                .addSnapshotListener { snapshot, _ ->
                    snapshot?.let {
                        _salaryList.value = it.toObjects(Salary::class.java)
                    }
                }
        }
    }
}