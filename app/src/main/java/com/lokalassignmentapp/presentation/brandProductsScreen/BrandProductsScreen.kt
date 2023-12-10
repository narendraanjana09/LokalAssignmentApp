package com.lokalassignmentapp.presentation.brandProductsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lokalassignmentapp.R
import com.lokalassignmentapp.data.remote.dto.Product
import com.lokalassignmentapp.presentation.productsScreen.BrandItem
import com.lokalassignmentapp.presentation.common.ProductItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BrandProductsScreen(
    brandItem: BrandItem,
    onProductClick: (Product) -> Unit,
    onNavigateBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = brandItem.brandName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBackClick) {
                        Icon(painter = painterResource(id = R.drawable.ic_back_arrow), contentDescription = "back arrow")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    ) { paddingValues->
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            maxItemsInEachRow = 2,
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(20.dp).verticalScroll(rememberScrollState())
        ) {
            brandItem.productsList.forEachIndexed { index, product ->
               ProductItem(
                   modifier = Modifier
                       .weight(1f)
                       .clip(RoundedCornerShape(15.dp))
                       .background(
                           MaterialTheme.colorScheme.surfaceVariant
                       )
                       .clickable {
                           onProductClick(product)
                       },
                   product = product
               )
           }
        }
    }
}