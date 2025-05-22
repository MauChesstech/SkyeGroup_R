package com.chesstech.skyegroup.domain

import com.chesstech.skyegroup.data.model.TodoModel
import com.chesstech.skyegroup.data.model.network.TodoRepository
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(

    //Inyectando dependencias de casos de uso
    private val repositorio: TodoRepository
){
    //Retornar el caso de uso
    suspend operator fun invoke(): List<TodoModel> = repositorio.getAllTodos()

}