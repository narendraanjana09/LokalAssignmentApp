package com.lokalassignmentapp.presentation.productsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lokalassignmentapp.R
import com.lokalassignmentapp.data.remote.dto.Product
import com.lokalassignmentapp.presentation.common.BrandDataItem
import com.lokalassignmentapp.presentation.common.PrimarySnackBar
import com.lokalassignmentapp.presentation.common.SpinningProgress
import com.lokalassignmentapp.presentation.productsScreen.components.HorizontalChipList

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = hiltViewModel(),
    onBrandViewClick: (BrandItem) -> Unit,
    onProductViewClick: (Product,List<Product>) -> Unit,
) {
    val screenState by viewModel.screenState
    val dataState by viewModel.dataState
    val refreshState = rememberPullRefreshState(screenState.loading.second, {
        viewModel.getProducts()
    })
    LaunchedEffect(key1 = Unit) {
        viewModel.getProducts()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.products),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
    ) {paddingValues->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(refreshState)
                .padding(paddingValues)
        ){
            if(screenState.loading.second){
                SpinningProgress(
                    modifier = Modifier
                        .size(25.dp)
                        .align(Alignment.Center)
                )
            }else{
                Column {
                    HorizontalChipList(
                        chipItems = dataState.categories,
                        selectable = true,
                        currentSelection = 0,
                        allowPadding = true,
                        onChipSelected = {index->
                            viewModel.onEvent(ProductsScreenEvents.LoadCategory(dataState.categories[index]))
                        })
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        contentPadding = PaddingValues(horizontal = 20.dp)
                    ) {
                        itemsIndexed(
                            dataState.brandItems
                        ){index, item ->
                            BrandDataItem(
                                brandItem = item,
                                onViewAllClick = {
                                   onBrandViewClick(item)
                                },
                            ) {product->
                                onProductViewClick(product,item.productsList - product)
                            }
                        }
                    }
                }
            }

            if(screenState.message.second){
                when(screenState.message.first){
                    is Int -> PrimarySnackBar(stringResource(id = screenState.message.first as Int))
                    else -> PrimarySnackBar(screenState.message.first as String)
                }
            }
            PullRefreshIndicator(screenState.loading.second, refreshState, Modifier.align(Alignment.TopCenter))
        }
    }
}
