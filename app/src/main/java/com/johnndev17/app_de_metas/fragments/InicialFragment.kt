package com.johnndev17.app_de_metas.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.johnndev17.app_de_metas.views.GoalsFormActivity
import com.johnndev17.app_de_metas.adapter.GoalsAdapter
import com.johnndev17.app_de_metas.databinding.FragmentInicialBinding
import com.johnndev17.app_de_metas.listeners.Listener
import com.johnndev17.app_de_metas.viewModel.GoalsViewModel

class InicialFragment : Fragment() {
    private var _binding: FragmentInicialBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GoalsViewModel
    private val adapter = GoalsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View? {
        _binding = FragmentInicialBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(GoalsViewModel::class.java)

        binding.recyclerList.layoutManager = LinearLayoutManager(context)
        binding.recyclerList.adapter = adapter

        val ltn = object : Listener {
            override fun onUpgrade(id: Int) {

                val i = Intent(context, GoalsFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt("goalsID", id)

                i.putExtras(bundle)
                startActivity(i)
            }

            override fun onRemove(id: Int) {
                viewModel.remove(id)
                viewModel.getInicialGoals()
            }
        }

        adapter.listenerAdapter(ltn)
        observe()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.getInicialGoals()
    }

    private fun observe(){
        viewModel.listGoals.observe(viewLifecycleOwner) {

            adapter.updateList(it)
        }
    }
}