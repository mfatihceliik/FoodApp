package com.muhammedfatihcelik.foodorder.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muhammedfatihcelik.foodorder.data.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import android.os.Handler

@HiltViewModel
class SplashViewModel @Inject constructor(
    val apiRepository: ApiRepository
): ViewModel() {

    private var navigationLiveData = MutableLiveData<String>()

    fun observeLiveData(): LiveData<String> = navigationLiveData

    fun checkTokenAndNavigate() {
        Handler().postDelayed({
            /*if(!apiRepository.checkToken().isNullOrEmpty()) {
                navigationLiveData.value = "HOME"
            }else {
                navigationLiveData.value = "LOGIN"
            }*/
            navigationLiveData.value = "LOGIN"
        },1500)
    }
}