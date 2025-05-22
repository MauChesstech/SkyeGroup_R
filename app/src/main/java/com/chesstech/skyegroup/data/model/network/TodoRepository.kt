package com.chesstech.skyegroup.data.model.network

import com.chesstech.skyegroup.data.model.TodoModel
import com.chesstech.skyegroup.data.model.TodoProvider
import javax.inject.Inject

class TodoRepository @Inject constructor(

    //Inyectando dependencias de casos de uso
    private val api: TodoService,
    private val todoProvider: TodoProvider
) {

    suspend fun getAllTodos():List<TodoModel>{
        val response = api.getTodos()
        todoProvider.todos = response
        return response
    }

}