package com.chesstech.skyegroup.domain

import android.content.Context
import com.chesstech.skyegroup.data.model.databaseEntities.toDatabase
import com.chesstech.skyegroup.data.model.network.NetworkUtils.isInternetAvailable
import com.chesstech.skyegroup.data.model.network.TodoRepository
import com.chesstech.skyegroup.domain.model.Todo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/* Inyectando dependencias de casos de uso */
class GetTodosUseCase @Inject constructor(private val repository: TodoRepository,
                                          @ApplicationContext private val context: Context
){
        //Retornar el caso de uso
    suspend operator fun invoke(): List<Todo>
    {
        //val todos = repository.getAllTodosFromApi()

        // Siempre se devolverán datos locales si existen
        val localTodos = repository.getAllTodosFromDatabase()
        if (localTodos.isNotEmpty()) {
            return localTodos
        }

        return if (isInternetAvailable(context)) {  /* Se hará comprobación de internet */
            try {
                val apiTodos = repository.getAllTodosFromApi()
                if (apiTodos.isNotEmpty()) {
                    repository.clearTodos()
                    repository.insertTodos(apiTodos.map { it.toDatabase() })
                }
                apiTodos
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
/*
        return if (todos.isNotEmpty()){
            repository.clearTodos()
            repository.insertTodos(todos.map { it.toDatabase() })
            todos
        }
        else{
            repository.getAllTodosFromDatabase()
        }*/
    }
}