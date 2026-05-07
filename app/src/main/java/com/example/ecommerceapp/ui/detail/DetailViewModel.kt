package com.example.ecommerceapp.ui.detail

import androidx.lifecycle.SavedStateHandle
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

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val product: Product) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: ProductRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    private val productId: Int = checkNotNull(savedStateHandle["productId"])

    init {
        fetchProductDetail()
    }

    fun fetchProductDetail() {
        _uiState.value = DetailUiState.Loading
        viewModelScope.launch {
            repository.getProductById(productId).onSuccess { response ->
                _uiState.value = DetailUiState.Success(response.data)
            }.onFailure { error ->
                _uiState.value = DetailUiState.Error(error.message ?: "Unknown error occurred")
            }
        }
    }
}
