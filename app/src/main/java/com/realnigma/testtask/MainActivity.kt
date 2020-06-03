package com.realnigma.testtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realnigma.testtask.room.EmployeeDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: EmployeeViewModel
    private var specialtyAdapter = SpecialtyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initRecyclerView()
    }

    private fun initViewModel() {
        Log.w("Employee", "Creating viewmodel...")
        viewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        viewModel.insertEmployeesAndSpecialties()
        viewModel.specialties.observe(this, Observer { specialities ->
            Log.w("Employee", "data are right here: $specialities")
            specialtyAdapter.updateSpecialties(specialities)
        })

        viewModel.employees.observe(this, Observer { specialities ->
            Log.w("Employee", "data are right here: $specialities")
        })
    }

    private fun initRecyclerView() {
        specialtyRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
            adapter = specialtyAdapter
        }
    }
}