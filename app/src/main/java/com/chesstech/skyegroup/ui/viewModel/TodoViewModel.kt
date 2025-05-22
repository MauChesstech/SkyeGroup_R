package com.chesstech.skyegroup.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chesstech.skyegroup.domain.GetTodosUseCase
import com.chesstech.skyegroup.domain.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
/*
@HiltViewModel
class TodoViewModel @Inject constructor(
        //Inyectando dependencias de casos de uso
    private val getTodoUseCase: GetTodosUseCase,
    private val getRandomTodosUseCase: GetRandomTodosUseCase

) : ViewModel(){

    val todoModel = MutableLiveData<Todo>()
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
        viewModelScope.launch {
            isLoading.postValue(true)

            val quote = getRandomTodosUseCase()
            if(quote != null){
                todoModel.postValue(quote)
            }

            isLoading.postValue(false)
        }
    }
} */

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase
) : ViewModel() {

    private val _todosList = MutableLiveData<List<Todo>>()
    val todosList: LiveData<List<Todo>> get() = _todosList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun onCreate() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val result = getTodosUseCase()
                _todosList.value = result
            } catch (e: Exception) {
                // Manejo de errores
                Log.e("TodoViewModel", "Error fetching todos", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}