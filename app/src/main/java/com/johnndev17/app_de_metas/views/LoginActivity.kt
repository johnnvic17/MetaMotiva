package com.johnndev17.app_de_metas.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.johnndev17.app_de_metas.R
import com.johnndev17.app_de_metas.SharedPreferences.GoalsPreferences
import com.johnndev17.app_de_metas.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSaveLogin.setOnClickListener(this)
        verifyUserLogin()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_save_login){

            val name = binding.editUserName.text.toString()

            if(name != ""){
                val i = Intent(this, MainActivity::class.java)

                GoalsPreferences(this).saveUserName("userName", name)

                val bundle = Bundle()
                bundle.putString("username", name)

                i.putExtras(bundle)
                startActivity(i)
                finish()

            }else {
                Toast.makeText(this, "Nome inválido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun verifyUserLogin(){

        val username = GoalsPreferences(this).getUserName("userName")

        if(username != ""){

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}