package com.chesstech.skyegroup.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chesstech.skyegroup.data.model.TodoModel
import com.chesstech.skyegroup.domain.GetRandomTodosUseCase
import com.chesstech.skyegroup.domain.GetTodosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
        //Inyectando dependencias de casos de uso
    private val getTodoUseCase: GetTodosUseCase,
    private val getRandomTodosUseCase: GetRandomTodosUseCase

) : ViewModel(){

    val todoModel = MutableLiveData<TodoModel>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {

        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getTodoUseCase()

            if (result.isNotEmpty()){
                todoModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomTodo(){ //Recupera un numero aleatorio y se lo pone al viewModel

        isLoading.postValue(true)

        val quote = getRandomTodosUseCase()
        if(quote != null){
            todoModel.postValue(quote)
        }

        isLoading.postValue(false)
    }
}