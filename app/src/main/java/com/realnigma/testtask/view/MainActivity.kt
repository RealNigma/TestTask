package com.realnigma.testtask.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.realnigma.testtask.viewmodel.EmployeeViewModel
import com.realnigma.testtask.R
import com.realnigma.testtask.view.adapter.SpecialtyAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: EmployeeViewModel
    private var specialtyAdapter =
        SpecialtyAdapter()
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
    }

    private fun initViewModel() {
        Log.w("Employee", "Creating viewmodel...")
        viewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        viewModel.insertEmployeesAndSpecialties()
        viewModel.specialties.observe(this, Observer { specialities ->
            Log.w(TAG, "Local database specialties: $specialities")
            specialtyAdapter.updateSpecialties(specialities)
        })

        viewModel.specialtyId.observe(this, Observer { id ->
            viewModel.getSpecialtyById(id)
            Log.w(TAG, "Local database specialtyId: $id")
        })

        viewModel.employeeId.observe(this, Observer { id ->
            viewModel.getEmployeeById(id)
            Log.w(TAG, "Local database employeeId: $id")
        })

    }

}