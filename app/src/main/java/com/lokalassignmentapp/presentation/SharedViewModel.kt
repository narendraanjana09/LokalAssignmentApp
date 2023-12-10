package com.lokalassignmentapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lokalassignmentapp.data.remote.dto.Product
import com.lokalassignmentapp.presentation.productsScreen.BrandItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() :ViewModel() {

    private val _brandItem = mutableStateOf<BrandItem?>(null)
    val brandItem: State<BrandItem?> = _brandItem

    private val _productItem = mutableStateOf<List<Pair<Product,List<Product>>>>(emptyList())
    val productItem: State<List<Pair<Product,List<Product>>>> = _productItem

    fun updateBrandItem(brandItem: BrandItem){
        _brandItem.value = brandItem
    }

    fun addProductItem(product: Product, similarProductsList:List<Product>){
        _productItem.value = productItem.value.plus(product to similarProductsList)
    }

    fun removeLastProductItem() {
        val list = productItem.value.toMutableList()
        list.removeAt(list.size-1)
        _productItem.value = list
    }

}