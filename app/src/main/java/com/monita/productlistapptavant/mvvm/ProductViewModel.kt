package com.monita.productlistapptavant.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monita.productlistapptavant.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(val repo:Repository) : ViewModel(){

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getProduct(10)
        }

    }
    val product:LiveData<List<Product>>
        get() = repo.product

    val favproductLivedata= MutableLiveData<List<Product>>()
    val favarray= ArrayList<Product>()

    fun updateFav( product: Product)
    {
        favarray.add(product)
        favproductLivedata.postValue(favarray)
    }
    fun removeFav( product: Product)
    {
        favarray.remove(product)
        favproductLivedata.postValue(favarray)
    }

    val favproductList:LiveData<List<Product>>
        get() = favproductLivedata
}