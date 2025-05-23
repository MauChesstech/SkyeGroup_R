package com.chesstech.skyegroup.core

import com.chesstech.skyegroup.data.model.network.TodoApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /* Proveedor Retrofit */
    @Singleton /* Mantengo una única instancia de retrofit */
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")   /* Liga del JSON */
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providerTodoApiClient(retrofit: Retrofit): TodoApiClient {
        return retrofit.create(TodoApiClient::class.java)
    }
}