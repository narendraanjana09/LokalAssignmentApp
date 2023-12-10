package com.lokalassignmentapp.presentation.productsScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalChipList(
    modifier: Modifier = Modifier,
    chipItems: List<String>,
    currentSelection: Int = 0,
    selectable: Boolean = true,
    allowPadding: Boolean = true,
    onChipSelected: (Int) -> Unit
) {

    var selectedChip by remember {
        mutableStateOf(currentSelection)
    }

    LaunchedEffect(key1 = currentSelection) {
        selectedChip = currentSelection
    }

    LaunchedEffect(key1 = Unit) {
        if(selectable && chipItems.isNotEmpty()){
            onChipSelected(currentSelection)
        }
    }

    LazyRow(
        modifier = modifier,
        contentPadding = if(allowPadding) PaddingValues(horizontal = 20.dp, vertical = 10.dp) else PaddingValues(),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ){
        itemsIndexed(chipItems){ index, item ->
            PrimaryChip(
                text = item,
                isSelected = if(selectable){ selectedChip == index } else false,
                onItemClick = {
                    if(selectable){
                        selectedChip = index
                    }
                    onChipSelected(index)
                }
            )
        }
    }
}