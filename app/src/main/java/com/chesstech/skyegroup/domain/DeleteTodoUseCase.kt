package com.chesstech.skyegroup.domain

import com.chesstech.skyegroup.data.model.network.TodoRepository
import com.chesstech.skyegroup.domain.model.Todo
import javax.inject.Inject

/*
class DeleteTodoUseCase @Inject constructor(private val repository: TodoRepository)
{
   suspend operator fun invoke() {
       val todos = repository.getAllTodosFromDatabase()

       repository.deleteItemTodo(todos.map { it.toDatabase() })
   }
} */

class DeleteTodoUseCase @Inject constructor(
     private val repository: TodoRepository
) {
        suspend operator fun invoke(todo: Todo) {
        repository.deleteItemTodo(todo)
    }
}