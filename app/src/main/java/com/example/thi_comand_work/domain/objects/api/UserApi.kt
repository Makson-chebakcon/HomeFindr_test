package com.example.thi_comand_work.domain.objects.api

import com.example.thi_comand_work.domain.objects.User
import com.example.thi_comand_work.domain.objects.UserLogin
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApi {

    @POST("api/register")
    suspend fun registerUser(@Body user:User):String

    @FormUrlEncoded
    @POST("api/login")
    suspend fun loginUser(
        @Field("username") phone: String,
        @Field ("password") password: String): UserLogin
    /*
    @POST("users/")
    suspend fun postUser(user: User)

    @GET("users/")
    suspend fun getAllUsers(): MutableList<User>

    @GET("users/{email}")
    suspend fun getAllUsers(@Path("email") email:String): User

    @POST("user/add/object/")
    suspend fun postUserObject(house:House)
     */


}