package com.mark.deeplink_tool.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.insets.systemBarsPadding
import com.mark.deeplink_tool.ui.components.DeeplinkCard
import com.mark.deeplink_tool.ui.components.DeeplinkCardContent
import com.mark.deeplink_tool.ui.components.DeeplinkSnackbar

import com.mark.deeplink_tool.ui.theme.DeeplinktoolTheme
import com.mark.deeplink_tool.ui.util.SampleData
import com.mark.deeplink_tool.R
import java.lang.Exception


@Composable
fun DeeplinkApp() {
    DeeplinktoolTheme {
        ProvideWindowInsets {
            Scaffold(
                modifier = Modifier.statusBarsPadding(),
                topBar = { DeeplinkTopBar() },
                floatingActionButton = { DeeplinkFab(onClick = {/* TODO */ }) },
                snackbarHost = {
                    SnackbarHost(
                        hostState = it,
                        snackbar = { snackbarData -> DeeplinkSnackbar(snackbarData) })
                }
            ) { innerPaddingModifier ->
                DeeplinkContent(modifier = Modifier.padding(innerPaddingModifier))
            }
        }
    }
}

@SuppressLint("QueryPermissionsNeeded")
fun launchUri(uri: Uri, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, uri)
    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        "No Activity Found"
    }
}

@Composable
fun DeeplinkTopBar() {
    TopAppBar(
        title = {
            Text(text = "Deeplink Tool", textAlign = TextAlign.Center)
        },
        navigationIcon = {
            Icon(
                painter = painterResource(R.drawable.deeplink_icon),
                contentDescription = null
            )
        }
    )
}

@Composable
fun DeeplinkFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(onClick = onClick, modifier = modifier.systemBarsPadding()) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Deeplink")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeeplinkContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val deeplinkList = SampleData.deeplinkList
    LazyColumn {
        items(deeplinkList) { deeplink ->
            val schemeWithPath = deeplink.scheme + ":///" + deeplink.path
            val uri = Uri.parse(schemeWithPath)
            DeeplinkCard(
                onClick = { launchUri(uri = uri, context = context) },
                shape = RoundedCornerShape(14.dp)
            ) {
                DeeplinkCardContent(deeplink = deeplink)
            }
        }
    }
}

@Preview
@Composable
fun DeeplinkTopBarPreview() {
    DeeplinkTopBar()
}
