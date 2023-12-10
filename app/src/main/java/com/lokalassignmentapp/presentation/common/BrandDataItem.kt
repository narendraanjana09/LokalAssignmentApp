package com.lokalassignmentapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lokalassignmentapp.R
import com.lokalassignmentapp.data.remote.dto.Product
import com.lokalassignmentapp.presentation.productsScreen.BrandItem

@Composable
fun BrandDataItem(
    brandItem: BrandItem,
    onViewAllClick: () -> Unit,
    onProductClick: (Product) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = brandItem.brandName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = stringResource(id = R.string.view_all),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.clickable {
                    onViewAllClick()
                }
            )
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ){
            itemsIndexed (brandItem.productsList) {index, product ->
                ProductItem(
                    modifier = Modifier
                        .width(250.dp)
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