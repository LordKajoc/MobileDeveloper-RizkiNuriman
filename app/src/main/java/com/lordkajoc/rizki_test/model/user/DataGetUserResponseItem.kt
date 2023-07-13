package com.lordkajoc.rizki_test.model.user


import com.google.gson.annotations.SerializedName

data class DataGetUserResponseItem(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String
)