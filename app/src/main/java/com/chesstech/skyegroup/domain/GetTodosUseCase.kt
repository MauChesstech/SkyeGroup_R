package com.chesstech.skyegroup.domain

import com.chesstech.skyegroup.data.model.databaseEntities.toDatabase
import com.chesstech.skyegroup.data.model.network.TodoRepository
import com.chesstech.skyegroup.domain.model.Todo
import javax.inject.Inject

//Inyectando dependencias de casos de uso
class GetTodosUseCase @Inject constructor(private val repositorio: TodoRepository){

        //Retornar el caso de uso
    suspend operator fun invoke(): List<Todo>
    {
        val todos = repositorio.getAllTodosFromApi()

        return if (todos.isNotEmpty()){
            repositorio.clearTodos()
            repositorio.insertTodos(todos.map { it.toDatabase() })
            todos
        }
        else{
            repositorio.getAllTodosFromDatabase()
        }
    }
}