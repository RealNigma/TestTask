package com.realnigma.testtask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider



class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: EmployeeViewModel
    private var specialtyAdapter = SpecialtyAdapter()
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        //initRecyclerView()



    }

    private fun initViewModel() {
        Log.w("Employee", "Creating viewmodel...")
        viewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        viewModel.insertEmployeesAndSpecialties()
        viewModel.specialties.observe(this, Observer { specialities ->
            Log.w(TAG, "Local database specialties: $specialities")
            specialtyAdapter.updateSpecialties(specialities)
        })

        viewModel.employees.observe(this, Observer { employees ->
            Log.w(TAG, "Local database employees: $employees")
        })


        viewModel.ref.observe(this, Observer { ref ->
            Log.w(TAG, "Local database ref: $ref")
        })

        viewModel.specialtyId.observe(this, Observer { id ->
            //showEmployeeFragment()
            Log.w(TAG, "Local database specialtyId: $id")
        })
    }

}