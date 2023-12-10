package com.lokalassignmentapp.presentation.productsScreen

sealed class ProductsScreenEvents {
    data class LoadCategory(val category: String):ProductsScreenEvents()
}