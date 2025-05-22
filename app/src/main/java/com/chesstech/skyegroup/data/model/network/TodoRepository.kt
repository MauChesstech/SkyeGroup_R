package com.chesstech.skyegroup.data.model.network

import com.chesstech.skyegroup.data.model.TodoModel
import com.chesstech.skyegroup.data.model.databaseEntities.Dao.TodoDao
import com.chesstech.skyegroup.data.model.databaseEntities.TodoEntities
import com.chesstech.skyegroup.domain.model.Todo
import com.chesstech.skyegroup.domain.model.toDonain
import javax.inject.Inject

class TodoRepository @Inject constructor(

    //Inyectando dependencias de casos de uso
    private val api: TodoService,
    private val todoDao: TodoDao
) {

    suspend fun getAllTodosFromApi(): List<Todo> {
        val response: List<TodoModel> = api.getTodos()
        return response.map { it.toDonain() }
    }

    suspend fun getAllTodosFromDatabase(): List<Todo> {
        val response: List<TodoEntities> = todoDao.getAllTodo()
        return response.map { it.toDonain() }
    }

    suspend fun insertTodos(todos: List<TodoEntities>) {
        todoDao.insertAll(todos)
    }

    suspend fun clearTodos() {
        todoDao.deleteAllTodo()
    }
}