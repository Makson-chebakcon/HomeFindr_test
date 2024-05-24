package com.example.thi_comand_work.domain.objects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ViewModel {
    private val _houseList = MutableLiveData<MutableList<House>>()
    private val _user = MutableLiveData<User>()
    private val _statusAuth = MutableLiveData<Boolean>()

    val houseList:LiveData<MutableList<House>> =_houseList
    val user:LiveData<User> = _user
    val statusAuth:LiveData<Boolean> = _statusAuth

}