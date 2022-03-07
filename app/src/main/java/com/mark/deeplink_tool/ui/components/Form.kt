package com.mark.deeplink_tool.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mark.deeplink_tool.data.model.DeeplinkItem
import kotlinx.coroutines.flow.SharingStarted

@Composable
fun DeeplinkItemEntryInput(
    onNameChange: (String) -> Unit,
    onSchemeChange: (String) -> Unit,
    onPathChange: (String) -> Unit,
    currentEditItem: DeeplinkItem,
    onSubmit: (DeeplinkItem) -> Unit
) {

    val submit = {
        onSubmit(currentEditItem)
    }

    InputForm(
        name = currentEditItem.name!!,
        onNameChange = onNameChange,
        scheme = currentEditItem.scheme,
        onSchemeChange = onSchemeChange,
        path = currentEditItem.path,
        onPathChange = onPathChange,
        onSubmit = submit
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
    onSubmit: () -> Unit
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        DeeplinkTextField(
            modifier = modifier,
            text = name,
            onTextChange = onNameChange,
            label = "Name",
            placeholder = "Input Name"
        )

        DeeplinkTextField(
            modifier = modifier,
            text = scheme,
            onTextChange = onSchemeChange,
            label = "Scheme",
            placeholder = "Enter App Url Scheme"
        )

        DeeplinkTextField(
            modifier = modifier,
            text = path,
            onTextChange = onPathChange,
            label = "Deeplink",
            placeholder = "Input Deeplink (Default: Debug)"
        )

        Divider(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp), thickness = 2.dp
        )
        OutlinedButton(
            onClick = onSubmit,
            modifier = Modifier
                .align(CenterHorizontally)
                .width(120.dp)
        ) {
            Text(text = "Save")
        }
    }

}

@Composable
fun DeeplinkTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    label: String,
    placeholder: String = ""
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = { onTextChange(it) },
        label = { Text(label) },
        placeholder = { Text(placeholder) }
    )
}

