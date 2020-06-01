package com.realnigma.testtask.dagger

import com.realnigma.testtask.EmployeeRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [EmployeeAPIModule::class])
interface EmployeeAPIComponent {

    fun inject(employeeRepository: EmployeeRepository)
}