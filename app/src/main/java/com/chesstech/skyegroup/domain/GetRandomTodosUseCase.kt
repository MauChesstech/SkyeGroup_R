package com.chesstech.skyegroup.domain

import com.chesstech.skyegroup.data.model.TodoModel
import com.chesstech.skyegroup.data.model.TodoProvider
import javax.inject.Inject

class GetRandomTodosUseCase @Inject constructor(

    private val todoProvider: TodoProvider
) {

    operator  fun invoke():TodoModel?{
        val todos = todoProvider.todos

        if (!todos.isNullOrEmpty()){
            val randomNumber = (todos.indices).random()
            return todos[randomNumber]
        }
        return null
    }

}