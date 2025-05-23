package com.chesstech.skyegroup.data.model.databaseEntities.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.chesstech.skyegroup.data.model.databaseEntities.TodoEntities

@Dao
interface TodoDao {

    @Query("SELECT * FROM table_skye_group ORDER BY id ASC")   //Ordenar la base de datos al recuperar la información
    suspend fun getAllTodo(): List<TodoEntities>

    @Insert(onConflict = OnConflictStrategy.REPLACE)    //Si al insertar los campos manda error, se reemplazará el valor
    suspend fun insertAll(todo: List<TodoEntities>)

    @Query("DELETE FROM table_skye_group")
    suspend fun deleteAllTodo()

    @Delete
    suspend fun deleteItemTodo(todo: TodoEntities) // Esto borra solo el ítem recibido

    @Update
    suspend fun updateTodo(todo: TodoEntities)
}

