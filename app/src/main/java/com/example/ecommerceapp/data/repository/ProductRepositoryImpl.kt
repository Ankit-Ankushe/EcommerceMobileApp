package com.example.ecommerceapp.data.repository

import com.example.ecommerceapp.data.model.ProductDetailResponse
import com.example.ecommerceapp.data.model.ProductListResponse
import com.example.ecommerceapp.data.remote.FreeApiService
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: FreeApiService
) : ProductRepository {

    override suspend fun getProducts(): Result<ProductListResponse> {
        return try {
            val response = apiService.getProducts()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getProductById(id: Int): Result<ProductDetailResponse> {
        return try {
            val response = apiService.getProductById(id)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
