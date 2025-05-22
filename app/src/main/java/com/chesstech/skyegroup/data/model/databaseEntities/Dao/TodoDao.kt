package com.chesstech.skyegroup.data.model.databaseEntities.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chesstech.skyegroup.data.model.databaseEntities.TodoEntities

@Dao
interface TodoDao {

    @Query("SELECT * FROM table_skye_group ORDER BY id DESC")   //Ordenar la base de datos al recuperar la información
    suspend fun getAllTodo(): List<TodoEntities>

    @Insert(onConflict = OnConflictStrategy.REPLACE)    //Si al insertar los campos manda error, se reemplazará el valor
    suspend fun insertAll(todo:List<TodoEntities>)

    @Query("DELETE FROM table_skye_group")
    suspend fun deleteAllTodo()
}

