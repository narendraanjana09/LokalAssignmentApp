package com.lokalassignmentapp.presentation

sealed class Screen(val route: String){
    object ProductsScreen: Screen("products_screen")
    object BranProductsScreen: Screen("brand_products_screen")
    object ProductDetailScreen: Screen("product_detail_screen")
}
