package com.mark.deeplink_tool.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.mark.deeplink_tool.data.model.DeeplinkItem
import com.mark.deeplink_tool.ui.util.Constant


@Composable
fun DeeplinkCard(
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
fun DeeplinkCardContent(
    deeplink: DeeplinkItem
) {
    var isEditing by remember { mutableStateOf(true) }
    Column {
        Row(
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            LetterImage(
                letter = deeplink.name.first().toString().uppercase(),
                gradient = deeplink.imageGradient
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {

                Text(text = deeplink.name, style = MaterialTheme.typography.h6)

                /*TODO style this surface and fix overall layout*/
                Surface() {
                    Text(
                        text = "${deeplink.scheme}:///${deeplink.path}",
                        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Light)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                modifier = Modifier.requiredWidth(48.dp),
                onClick = { isEditing = !isEditing }
            ) {
                Icon(
                    imageVector = if (isEditing) Icons.Outlined.Close else Icons.Outlined.Edit,
                    contentDescription = "Edit Deeplink",
                    modifier = Modifier.diagonalGradientTint(
                        colors = deeplink.imageGradient,
                        blendMode = BlendMode.Plus
                    )
                )
            }
        }
        var text by remember {
            mutableStateOf("")
        }
        AnimatedVisibility(isEditing) {

            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Name") })
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("App Url Scheme") })
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Link (e.g. debug)") })
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp), thickness = 2.dp
                )
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.align(CenterHorizontally).width(120.dp)
                ) {
                    Text(text = "Save")
                }

            }
        }
    }
}

@Composable
fun LetterImage(letter: String = "?", gradient: List<Color> = Constant.GRADIENT_MEGATRON) {
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
    val test = DeeplinkItem("JEC", "ebjec", "debug", Constant.GRADIENT_MEGATRON)

    DeeplinkCard(onClick = { /*TODO*/ }, shape = RoundedCornerShape(14.dp)) {
        DeeplinkCardContent(test)
    }
}
