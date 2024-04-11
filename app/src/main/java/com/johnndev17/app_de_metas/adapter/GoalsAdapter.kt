package com.johnndev17.app_de_metas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johnndev17.app_de_metas.databinding.RowGoalsBinding
import com.johnndev17.app_de_metas.listeners.Listener
import com.johnndev17.app_de_metas.model.GoalsModel

class GoalsAdapter(): RecyclerView.Adapter<GoalsViewHolder>() {

    private var listGoals: List<GoalsModel> = listOf()
    private lateinit var listener: Listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalsViewHolder {
        val item = RowGoalsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GoalsViewHolder(item, listener)
    }

    override fun getItemCount(): Int {
       return listGoals.count()
    }

    override fun onBindViewHolder(holder: GoalsViewHolder, position: Int) {
        holder.bind(listGoals[position])
    }

    fun updateList(list: List<GoalsModel>){

        listGoals = list
        notifyDataSetChanged()
    }

    fun listenerAdapter(listenerAdapter: Listener){

        listener = listenerAdapter
    }
}