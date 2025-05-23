package com.chesstech.skyegroup.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chesstech.skyegroup.data.model.network.TodoRepository
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
    private val repository: TodoRepository
) : ViewModel() {

    private val _todosList = MutableLiveData<List<Todo>>()
    val todosList: LiveData<List<Todo>> get() = _todosList

    private val _dataSource = MutableLiveData<DataSource>()
    val dataSource: LiveData<DataSource> get() = _dataSource

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    sealed class DataSource {
        data object API : DataSource()
        data object Local : DataSource()
        data object Empty : DataSource()
    }

    fun onCreate() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = getTodosUseCase()
                _todosList.value = result
                _dataSource.value = when {
                    result.isEmpty() -> DataSource.Empty
                    result.any { it.isFromApi } -> DataSource.API
                    else -> DataSource.Local
                }
            }
            catch (e: Exception) {
                _dataSource.value = DataSource.Local
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

    fun updateTodoStatus(todoId: Int, isChecked: Boolean) {
        _todosList.value = _todosList.value?.map { todo ->
            if (todo.id == todoId) todo.copy(completed = isChecked) else todo
        }

        viewModelScope.launch {
            repository.updateTodoStatus(todoId, isChecked)
        }
    }
}