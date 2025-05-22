package com.chesstech.skyegroup.data.model.databaseEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chesstech.skyegroup.domain.model.Todo

@Entity(tableName = "table_skye_group")
data class TodoEntities (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("userId") val userId: Int,
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("completed") val completed: Boolean
)

fun Todo.toDatabase() = TodoEntities(userId, id, title, completed)