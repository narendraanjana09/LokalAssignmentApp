package com.lokalassignmentapp.presentation.productDetailScreen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.lokalassignmentapp.presentation.common.ImageItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalImagesPager(
    modifier: Modifier,
    images:List<String>
) {
    val pagerState = rememberPagerState {
        images.size
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        HorizontalPager(
            modifier = modifier,
            state = pagerState,
        ) {index->
            ImageItem(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
                    .clip(RoundedCornerShape(10.dp)),
                image = images[index],
                contentScale = ContentScale.Fit
            )
        }
        ListIndicator(
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 20.dp)
                .animateContentSize(),
            totalItems = images.size,
            currentSelected = pagerState.currentPage
        )
    }
}

@Composable
fun ListIndicator(
    modifier: Modifier,
    totalItems: Int,
    currentSelected: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        for(i in 0 until totalItems){
            Box(
                modifier = Modifier
                    .animateContentSize()
                    .height(5.dp)
                    .width(if(i == currentSelected) 15.dp else 5.dp)
                    .background(
                        color = if(i == currentSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(15.dp)
                    )
            )
        }
    }
}
