package com.example.ecommerceapp.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.util.Log
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ecommerceapp.data.model.Product

/**
 * A card component representing a single product in the catalog.
 * Displays thumbnail, title, price, and stock status.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    product: Product,
    onProductClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onProductClick(product.id) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                coil.compose.SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product.thumbnail)
                        .crossfade(true)
                        .listener(
                            onError = { _, result ->
                                Log.e("CoilError", "Failed to load: ${product.thumbnail}, error: ${result.throwable}")
                            }
                        )
                        .build(),
                    contentDescription = product.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    loading = {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp))
                        }
                    },
                    error = {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.BrokenImage,
                                contentDescription = "Error",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )
                
                // Discount Badge
                Surface(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopEnd),
                    color = MaterialTheme.colorScheme.error,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "-${product.discountPercentage}%",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$${product.price}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.ExtraBold
                    )
                    
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "⭐ ${product.rating}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                
                StockIndicator(stock = product.stock)
            }
        }
    }
}

/**
 * A visual indicator for product stock levels.
 * Color-coded: Green (High), Orange (Low), Red (Out of Stock).
 */
@Composable
fun StockIndicator(stock: Int) {
    val color = when {
        stock > 20 -> Color(0xFF4CAF50) // Green
        stock > 0 -> Color(0xFFFF9800) // Orange
        else -> Color(0xFFF44336) // Red
    }
    
    val text = when {
        stock > 20 -> "In Stock ($stock)"
        stock > 0 -> "Low Stock ($stock left)"
        else -> "Out of Stock"
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(RoundedCornerShape(50))
                .background(color)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = color,
            fontWeight = FontWeight.SemiBold
        )
    }
}
