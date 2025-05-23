package com.chesstech.skyegroup.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")   /* Liga del JSON */
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}