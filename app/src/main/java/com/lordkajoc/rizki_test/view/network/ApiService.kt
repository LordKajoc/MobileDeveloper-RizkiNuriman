package com.lordkajoc.rizki_test.view.network

import com.lordkajoc.rizki_test.model.user.DataGetUserResponse
import com.lordkajoc.rizki_test.model.user.DataGetUserSingleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getDataUser(
        @Query("page") page : Int
    ): Call<DataGetUserResponse>

    @GET("users/{id}")
    fun getDataUserSingle(
        @Path ("id") id:Int
    ): Call<DataGetUserSingleResponse>
}