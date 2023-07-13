package com.lordkajoc.rizki_test.model.user


import com.google.gson.annotations.SerializedName

data class DataGetUserSingleResponse(
    @SerializedName("data")
    val `data`: DataGetUserResponseItem?,
)