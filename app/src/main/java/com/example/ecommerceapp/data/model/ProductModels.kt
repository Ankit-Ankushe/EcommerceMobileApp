package com.example.ecommerceapp.data.model

import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("data") val data: ProductDataWrapper,
    @SerializedName("message") val message: String,
    @SerializedName("success") val success: Boolean
)

data class ProductDetailResponse(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("data") val data: Product,
    @SerializedName("message") val message: String,
    @SerializedName("success") val success: Boolean
)

data class ProductDataWrapper(
    @SerializedName("page") val page: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("totalItems") val totalItems: Int,
    @SerializedName("data") val products: List<Product>
)

data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Double,
    @SerializedName("discountPercentage") val discountPercentage: Double,
    @SerializedName("rating") val rating: Double,
    @SerializedName("stock") val stock: Int,
    @SerializedName("brand") val brand: String,
    @SerializedName("category") val category: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("images") val images: List<String>
)
