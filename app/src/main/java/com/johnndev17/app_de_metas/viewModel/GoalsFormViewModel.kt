package com.johnndev17.app_de_metas.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.johnndev17.app_de_metas.data.GoalsRepository
import com.johnndev17.app_de_metas.model.GoalsModel

class GoalsFormViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = GoalsRepository.getInstance(application)

    private var _myGoal = MutableLiveData<GoalsModel>()
    val myGoal: LiveData<GoalsModel> = _myGoal

    private var _notification = MutableLiveData<String>()
    var notification: LiveData<String> = _notification

    fun save(goals: GoalsModel){

        if(goals.id == 0){

            if(repository.insert(goals)){

                _notification.value = "Nova meta adicionada!"

            } else {

                _notification.value = "Falha..."
            }

        }else {

            if(repository.update(goals)){

                _notification.value = "Meta atualizada!"

            } else {

                _notification.value = "Falha..."
            }
        }
    }

    fun getGoal(id: Int){

        _myGoal.value = repository.getGoal(id)
    }
}