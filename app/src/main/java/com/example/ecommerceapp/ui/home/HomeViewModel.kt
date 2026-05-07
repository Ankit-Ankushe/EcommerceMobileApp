package com.example.ecommerceapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.model.Product
import com.example.ecommerceapp.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(
        val products: List<Product>,
        val categories: List<String>,
        val selectedCategory: String = "All"
    ) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private var allProducts: List<Product> = emptyList()

    init {
        fetchProducts()
    }

    /**
     * Fetches products from the repository.
     * @param isRefresh If true, doesn't show the full screen loading state.
     */
    fun fetchProducts(isRefresh: Boolean = false) {
        if (!isRefresh) {
            _uiState.value = HomeUiState.Loading
        }
        
        viewModelScope.launch {
            if (isRefresh) _isRefreshing.value = true
            
            repository.getProducts().onSuccess { response ->
                allProducts = response.data.products
                val categories = listOf("All") + allProducts.map { it.category }.distinct()
                _uiState.value = HomeUiState.Success(
                    products = allProducts,
                    categories = categories
                )
            }.onFailure { error ->
                _uiState.value = HomeUiState.Error(error.message ?: "Unknown error occurred")
            }
            
            _isRefreshing.value = false
        }
    }

    fun filterByCategory(category: String) {
        val currentState = _uiState.value
        if (currentState is HomeUiState.Success) {
            val filteredProducts = if (category == "All") {
                allProducts
            } else {
                allProducts.filter { it.category == category }
            }
            _uiState.value = currentState.copy(
                products = filteredProducts,
                selectedCategory = category
            )
        }
    }
}
