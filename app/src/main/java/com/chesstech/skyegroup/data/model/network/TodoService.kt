package com.chesstech.skyegroup.data.model.network

import com.chesstech.skyegroup.data.model.TodoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoService @Inject constructor(private val api: TodoApiClient)
{
    suspend fun getTodos():List<TodoModel>{
        return withContext(Dispatchers.IO) {
            val response = api.getAllTodos()
            response.body() ?: emptyList() /* Si la lista es nula, regresa una lista vacía */
        }
    }
}