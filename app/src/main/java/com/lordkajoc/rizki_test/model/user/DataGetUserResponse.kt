package com.lordkajoc.rizki_test.model.user


import com.google.gson.annotations.SerializedName

data class DataGetUserResponse(
    @SerializedName("data")
    val `data`: List<DataGetUserResponseItem>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)