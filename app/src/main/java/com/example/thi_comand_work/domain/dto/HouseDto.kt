package com.example.thi_comand_work.domain.dto

import com.example.thi_comand_work.domain.objects.House
import com.example.thi_comand_work.domain.objects.HousePost
import com.example.thi_comand_work.domain.objects.api.HouseApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HouseDto {
    private val interseptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val cl = OkHttpClient.Builder().addInterceptor(interseptor).build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://realestatecorrect-1.onrender.com")
        .client(cl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val houseApi = retrofit.create(HouseApi::class.java)

    suspend fun getAllHouse():MutableList<House>{
        return houseApi.getHouseList()
    }
    suspend fun postHouse(house: HousePost , owner : String) {
        houseApi.postHouseCreate(owner,house)
    }
}