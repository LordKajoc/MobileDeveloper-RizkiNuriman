package com.lordkajoc.rizki_test.model.user


import com.google.gson.annotations.SerializedName

data class DataGetUserResponse(
    @SerializedName("data")
    val `data`: List<DataGetUserResponseItem>
)