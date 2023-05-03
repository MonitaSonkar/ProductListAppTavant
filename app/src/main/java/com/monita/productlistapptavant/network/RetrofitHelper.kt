package com.monita.productlistapptavant.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val BASE_URL="https://fakestoreapi.com"
    fun getInstance():Retrofit
    {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
}