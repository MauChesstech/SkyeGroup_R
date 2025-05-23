package com.chesstech.skyegroup.data.model.network

import com.chesstech.skyegroup.data.model.TodoModel
import retrofit2.Response
import retrofit2.http.GET

interface TodoApiClient {   /* API Client recibiendo la información */

    @GET("/todos")
    suspend fun getAllTodos(): Response<List<TodoModel>>
}