package com.chesstech.skyegroup.data.model.network

import com.chesstech.skyegroup.data.model.TodoModel
import com.chesstech.skyegroup.data.model.databaseEntities.Dao.TodoDao
import com.chesstech.skyegroup.data.model.databaseEntities.TodoEntities
import com.chesstech.skyegroup.data.model.databaseEntities.toDatabase
import com.chesstech.skyegroup.domain.model.Todo
import com.chesstech.skyegroup.domain.model.toDonain
import javax.inject.Inject

class TodoRepository @Inject constructor(        /* Inyectando dependencias de casos de uso */
    private val api: TodoService,
    private val todoDao: TodoDao
) {

    suspend fun getAllTodosFromApi(): List<Todo> {  /* Retornará toda la información de la API */
        val response: List<TodoModel> = api.getTodos()
        return response.map { it.toDonain() }
    }

    suspend fun getAllTodosFromDatabase(): List<Todo> {  /* Retornará toda la información de la DB local */
        val response: List<TodoEntities> = todoDao.getAllTodo()
        return response.map { it.toDonain() }
    }

    suspend fun insertTodos(todos: List<TodoEntities>) {    /* Inserción de los datos en las listas */
        todoDao.insertAll(todos)
    }

    suspend fun clearTodos() {  /* Limpiar la información */
        todoDao.deleteAllTodo()
    }

        /* Métodos para la actualización y eliminación de información */
    suspend fun deleteItemTodo(todos: Todo) {
        todoDao.deleteItemTodo(todos.toDatabase())
    }

    suspend fun updateTodo(todos: Todo) {
        todoDao.updateTodo(todos.toDatabase())
    }

    suspend fun updateTodoStatus(todoId: Int, completed: Boolean) {
        todoDao.updateTodoStatus(todoId, completed)
    }
}