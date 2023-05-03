package com.monita.productlistapptavant.network

import com.monita.productlistapptavant.model.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

//    https://fakestoreapi.com/products?limit=10
    @GET("/products")
    suspend fun getProduct(@Query("limit")limit:Int): Response<List<Product>>

}