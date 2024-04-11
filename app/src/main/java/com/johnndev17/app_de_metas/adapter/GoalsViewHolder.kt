package com.johnndev17.app_de_metas.adapter

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.johnndev17.app_de_metas.databinding.RowGoalsBinding
import com.johnndev17.app_de_metas.listeners.Listener
import com.johnndev17.app_de_metas.model.GoalsModel

class GoalsViewHolder(private val bind: RowGoalsBinding, private val listener: Listener): RecyclerView.ViewHolder(bind.root) {

    fun bind(goals: GoalsModel){

        bind.goalsName.text = goals.goalsName

        bind.btnRemove.setOnClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle("Confirmação de remoção")
                .setMessage("Tem certeza que deseja remover esta meta? ")
                .setPositiveButton("Sim") {dialog, which ->

                    listener.onRemove(goals.id)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()
        }

        bind.viewRow.setOnLongClickListener {

            listener.onUpgrade(goals.id)
            true
        }
    }
}