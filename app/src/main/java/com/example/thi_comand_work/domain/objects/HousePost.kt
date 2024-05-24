package com.example.thi_comand_work.domain.objects

data class HousePost(
    val name: String,
    val adress: String,
    val description: String,
    val price: Int,
    val owner: String,
    val img_url: String,
    val views: Int
)
