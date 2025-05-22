package com.chesstech.skyegroup.domain.model

import com.chesstech.skyegroup.data.model.TodoModel
import com.chesstech.skyegroup.data.model.databaseEntities.TodoEntities

data class Todo (
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)

fun TodoModel.toDonain() = Todo(
    userId, id, title, completed
)

fun TodoEntities.toDonain() = Todo(
    userId, id, title, completed
)