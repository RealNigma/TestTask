package com.realnigma.testtask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_specialty.*

class SpecialtyFragment : Fragment() {

    private lateinit var viewModel: EmployeeViewModel
    private var specialtyAdapter = SpecialtyAdapter()
    private val TAG = "EmployeeFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = activity?.run {
            ViewModelProvider(this).get(EmployeeViewModel::class.java)
        }!!

        viewModel.specialties.observe(this, Observer { specialties ->
            specialtyAdapter.updateSpecialties(specialties)
            Log.w(TAG, "Local database specialties: $specialties")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specialty, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
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
        val action = SpecialtyFragmentDirections.actionSpecialtyFragmentToEmployeeFragment()
        findNavController().navigate(action)
    }

}