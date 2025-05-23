package com.chesstech.skyegroup.domain

import com.chesstech.skyegroup.data.model.network.TodoRepository
import com.chesstech.skyegroup.domain.model.Todo
import javax.inject.Inject

class GetRandomTodosUseCase @Inject constructor(private val repository: TodoRepository) {

    suspend operator fun invoke(): Todo?{
        val todos = repository.getAllTodosFromDatabase()

        if (!todos.isNullOrEmpty()){
            val randomNumber = (todos.indices).random()
            return todos[randomNumber]
        }
        return null
    }

}