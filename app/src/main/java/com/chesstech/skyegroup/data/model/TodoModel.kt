package com.chesstech.skyegroup.data.model

import com.google.gson.annotations.SerializedName

/* SerializedName -> para que tome el nombre del campo de la DB */

data class TodoModel (
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("completed") val completed: Boolean
)