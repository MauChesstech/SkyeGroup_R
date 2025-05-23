package com.chesstech.skyegroup.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chesstech.skyegroup.domain.DeleteTodoUseCase
import com.chesstech.skyegroup.domain.GetTodosUseCase
import com.chesstech.skyegroup.domain.UpdateTodoUseCase
import com.chesstech.skyegroup.domain.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase
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
            }
            catch (e: Exception) {
                Log.e("TodoViewModel", "Error: ", e)
            }
            finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                deleteTodoUseCase(todo)
            } catch (e: Exception) {
                Log.e("TodoViewModel", "Error: ", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                updateTodoUseCase(todo)
            } catch (e: Exception) {
                Log.e("TodoViewModel", "Error: ", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}