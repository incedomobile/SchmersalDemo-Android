package com.schmersaldemo.mango.network

import com.schmersaldemo.mango.settings.UrlConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by puneet.bahuguna
 * Description: This apiclient class is responsible to get an instance of base url for remote network calls.
 */
object ApiClient {

    val client: Retrofit
        get() = retrofitClient
    private val retrofitClient: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(UrlConfig().BASE_URL)
            .client(OkHttpClient().newBuilder().connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}