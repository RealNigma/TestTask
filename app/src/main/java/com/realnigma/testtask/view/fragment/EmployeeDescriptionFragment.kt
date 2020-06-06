package com.realnigma.testtask.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realnigma.testtask.R
import com.realnigma.testtask.utils.calculateAge
import com.realnigma.testtask.utils.convertDate
import com.realnigma.testtask.utils.getFullName
import com.realnigma.testtask.utils.loadImage
import com.realnigma.testtask.view.adapter.SpecialtyAdapter
import com.realnigma.testtask.viewmodel.EmployeeViewModel
import kotlinx.android.synthetic.main.fragment_employee_description.*


class EmployeeDescriptionFragment : Fragment() {

    private lateinit var viewModel: EmployeeViewModel
    private var specialtyAdapter = SpecialtyAdapter()
    private val TAG = "EmployeeDescrFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employee_description, container, false)
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

        viewModel.employeeById?.observe(viewLifecycleOwner, Observer { employeeById ->
            fullName.text = getFullName(employeeById.employee.f_name, employeeById.employee.l_name)
            if (employeeById.employee.birthday == null) {
                birthdayText.text = "««"
            }
            else {
                birthdayText.text = employeeById.employee.birthday.let { convertDate(it) }
            }
            ageText.text = calculateAge(employeeById.employee.birthday)
            loadImage(employeeById.employee.avatr_url, employeeImage)
            specialtyAdapter.updateSpecialties(employeeById.specialty)
            Log.w(TAG, "Local database employeesById: $employeeById")
        })
    }

    private fun initRecyclerView() {
       specialtyDescriptionRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = specialtyAdapter
        }
    }

}