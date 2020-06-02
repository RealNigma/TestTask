package com.realnigma.testtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.realnigma.testtask.room.EmployeeDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        Log.w("Employee", "Creating viewmodel...")

        viewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        viewModel.insertEmployeesAndSpecialties()
        viewModel.specialties.observe(this, Observer {
                specialities ->
            Log.w("Employee", "data are right here: $specialities") })

        viewModel.employees.observe(this, Observer {
                specialities ->
            Log.w("Employee", "data are right here: $specialities") })
    }
}