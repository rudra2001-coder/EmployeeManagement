package com.rudra.employeemanagement.di

import com.rudra.employeemanagement.data.repository.AttendanceRepository
import com.rudra.employeemanagement.data.repository.AttendanceRepositoryImpl
import com.rudra.employeemanagement.data.repository.EmployeeRepository
import com.rudra.employeemanagement.data.repository.EmployeeRepositoryImpl
import com.rudra.employeemanagement.data.repository.LeaveRepository
import com.rudra.employeemanagement.data.repository.LeaveRepositoryImpl
import com.rudra.employeemanagement.data.repository.PayrollRepository
import com.rudra.employeemanagement.data.repository.PayrollRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEmployeeRepository(): EmployeeRepository {
        return EmployeeRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideAttendanceRepository(): AttendanceRepository {
        return AttendanceRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideLeaveRepository(): LeaveRepository {
        return LeaveRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providePayrollRepository(): PayrollRepository {
        return PayrollRepositoryImpl()
    }
}