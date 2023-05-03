package com.monita.productlistapptavant.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.monita.productlistapptavant.model.Product
import com.monita.productlistapptavant.network.ProductService

class Repository(val productService: ProductService) {

    val productLivedata=MutableLiveData<List<Product>>()
    val product:LiveData<List<Product>>
        get() = productLivedata

    suspend fun getProduct(page:Int)
    {
        val result=productService.getProduct(page)
        if(result?.body()!=null)
        {
            productLivedata.postValue(result.body())
        }
    }
}