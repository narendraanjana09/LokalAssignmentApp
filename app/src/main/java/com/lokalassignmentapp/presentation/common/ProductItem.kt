package com.lokalassignmentapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokalassignmentapp.R
import com.lokalassignmentapp.common.Util.calculateDiscount
import com.lokalassignmentapp.data.remote.dto.Product


@Composable
fun ProductItem(
    modifier: Modifier,
    product: Product
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        ImageItem(
            modifier = Modifier
                .fillMaxWidth()
                .height(132.dp)
                .clip(RoundedCornerShape(15.dp)),
            image = product.thumbnail
        )
        Column(
            modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
        ) {
            Text(
                text = product.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        ){
                            append("$ " + product.price.calculateDiscount(product.discountPercentage))
                        }
                        append(" ")
                        withStyle(
                            SpanStyle(
                            textDecoration = TextDecoration.LineThrough,
                            fontSize = 14.sp
                        )
                        ){
                            append("${product.price}")
                        }
                    }
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "star",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(text = "${product.rating}")
                }
            }
        }
    }
}