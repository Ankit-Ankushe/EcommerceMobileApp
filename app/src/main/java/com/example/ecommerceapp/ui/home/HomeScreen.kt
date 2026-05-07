package com.example.ecommerceapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ecommerceapp.ui.home.components.ProductItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import androidx.compose.animation.*
import androidx.compose.animation.core.*

/**
 * Main screen of the app displaying the product catalog.
 * Handles different UI states: Loading, Success, and Error.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onProductClick: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Ecommerce App",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    ) 
                }
            )
        }
    ) { paddingValues ->
        // SwipeRefresh for pull-to-refresh functionality (Bonus point requirement)
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.fetchProducts(isRefresh = true) },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                when (val state = uiState) {
                    is HomeUiState.Loading -> {
                        // Full screen loading indicator
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                    is HomeUiState.Success -> {
                        // Horizontal list of categories for filtering
                        CategoryFilterRow(
                            categories = state.categories,
                            selectedCategory = state.selectedCategory,
                            onCategorySelected = { viewModel.filterByCategory(it) }
                        )
                        
                        // Adaptive grid to display products
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(8.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(state.products, key = { it.id }) { product ->
                                // Subtle entrance animation for each product item
                                AnimatedVisibility(
                                    visible = true,
                                    enter = fadeIn(animationSpec = tween(500)) + 
                                            slideInVertically(initialOffsetY = { 50 })
                                ) {
                                    ProductItem(
                                        product = product,
                                        onProductClick = onProductClick
                                    )
                                }
                            }
                        }
                    }
                    is HomeUiState.Error -> {
                        // Error message with a retry option
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "Error: ${state.message}", color = MaterialTheme.colorScheme.error)
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(onClick = { viewModel.fetchProducts() }) {
                                    Text("Retry")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryFilterRow(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                selected = selectedCategory == category,
                onClick = { onCategorySelected(category) },
                label = { Text(category.replaceFirstChar { it.uppercase() }) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}
