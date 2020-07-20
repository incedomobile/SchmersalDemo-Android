package com.schmersaldemo.mango.network

/**
 * Created by puneet.bahuguna
 * Author:Puneet Bahuguna
 * Description: Interface methods defined for retrofit callabcks
 */

interface ResponseListener<T> {
    fun onSuccess(requestCode: Int, response: Any?)
    fun onError(requestCode: Int, response: Any?)
    fun onFailure(requestCode: Int, thr: Throwable)
}
