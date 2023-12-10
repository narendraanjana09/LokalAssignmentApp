package com.lokalassignmentapp.presentation.productsScreen

import com.lokalassignmentapp.data.remote.dto.Product

data class DataState(
    val productsList:List<Product> = emptyList(),
    val categories:List<String> = emptyList(),
    val brandItems:List<BrandItem> = emptyList()
)

data class BrandItem(
    val brandName:String = "",
    val productsList:List<Product> = emptyList()
)
