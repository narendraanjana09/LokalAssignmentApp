package com.lokalassignmentapp.presentation.productsScreen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lokalassignmentapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryChip(text: String, isSelected: Boolean, onItemClick: () -> Unit) {

    ElevatedSuggestionChip(
        onClick = onItemClick,
        icon = {
            getIcon(text)?.let {
                Icon(painter = painterResource(id = it), contentDescription = null)
            }
        },
        label = {
            Text(
                text = text.uppercase(),
                modifier = Modifier.padding(end = 10.dp, top = 10.dp, bottom = 10.dp),
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        border = SuggestionChipDefaults.suggestionChipBorder(
            borderWidth = 0.dp,
            borderColor = Color.Transparent
        ),
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = if(isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            labelColor = if(isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
            iconContentColor = if(isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
        )
    )
}

fun getIcon(text: String): Int? {
   return when(text.lowercase()){
       "smartphones" -> R.drawable.ic_phone
       "laptops" -> R.drawable.ic_laptop
       "fragrances" -> R.drawable.ic_fragnence
       "skincare" -> R.drawable.ic_skincare
       "groceries" -> R.drawable.ic_groceries
       "home-decoration" -> R.drawable.ic_decor
       else -> null
    }
}
