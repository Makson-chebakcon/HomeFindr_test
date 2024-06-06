package com.example.thi_comand_work.domain.objects

data class User (
    val phone: String,
    val password: String,
    val confirm_password: String,
    val email: String
)