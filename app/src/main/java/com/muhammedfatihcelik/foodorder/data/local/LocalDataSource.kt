package com.muhammedfatihcelik.foodorder.data.local

import com.google.gson.Gson
import com.muhammedfatihcelik.foodorder.data.entity.common.User
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    val sharedPreferenceManager: SharedPreferenceManager
) {
    fun saveToken(token: String?) {
        sharedPreferenceManager.saveToken(token = token)
    }
    fun getToken(): String? {
        return sharedPreferenceManager.getToken()
    }

    /*fun setUserId(id: Int) {
        sharedPreferenceManager.setUserId(id = id)
    }

    fun getUserId(): Int {
        return sharedPreferenceManager.getUserId()
    }*/

    fun setUser(user: User?) {
        sharedPreferenceManager.setUser(user = user)
    }

    fun getUser(): User? {
        return Gson().fromJson(sharedPreferenceManager.getUser(), User::class.java)
    }
}