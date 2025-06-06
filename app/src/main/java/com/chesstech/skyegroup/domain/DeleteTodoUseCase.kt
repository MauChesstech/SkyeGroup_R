package com.chesstech.skyegroup.domain

import com.chesstech.skyegroup.data.model.network.TodoRepository
import com.chesstech.skyegroup.domain.model.Todo
import javax.inject.Inject

    /* Caso de uso para eliminar los items de la base local con clase Todo */
class DeleteTodoUseCase @Inject constructor(
     private val repository: TodoRepository
) {
        suspend operator fun invoke(todo: Todo) {
        repository.deleteItemTodo(todo)
    }
}