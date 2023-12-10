package com.lokalassignmentapp.data.repository

import com.lokalassignmentapp.data.remote.ProductsApi
import com.lokalassignmentapp.data.remote.dto.Product
import com.lokalassignmentapp.data.remote.dto.Products
import com.lokalassignmentapp.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api:ProductsApi
):ProductRepository {
    override suspend fun getProducts(): Products {
        return api.getProducts()
    }
}