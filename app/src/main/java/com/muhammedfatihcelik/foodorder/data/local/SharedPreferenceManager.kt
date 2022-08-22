package com.muhammedfatihcelik.foodorder.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.muhammedfatihcelik.foodorder.data.entity.common.User
import com.muhammedfatihcelik.foodorder.data.entity.login.LoginResponse

class SharedPreferenceManager(context: Context) {
    companion object {
        const val TOKEN: String = "com.muhammedfatihcelik.TOKEN"
        const val USERID: String = "com.muhammedfatihcelik.USERID"
        const val USER: String = "com.muhammedfatihcelik.USER"
    }

    private val sharedPreference: SharedPreferences = context.getSharedPreferences("sharedPreferencesUtil", Context.MODE_PRIVATE)

    // TOKEN
    fun saveToken(token: String?) {
        sharedPreference.edit().putString(TOKEN, token).apply()
    }

    fun getToken(): String? {
        return sharedPreference.getString(TOKEN, "")
    }

    /*// USERID
    fun setUserId(id: Int ){
        sharedPreference.edit().putInt(USERID, id).apply()
    }

    fun getUserId(): Int {
        return sharedPreference.getInt(USERID, -1)
    }*/

    // USER
    fun setUser(user: User?) {
        var json = Gson().toJson(user)
        sharedPreference.edit().putString(USER, json).apply()
    }

    fun getUser(): String? {
        return sharedPreference.getString(USER, "")
    }
}