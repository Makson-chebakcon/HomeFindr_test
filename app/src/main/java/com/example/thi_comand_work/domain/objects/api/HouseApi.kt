package com.example.thi_comand_work.domain.objects.api

import com.example.thi_comand_work.domain.objects.House
import com.example.thi_comand_work.domain.objects.HousePost
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HouseApi {
    @GET("items/")
   suspend fun getHouseList(): MutableList<House>
   @GET("items/{email}")
   suspend fun getHouseToEmail(@Path("email") email:String):House

   @GET("items/get/{id}")
   suspend fun getHouseToId(@Path("id") id:Int):House

   @GET("items/get/avg_price/{id}")
   suspend fun getHouseAvgPriceToId(@Path("id") id:Int):House


    @POST("user/add/object/")
    suspend fun postHouseCreate(@Query("owner") owner: String, @Body house: HousePost): HousePost

}