package com.realnigma.testtask.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realnigma.testtask.R
import com.realnigma.testtask.room.Specialty
import kotlinx.android.synthetic.main.specialty_item.view.*

class SpecialtyAdapter : RecyclerView.Adapter<SpecialtyAdapter.SpecialtyViewHolder>() {

    private var specialties = ArrayList<Specialty>()
    private var listener: OnItemClickListener? = null

    fun updateSpecialties(specialties: List<Specialty>) {
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
            ), listener
        )

    override fun getItemCount(): Int = specialties.size

    override fun onBindViewHolder(holder: SpecialtyViewHolder, position: Int) {
        holder.bind(specialties[position])
        holder.onItemClick(specialties[position].specialty_id)
    }

    class SpecialtyViewHolder(view: View, private  val interaction: OnItemClickListener?) : RecyclerView.ViewHolder(view) {
        private val specialtyId = view.specialtyId
        private val specialtyName = view.specialtyName

        fun bind(specialty: Specialty) {
            specialtyId.text = specialty.specialty_id.toString()
            specialtyName.text = specialty.name
        }

        fun onItemClick(specialtyId: Int) {
            itemView.setOnClickListener {
                interaction?.onItemClick(specialtyId)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(specialtyId : Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}