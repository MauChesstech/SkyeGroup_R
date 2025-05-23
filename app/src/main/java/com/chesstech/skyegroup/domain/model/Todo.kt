package com.chesstech.skyegroup.domain.model

import com.chesstech.skyegroup.data.model.TodoModel
import com.chesstech.skyegroup.data.model.databaseEntities.TodoEntities

data class Todo (
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean,
    val isFromApi: Boolean = false
)

fun TodoModel.toDonain() = Todo(
    userId, id, title, completed, isFromApi = true
)

fun TodoEntities.toDonain() = Todo(
    userId, id, title, completed, isFromApi = false
)