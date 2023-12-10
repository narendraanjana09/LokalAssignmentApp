package com.lokalassignmentapp.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun PrimarySnackBar(
  text: String = "",
  modifier: Modifier = Modifier.fillMaxWidth(),
  backgroundColor: Color = MaterialTheme.colorScheme.background,
  contentColor: Color = MaterialTheme.colorScheme.onBackground,
) {

  var visible by remember {
    mutableStateOf(false)
  }
  LaunchedEffect(key1 = text){
    visible = text.isNotEmpty()
  }


  Box(modifier = Modifier
    .fillMaxSize()
    .padding(horizontal = 20.dp, vertical = 10.dp),
    contentAlignment = Alignment.BottomCenter
  )
  {
    AnimatedVisibility(
      visible = visible,
      enter = slideInVertically(tween(durationMillis = 300)) {
        it
      },
      exit = slideOutVertically (tween(durationMillis = 300)) {
        it
      }
    ) {
      TextCard(modifier, backgroundColor, contentColor, text)
    }
  }
}

@Composable
private fun TextCard(
  modifier: Modifier,
  backgroundColor: Color,
  contentColor: Color,
  text: String,
) {
  Card(
    modifier = modifier,
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(
      containerColor = backgroundColor,
      contentColor = contentColor
    ),
    elevation = CardDefaults.cardElevation(
      defaultElevation = 10.dp
    )
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
    ) {
      Text(text = text, color = contentColor, modifier = Modifier.weight(0.8f))
    }
  }
}