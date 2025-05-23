package com.chesstech.skyegroup.data.model.network

import dagger.hilt.android.qualifiers.ApplicationContext
import android.content.Context
import com.chesstech.skyegroup.data.model.TodoModel
import com.chesstech.skyegroup.data.model.databaseEntities.Dao.TodoDao
import com.chesstech.skyegroup.data.model.databaseEntities.TodoEntities
import com.chesstech.skyegroup.data.model.databaseEntities.toDatabase
import com.chesstech.skyegroup.domain.model.Todo
import com.chesstech.skyegroup.domain.model.toDonain
import javax.inject.Inject

class TodoRepository @Inject constructor(
        /* Inyectando dependencias de casos de uso */
    private val api: TodoService,
    private val todoDao: TodoDao,
    @ApplicationContext private val context: Context
) {

    suspend fun getAllTodosFromApi(): List<Todo> {
        /* if (NetworkUtils.isInternetAvailable(context)) {
            val response: List<TodoModel> = api.getTodos()
            return response.map { it.toDonain() }
        } else {
            val response: List<TodoEntities> = todoDao.getAllTodo()
            return response.map { it.toDonain() }
        } */
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

        /* Métodos para la actualización y eliminación de información */
    suspend fun deleteItemTodo(todos: Todo) {
        todoDao.deleteItemTodo(todos.toDatabase())
    }

    suspend fun updateTodo(todos: Todo) {
        todoDao.updateTodo(todos.toDatabase())
    }
}