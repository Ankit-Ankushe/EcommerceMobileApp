package com.example.ecommerceapp.data.remote

import com.example.ecommerceapp.data.model.ProductDetailResponse
import com.example.ecommerceapp.data.model.ProductListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FreeApiService {
    @GET("public/randomproducts")
    suspend fun getProducts(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 100
    ): ProductListResponse

    @GET("public/randomproducts/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): ProductDetailResponse
}
