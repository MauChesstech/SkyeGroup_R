package com.chesstech.skyegroup.core

import android.content.Context
import androidx.room.Room
import com.chesstech.skyegroup.data.model.databaseEntities.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val TODO_DATABASE_NAME = "todo_database"  /* Base de datos local */

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TodoDatabase::class.java, TODO_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideTodoDao(db: TodoDatabase) = db.getTodoDao()
}