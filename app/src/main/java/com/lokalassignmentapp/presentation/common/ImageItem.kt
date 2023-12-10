package com.lokalassignmentapp.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.lokalassignmentapp.presentation.common.SpinningProgress
import kotlinx.coroutines.Dispatchers

@Composable
fun ImageItem(
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    image: String) {
    var imageLoaded by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = contentScale,
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    image
                )
                .dispatcher(Dispatchers.Default)
                .memoryCacheKey(image)
                .diskCacheKey(image)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
                .listener(
                    onStart = {
                        imageLoaded = false
                    },
                    onSuccess = { request, result ->
                        imageLoaded = true
                    }
                )
                .build(),
            contentDescription = "Image",
        )
        if(!imageLoaded){
            SpinningProgress(
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
