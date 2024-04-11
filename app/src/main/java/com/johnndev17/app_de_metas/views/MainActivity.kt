package com.johnndev17.app_de_metas.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.johnndev17.app_de_metas.R
import com.johnndev17.app_de_metas.SharedPreferences.GoalsPreferences
import com.johnndev17.app_de_metas.databinding.ActivityMainBinding
import com.johnndev17.app_de_metas.fragments.AllFragment
import com.johnndev17.app_de_metas.fragments.InicialFragment
import com.johnndev17.app_de_metas.fragments.ProgressFragment

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var allGoals: AllFragment
    private lateinit var inicialGoals: InicialFragment
    private lateinit var progressGoals: ProgressFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadUser()
        listeners()

        allGoals = AllFragment()
        inicialGoals = InicialFragment()
        progressGoals = ProgressFragment()

        setFragment(allGoals)

        binding.fabMain.setOnClickListener {

            startActivity(Intent(applicationContext, GoalsFormActivity::class.java))
        }
    }

    override fun onClick(v: View) {
        switchFragments(v.id)
    }

    private fun listeners(){
        binding.btnAll.setOnClickListener(this)
        binding.btnInicial.setOnClickListener(this)
        binding.btnInProgress.setOnClickListener(this)
    }

    private fun setFragment(fragment: Fragment){
        val switchFrag = supportFragmentManager.beginTransaction()
        switchFrag.replace(R.id.layout_fragments, fragment)
        switchFrag.commit()
    }

    private fun switchFragments(id: Int){
        when(id){
            R.id.btn_all -> {
                setFragment(allGoals)
            }
            R.id.btn_inicial -> {
                setFragment(inicialGoals)
            }
            R.id.btn_in_progress -> {
                setFragment(progressGoals)
            }
        }
    }

    private fun loadUser(){
        val username = GoalsPreferences(this).getUserName("userName")
        binding.textName.text = "Olá $username, essas são suas metas"
    }
}