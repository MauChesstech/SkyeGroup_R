package com.chesstech.skyegroup.data.model.databaseEntities

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chesstech.skyegroup.data.model.databaseEntities.Dao.TodoDao

@Database(entities = [TodoEntities::class], version = 1, exportSchema = false)

abstract class TodoDatabase: RoomDatabase() {
    abstract fun getTodoDao(): TodoDao
}