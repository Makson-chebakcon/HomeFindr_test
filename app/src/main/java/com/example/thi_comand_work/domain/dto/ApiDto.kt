package com.example.thi_comand_work.domain.dto

import com.example.thi_comand_work.domain.objects.User
import com.example.thi_comand_work.domain.objects.UserLogin
import com.example.thi_comand_work.domain.objects.api.HouseApi
import com.example.thi_comand_work.domain.objects.api.UserApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiDto {
    private val interseptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val cl = OkHttpClient.Builder().addInterceptor(interseptor).build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://uni-project-uf6u.onrender.com")
        .client(cl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val houseApi = retrofit.create(HouseApi::class.java)
    private val userApi = retrofit.create(UserApi::class.java)

    suspend fun postRegistrationUser(user: User){
        userApi.registerUser(user)
    }
    suspend fun postLoginUser(phone : String, password: String): UserLogin{
        return userApi.loginUser(phone, password)
    }


/*
    suspend fun getAllHouse():MutableList<House>{
        return houseApi.getHouseList()
    }
    suspend fun postHouse(house: HousePost, owner : String) {
        houseApi.postHouseCreate(owner,house)
    }

 */
}