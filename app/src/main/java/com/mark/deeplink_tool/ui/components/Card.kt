package com.mark.deeplink_tool.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.mark.deeplink_tool.util.GradientUtil


@Composable
fun Card(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    onClick: () -> Unit,
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier
        .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
        .shadow(elevation = elevation, shape = shape)
        .zIndex(elevation.value)
        .background(color = Color.White, shape = shape)
        .clip(shape)
        .clickable { onClick() }

    ) {
        content()
    }
}



@Composable
fun LetterImage(letter: String = "?", gradient: List<Color> = GradientUtil.GRADIENT_MEGATRON) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .offsetGradientBackground(
                colors = gradient,
                135f,
                0f
            )
    ) {
        Text(
            text = letter,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DeeplinkCardPreview() {
    Card(onClick = {}) {
    }
}
