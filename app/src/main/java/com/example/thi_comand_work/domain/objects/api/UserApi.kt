package com.example.thi_comand_work.domain.objects.api

import com.example.thi_comand_work.domain.objects.House
import com.example.thi_comand_work.domain.objects.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @POST("users/")
    suspend fun postUser(user: User)

    @GET("users/")
    suspend fun getAllUsers(): MutableList<User>

    @GET("users/{email}")
    suspend fun getAllUsers(@Path("email") email:String): User

    @POST("user/add/object/")
    suspend fun postUserObject(house:House)



}