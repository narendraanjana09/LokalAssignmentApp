package com.lokalassignmentapp.domain.usecases

import com.lokalassignmentapp.common.Resource
import com.lokalassignmentapp.data.remote.dto.Products
import com.lokalassignmentapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    fun getProducts() : Flow<Resource<Products>> = flow {
        try {
            emit(Resource.Loading())
            val products = repository.getProducts()
            emit(Resource.Success(products))
        }catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}