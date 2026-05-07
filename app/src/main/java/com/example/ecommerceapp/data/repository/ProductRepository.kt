package com.example.ecommerceapp.data.repository

import com.example.ecommerceapp.data.model.Product
import com.example.ecommerceapp.data.model.ProductDetailResponse
import com.example.ecommerceapp.data.model.ProductListResponse

interface ProductRepository {
    suspend fun getProducts(): Result<ProductListResponse>
    suspend fun getProductById(id: Int): Result<ProductDetailResponse>
}
