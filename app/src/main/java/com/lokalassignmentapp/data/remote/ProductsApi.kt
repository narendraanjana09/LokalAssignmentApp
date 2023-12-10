package com.lokalassignmentapp.data.remote

import com.lokalassignmentapp.data.remote.dto.Products
import retrofit2.http.GET

interface ProductsApi {

    @GET("products")
    suspend fun getProducts() : Products
}