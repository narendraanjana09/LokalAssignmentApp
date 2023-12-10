package com.lokalassignmentapp.presentation.productDetailScreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokalassignmentapp.R
import com.lokalassignmentapp.common.Util.calculateDiscount
import com.lokalassignmentapp.data.remote.dto.Product
import com.lokalassignmentapp.presentation.common.ProductItem
import com.lokalassignmentapp.presentation.productDetailScreen.components.CountWithIconItem
import com.lokalassignmentapp.presentation.productDetailScreen.components.HorizontalImagesPager

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ProductDetailScreen(
    product: Product,
    similarProducts: List<Product>,
    onProductClick: (Product) -> Unit,
    onNavigateBackClick: () -> Unit
) {
    BackHandler {
        onNavigateBackClick()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = product.title,
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            item {
                HorizontalImagesPager(
                    modifier = Modifier
                        .aspectRatio(16f/14f),
                    images = product.images
                )
            }
            item {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 20.dp, vertical = 10.dp),
                        color = contentColorFor(MaterialTheme.colorScheme.secondaryContainer),
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp
                                )
                            ){
                                append("$ " + product.price.calculateDiscount(product.discountPercentage))
                            }
                            append(" ")
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            ){
                                append("${product.discountPercentage}%")
                            }
                            append("  ")
                            withStyle(
                                SpanStyle(
                                    textDecoration = TextDecoration.LineThrough,
                                    fontSize = 16.sp
                                )
                            ){
                                append("${product.price}")
                            }
                        }
                    )
                    Text(
                        text = product.description,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Light
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                        CountWithIconItem(
                            text = "${product.rating}",
                            icon = R.drawable.ic_star
                        )
                        CountWithIconItem(
                            text = "${product.stock}",
                            icon = R.drawable.ic_stock,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        SuggestionChip(
                            onClick = { },
                            label = {
                                Text(text = product.category)
                            }
                        )
                        SuggestionChip(
                            onClick = { },
                            label = {
                                Text(text = product.brand)
                            }
                        )
                    }
                }
            }
            if (similarProducts.isNotEmpty()) {
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Divider(
                            thickness = 1.dp,
                            color = MaterialTheme.colorScheme.outline
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.similar),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            maxItemsInEachRow = 2,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            similarProducts.forEachIndexed { index, product ->
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
            }
        }
    }
}