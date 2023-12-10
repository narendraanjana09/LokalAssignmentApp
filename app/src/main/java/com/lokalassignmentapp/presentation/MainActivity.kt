package com.lokalassignmentapp.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lokalassignmentapp.presentation.brandProductsScreen.BrandProductsScreen
import com.lokalassignmentapp.presentation.productDetailScreen.ProductDetailScreen
import com.lokalassignmentapp.presentation.productsScreen.ProductsScreen
import com.lokalassignmentapp.presentation.ui.theme.LokalAssignmentAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            LokalAssignmentAppTheme {
                val sharedViewModel: SharedViewModel = hiltViewModel()
                val navController = rememberNavController()
                Surface(
                    contentColor = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ProductsScreen.route
                    ) {
                        composable(
                            route = Screen.ProductsScreen.route
                        ) {
                            ProductsScreen(
                                onBrandViewClick = { brandItem ->
                                    sharedViewModel.updateBrandItem(brandItem)
                                    navController.navigate(Screen.BranProductsScreen.route)
                                },
                                onProductViewClick = { product, similarProducts ->
                                    sharedViewModel.addProductItem(product, similarProducts)
                                    navController.navigate(Screen.ProductDetailScreen.route)
                                }
                            )
                        }
                        composable(
                            route = Screen.BranProductsScreen.route,
                        ) {
                            val brandItem by sharedViewModel.brandItem
                            if (brandItem == null) {
                                navController.navigateUp()
                            } else {
                                BrandProductsScreen(
                                    brandItem = brandItem!!,
                                    onProductClick = { product ->
                                        sharedViewModel.addProductItem(
                                            product,
                                            brandItem!!.productsList - product
                                        )
                                        navController.navigate(Screen.ProductDetailScreen.route)
                                    },
                                    onNavigateBackClick = {
                                        navController.navigateUp()
                                    }
                                )
                            }
                        }
                        composable(
                            route = Screen.ProductDetailScreen.route
                        ) {
                            val productItems by sharedViewModel.productItem
                            if(productItems.isEmpty()){
                                return@composable
                            }
                            ProductDetailScreen(
                                product = productItems.last().first,
                                similarProducts = productItems.last().second,
                                onProductClick = { product ->
                                    sharedViewModel.addProductItem(
                                        product,
                                        productItems.last().second - product + productItems.last().first
                                    )
                                    navController.navigate(Screen.ProductDetailScreen.route)
                                },
                                onNavigateBackClick = {
                                    navController.navigateUp()
                                    sharedViewModel.removeLastProductItem()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
