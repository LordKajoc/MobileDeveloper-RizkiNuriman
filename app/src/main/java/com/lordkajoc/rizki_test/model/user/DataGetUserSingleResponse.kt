package com.lordkajoc.rizki_test.model.user


import com.google.gson.annotations.SerializedName
import com.lordkajoc.rizki_test.model.user.DataGetUserResponseItem

data class DataGetUserSingleResponse(
    @SerializedName("data")
    val `data`: DataGetUserResponseItem?,
)