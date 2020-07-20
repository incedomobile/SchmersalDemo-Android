package com.schmersaldemo.mango.network

import com.schmersaldemo.mango.data.entity.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
@Author:Puneet Bahuguna
@Year:2020
@Description:Created by puneet.bahuguna. Common class for receiving retrofit claabacks event
 */

class CallsHandler(private val responseListener: ResponseListener<*>) {
    fun <T> callRequest(call: Call<T>, i: Int) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    responseListener.onSuccess(i, response.body())
                } else {
                    responseListener.onError(i, response.message())
                }
            }
            override fun onFailure(call: Call<T>, t: Throwable) {
                responseListener.onFailure(i, t)
            }
        })
    }
}


