package com.realnigma.testtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realnigma.testtask.room.Specialty
import com.realnigma.testtask.room.SpecialtyWithEmployees
import kotlinx.android.synthetic.main.specialty_item.view.*

class SpecialtyAdapter : RecyclerView.Adapter<SpecialtyAdapter.SpecialtyViewHolder>() {

    private var specialties = ArrayList<SpecialtyWithEmployees>()

    fun updateSpecialties(specialties: List<SpecialtyWithEmployees>) {
        this.specialties.clear()
        this.specialties.addAll(specialties)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int) =
        SpecialtyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.specialty_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = specialties.size

    override fun onBindViewHolder(holder: SpecialtyViewHolder, position: Int) {
        holder.bind(specialties[position])
    }

    class SpecialtyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val specialtyId = view.specialtyId
        private val specialtyName = view.specialtyName

        fun bind(specialty: SpecialtyWithEmployees) {
            specialtyId.text = specialty.specialty.specialty_id.toString()
            specialtyName.text = specialty.specialty.name
        }

    }
}