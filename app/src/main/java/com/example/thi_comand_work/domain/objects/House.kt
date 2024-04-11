package com.example.thi_comand_work.domain.objects

data class House ( //get to Id
    val name: String,
    val adress: String,
    val img_url: String,
    val views: Int,
    val description: String,
    val id: Int,
    val price: Int,
    val owner: String
)