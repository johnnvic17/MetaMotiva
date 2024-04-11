package com.johnndev17.app_de_metas.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.johnndev17.app_de_metas.R
import com.johnndev17.app_de_metas.databinding.ActivityGoalsFormBinding
import com.johnndev17.app_de_metas.model.GoalsModel
import com.johnndev17.app_de_metas.viewModel.GoalsFormViewModel

class GoalsFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityGoalsFormBinding
    private lateinit var viewModel: GoalsFormViewModel
    private var goalsID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel = ViewModelProvider(this).get(GoalsFormViewModel::class.java)
        binding = ActivityGoalsFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSaveGoals.setOnClickListener(this)
        binding.radioInicial.isChecked = true

        loadId()
        observe()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_save_goals){

            val name = binding.editGoalsName.text.toString()
            val progress = binding.radioInicial.isChecked

            if(name != "") {

                val model = GoalsModel(goalsID, name, progress)
                viewModel.save(model)

            }
        }
    }

    private fun observe(){

        viewModel.myGoal.observe(this, Observer {

            binding.editGoalsName.setText(it.goalsName)

            if(it.progress){

                binding.radioInicial.isChecked = true

            }else {

                binding.radioInProgress.isChecked = true
            }
        })

        viewModel.notification.observe(this, Observer {

            if(it != ""){

                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                finish()
            }
        })

    }

    private fun loadId(){

        val bundle = intent.extras

        if(bundle != null){

            goalsID = bundle.getInt("goalsID")
            viewModel.getGoal(goalsID)
        }

    }
}