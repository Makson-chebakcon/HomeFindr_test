package com.example.thi_comand_work.domain.objects

data class User (
    val name: String,
    val sur_name: String,
    val object_home:List<Int>,
    val email: String,
    val img_url: String
)