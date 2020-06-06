package com.realnigma.testtask.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realnigma.testtask.R
import com.realnigma.testtask.room.Employee
import com.realnigma.testtask.utils.calculateAge
import com.realnigma.testtask.utils.getFullName
import kotlinx.android.synthetic.main.employee_item.view.*
import kotlin.collections.ArrayList

class EmployeeAdapter : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private var employees = ArrayList<Employee>()
    private var listener: OnItemClickListener? = null

    fun updateEmployees(employees: List<Employee>) {
        this.employees.clear()
        this.employees.addAll(employees)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int) =
        EmployeeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.employee_item,
                parent,
                false
            ), listener
        )

    override fun getItemCount(): Int = employees.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(employees[position])
        holder.onItemClick(employees[position].employee_id)
    }

    class EmployeeViewHolder(view: View, private  val interaction: OnItemClickListener?) : RecyclerView.ViewHolder(view) {
        private val fullName = view.fullName
        private val age = view.age

        fun bind(employee: Employee) {
            fullName.text = getFullName(
                firstName = employee.f_name,
                lastName = employee.l_name
            )
            val ageText =
                calculateAge(employee.birthday)
            if (ageText != "") age.text = ageText
        }

        fun onItemClick(employeeId: Int) {
            itemView.setOnClickListener {
                interaction?.onItemClick(employeeId)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(employeeId : Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}