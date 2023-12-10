package com.lokalassignmentapp.domain.repository

import com.lokalassignmentapp.data.remote.dto.Product
import com.lokalassignmentapp.data.remote.dto.Products

interface ProductRepository {

    suspend fun getProducts(): Products

}