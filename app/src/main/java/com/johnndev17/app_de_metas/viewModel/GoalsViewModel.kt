package com.johnndev17.app_de_metas.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.johnndev17.app_de_metas.data.GoalsRepository
import com.johnndev17.app_de_metas.model.GoalsModel

class GoalsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GoalsRepository.getInstance(application)

    private val _listGoals = MutableLiveData<List<GoalsModel>>()
    val listGoals: LiveData<List<GoalsModel>> = _listGoals

    fun getAllGoals(){

       _listGoals.value = repository.allGoals()
    }

    fun getInicialGoals(){

        _listGoals.value = repository.inicialGoals()
    }

    fun getInProgressGoals(){

        _listGoals.value = repository.inProgressGoals()
    }

    fun remove(id: Int){

        repository.remove(id)
    }
}