package com.mark.deeplink_tool.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import com.mark.deeplink_tool.data.model.DeeplinkItem

@Composable
fun PopupDialogForm(
    modifier: Modifier = Modifier,
    title: String,
    name: String,
    onNameChange: (String) -> Unit,
    scheme: String,
    onSchemeChange: (String) -> Unit,
    path: String,
    onPathChange: (String) -> Unit,
    errorMessage: String? = null,
    onSubmit: (DeeplinkItem) -> Unit,
    onDismiss: () -> Unit,
    gradient: List<Color>? = null
) {
    CustomDialog(title = title,onDismiss = onDismiss) {
        DeeplinkItemNewEntryInput(
            name = name,
            onNameChange = onNameChange,
            scheme = scheme,
            onSchemeChange = onSchemeChange,
            path = path,
            onPathChange = onPathChange,
            onSubmit = onSubmit,
            onDismiss = onDismiss,
            errorMessage = errorMessage,
            imageGradient = gradient
        )
    }
}

@Composable
fun CustomDialog(
    modifier: Modifier = Modifier,
    title: String = "Title",
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        CustomDialogContent(title = title) {
            content()
        }
    }
}

// Had to Extract the actual content of CustomDialog so I can Preview it
@Composable
private fun CustomDialogContent(
    title: String,
    content: @Composable () -> Unit
) {
    Surface(elevation = 8.dp, shape = RoundedCornerShape(12.dp)) {
        Column(
            modifier = Modifier
                .width(400.dp)
                .wrapContentHeight()
                .background(Color.White)
                .padding(8.dp)
        ) {

            Text(
                text =  title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                content()
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Preview
@Composable
fun CustomDialogPreview() {
    CustomDialogContent("Dialog Title") {
        DeeplinkItemNewEntryInput(
            name = "",
            onNameChange = {},
            scheme = "",
            onSchemeChange = {},
            path = "",
            onPathChange = {},
            onSubmit = {},
            onDismiss = {},
            errorMessage = null
        )
    }
}