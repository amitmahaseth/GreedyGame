package com.example.greedygame.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.greedygame.network.ApiInterFace
import com.example.greedygame.network.ServiceClient
import com.example.greedygame.pojo.AlbumResponse
import com.example.greedygame.utils.Extension

class AlbumVM : ViewModel() {
    val apiInterface = ServiceClient.apiClient().create(ApiInterFace::class.java)
    var errorLiveDataForAlbumList = MutableLiveData<String>()
    suspend fun getAlbumList(
        method: String,
        tag: String,
        api_key: String,
        format: String
    ): LiveData<AlbumResponse> {
        val mutableLiveData = MutableLiveData<AlbumResponse>()
        try {
            val response = apiInterface.getHomeAlbum(method, tag, api_key, format)
            if (response.code() == 200) {
                mutableLiveData.postValue(response.body())
            } else {
                errorLiveDataForAlbumList.value = Extension.errorMessage(response.errorBody()!!)

            }
            return mutableLiveData
        } catch (e: Exception) {
            Log.d("ewshfikhegiu", e.message.toString())
        }

        return mutableLiveData
    }
}