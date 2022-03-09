package com.mark.deeplink_tool.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mark.deeplink_tool.data.model.DeeplinkItem

/**
 * Entry Point for editing a [DeeplinkItem]
 */
@Composable
fun DeeplinkItemEntryInput(
    name: String,
    onNameChange: (String) -> Unit,
    scheme: String,
    onSchemeChange: (String) -> Unit,
    path: String,
    onPathChange: (String) -> Unit,
    currentEditItem: DeeplinkItem,
    onSubmit: (DeeplinkItem) -> Unit,
    onEditDone: () -> Unit,
    errorMessage: String?
) {
    val submit = {
        onSubmit(
            DeeplinkItem(
                currentEditItem.id,
                name,
                scheme,
                path,
                currentEditItem.imageGradient
            )
        )
    }

    InputForm(
        name = name,
        onNameChange = onNameChange,
        scheme = scheme,
        onSchemeChange = onSchemeChange,
        path = path,
        onPathChange = onPathChange,
        errorMessage = errorMessage,
        onSubmit = submit,
        onDismiss = onEditDone
    )

}

/**
 * Entry point for creating *new* [DeeplinkItem]
 */
@Composable
fun DeeplinkItemNewEntryInput(
    name: String,
    onNameChange: (String) -> Unit,
    scheme: String,
    onSchemeChange: (String) -> Unit,
    path: String,
    onPathChange: (String) -> Unit,
    onSubmit: (DeeplinkItem) -> Unit,
    onDismiss: () -> Unit,
    errorMessage: String?,
    imageGradient: List<Color>? = null
) {
    val submit = {
        onSubmit(
            DeeplinkItem(
                name = name,
                scheme = scheme,
                path = path,
                imageGradient = imageGradient
            )
        )
    }

    InputForm(
        name = name,
        onNameChange = onNameChange,
        scheme = scheme,
        onSchemeChange = onSchemeChange,
        path = path,
        onPathChange = onPathChange,
        errorMessage = errorMessage,
        onSubmit = submit,
        onDismiss = onDismiss
    )

}

@Composable
fun InputForm(
    modifier: Modifier = Modifier,
    name: String,
    onNameChange: (String) -> Unit,
    scheme: String,
    onSchemeChange: (String) -> Unit,
    path: String,
    onPathChange: (String) -> Unit,
    errorMessage: String? = null,
    onSubmit: () -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        DeeplinkTextField(
            text = name,
            onTextChange = onNameChange,
            label = "Name",
            placeholder = "Enter Name"
        )

        DeeplinkTextField(
            text = scheme,
            onTextChange = onSchemeChange,
            label = "Scheme",
            placeholder = "Enter App Url Scheme",
            errorMessage = errorMessage
        )

        DeeplinkTextField(
            text = path,
            onTextChange = onPathChange,
            label = "Deeplink",
            placeholder = "Enter Deeplink (Default: Debug)"
        )

        Divider(
            modifier = Modifier
                .padding(top = 8.dp, start = 32.dp, end = 32.dp)
        )
        Box(Modifier.align(CenterHorizontally)) {
            Row {
                OutlinedButton(
                    onClick = onSubmit,
                    modifier = Modifier
                        .width(120.dp)
                ) {
                    Text(text = "Save")
                }
                Spacer(modifier = Modifier.padding(4.dp))
                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .width(120.dp)
                ) {
                    Text(text = "Dismiss")
                }

            }
        }
    }

}

@Composable
fun DeeplinkTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    errorMessage: String? = null
) {
    val isError = !errorMessage.isNullOrEmpty()
    Column {
        OutlinedTextField(
            modifier = modifier,
            value = text,
            onValueChange = { onTextChange(it) },
            label = { Text(label) },
            placeholder = { Text(placeholder) },
            isError = isError
        )
        if (isError) {
            errorMessage?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.error
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun InputFormPreview() {
    InputForm(
        name = "",
        onNameChange = {},
        scheme = "",
        onSchemeChange = {},
        path = "",
        onPathChange = {},
        onDismiss = {},
        onSubmit = {}
    )
}

