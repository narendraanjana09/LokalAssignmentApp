package com.lokalassignmentapp.di

import com.lokalassignmentapp.common.Constants
import com.lokalassignmentapp.data.remote.ProductsApi
import com.lokalassignmentapp.data.repository.ProductRepositoryImpl
import com.lokalassignmentapp.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductApi() : ProductsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        api: ProductsApi
    ):ProductRepository = ProductRepositoryImpl(api)


}