package com.realnigma.testtask.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realnigma.testtask.view.adapter.EmployeeAdapter
import com.realnigma.testtask.viewmodel.EmployeeViewModel
import com.realnigma.testtask.R
import kotlinx.android.synthetic.main.fragment_employee.*

class EmployeeFragment : Fragment() {


    private lateinit var viewModel: EmployeeViewModel
    private var employeeAdapter =
        EmployeeAdapter()
    private val TAG = "EmployeeFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = activity?.run {
            ViewModelProvider(this).get(EmployeeViewModel::class.java)
        }!!

        viewModel.specialtyById?.observe(viewLifecycleOwner, Observer { specialtyById ->
                employeeAdapter.updateEmployees(specialtyById.employee)
                Log.w(TAG, "Local database specialtyById: $specialtyById")
            })

    }

    private fun initRecyclerView() {
        employeeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = employeeAdapter
        }

        employeeAdapter.setOnItemClickListener(object :
            EmployeeAdapter.OnItemClickListener {
            override fun onItemClick(employeeId: Int) {
                viewModel.employeeId.value = employeeId
                showEmployeeDescriptionFragment()
            }
        })
    }

    private fun showEmployeeDescriptionFragment() {
       val action =
           EmployeeFragmentDirections.actionEmployeeFragmentToEmployeeDescriptionFragment()
        findNavController().navigate(action)
    }


}