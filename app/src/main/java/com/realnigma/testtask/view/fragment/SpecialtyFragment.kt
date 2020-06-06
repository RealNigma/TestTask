package com.realnigma.testtask.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realnigma.testtask.viewmodel.EmployeeViewModel
import com.realnigma.testtask.R
import com.realnigma.testtask.view.adapter.SpecialtyAdapter
import kotlinx.android.synthetic.main.fragment_specialty.*

class SpecialtyFragment : Fragment() {

    private lateinit var viewModel: EmployeeViewModel
    private var specialtyAdapter =
        SpecialtyAdapter()
    private val TAG = "SpecialtyFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun initViewModel() {
        viewModel = activity?.run {
            ViewModelProvider(this).get(EmployeeViewModel::class.java)
        }!!

        viewModel.specialties.observe(viewLifecycleOwner, Observer { specialties ->
            specialtyAdapter.updateSpecialties(specialties)
            Log.w(TAG, "Local database specialties: $specialties")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_specialty, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        specialtyRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = specialtyAdapter
        }

        specialtyAdapter.setOnItemClickListener(object : SpecialtyAdapter.OnItemClickListener {
            override fun onItemClick(specialtyId: Int) {
                viewModel.specialtyId.value = specialtyId
                showEmployeeFragment()
            }

        })

    }

    private fun showEmployeeFragment() {
        val action =
            SpecialtyFragmentDirections.actionSpecialtyFragmentToEmployeeFragment()
        findNavController().navigate(action)
    }

}