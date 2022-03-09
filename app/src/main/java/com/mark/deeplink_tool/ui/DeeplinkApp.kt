package com.mark.deeplink_tool.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.insets.systemBarsPadding

import com.mark.deeplink_tool.ui.theme.DeeplinktoolTheme
import com.mark.deeplink_tool.R
import com.mark.deeplink_tool.ui.viewmodel.DeeplinkViewModel
import java.lang.Exception
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.mark.deeplink_tool.data.model.DeeplinkItem
import com.mark.deeplink_tool.ui.components.*
import com.mark.deeplink_tool.util.GradientUtil


@Composable
fun DeeplinkApp(deeplinkViewModel: DeeplinkViewModel = viewModel()) {
    val state by deeplinkViewModel.state.collectAsState()
    val context = LocalContext.current

    // test

    DeeplinktoolTheme {
        ProvideWindowInsets {
            Scaffold(
                modifier = Modifier.statusBarsPadding(),
                topBar = {
                    DeeplinkTopBar(
                        onClick = deeplinkViewModel::onOpenDialog,
                        onDelete = deeplinkViewModel::deleteAll
                    )
                },
                snackbarHost = {
                    SnackbarHost(
                        hostState = it,
                        snackbar = { snackbarData -> DeeplinkSnackbar(snackbarData) })
                }
            ) { innerPaddingModifier ->

                if (state.loading) {
                    Box(
                        modifier = Modifier
                            .background(color = Color.Transparent)
                            .padding(innerPaddingModifier)
                            .fillMaxSize()
                            .wrapContentHeight(CenterVertically)
                            .wrapContentWidth(CenterHorizontally)
                            .zIndex(8F)
                    ) {
                        CircularProgressIndicator()
                    }
                }

                AnimatedVisibility(state.isDialogOpen) {
                    ShowDialog(
                        title = "Add New Deeplink",
                        name = state.name,
                        onNameChange = deeplinkViewModel::onNameChange,
                        scheme = state.scheme,
                        onSchemeChange = deeplinkViewModel::onSchemeChange,
                        path = state.path,
                        onPathChange = deeplinkViewModel::onPathChange,
                        onSubmit = deeplinkViewModel::submitDeeplink,
                        onDismiss = deeplinkViewModel::onDismissDialog,
                        errorMessage = state.errorMessage,
                        gradient = state.imageGradient
                    )
                }
                DeeplinkContent(
                    modifier = Modifier
                        .padding(innerPaddingModifier)
                        .navigationBarsPadding(),
                    context = context,
                    deeplinkList = state.deeplinkList,
                    name = state.name,
                    onNameChange = deeplinkViewModel::onNameChange,
                    scheme = state.scheme,
                    onSchemeChange = deeplinkViewModel::onSchemeChange,
                    path = state.path,
                    onPathChange = deeplinkViewModel::onPathChange,
                    currentEditItem = state.currentEditItem,
                    onEditStarted = deeplinkViewModel::onEditItemStarted,
                    onSubmit = deeplinkViewModel::submitEditedDeeplink,
                    onEditDone = deeplinkViewModel::onEditDone,
                    errorMessage = state.errorMessage
                )
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
fun DeeplinkTopBar(
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Deeplink Tool", textAlign = TextAlign.Center)
        },
        navigationIcon = {
            Icon(
                painter = painterResource(R.drawable.deeplink_icon),
                contentDescription = null
            )
        },
        actions = {
            IconButton(onClick = onClick) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add Deeplink")
            }
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete All Deeplink"
                )
            }
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

@Composable
fun ShowDialog(
    title: String,
    name: String,
    onNameChange: (String) -> Unit,
    scheme: String,
    onSchemeChange: (String) -> Unit,
    path: String,
    onPathChange: (String) -> Unit,
    onSubmit: (DeeplinkItem) -> Unit,
    onDismiss: () -> Unit,
    errorMessage: String?,
    gradient: List<Color>?
) {
    PopupDialogForm(
        title = title,
        name = name,
        onNameChange = onNameChange,
        scheme = scheme,
        onSchemeChange = onSchemeChange,
        path = path,
        onPathChange = onPathChange,
        onSubmit = onSubmit,
        onDismiss = onDismiss,
        errorMessage = errorMessage,
        gradient = gradient
    )
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun DeeplinkContent(
    modifier: Modifier = Modifier,
    context: Context,
    deeplinkList: List<DeeplinkItem>,
    name: String,
    onNameChange: (String) -> Unit,
    scheme: String,
    onSchemeChange: (String) -> Unit,
    path: String,
    onPathChange: (String) -> Unit,
    currentEditItem: DeeplinkItem?,
    onEditStarted: (DeeplinkItem) -> Unit,
    onSubmit: (DeeplinkItem) -> Unit,
    onEditDone: () -> Unit,
    errorMessage: String?
) {

    LazyColumn(modifier = modifier) {
        items(deeplinkList) { deeplink ->
            val schemeWithPath = deeplink.scheme + ":///" + deeplink.path
            val uri = Uri.parse(schemeWithPath)
            Card(
                onClick = { launchUri(uri = uri, context = context) },
                shape = RoundedCornerShape(14.dp)
            ) {
                val contentTransition = updateTransition(
                    (currentEditItem?.id == deeplink.id),
                    label = "Expand/Collapse"
                )
                contentTransition.AnimatedContent(
                    transitionSpec = {
                        fadeIn(animationSpec = tween(150, 150)) with
                                fadeOut(animationSpec = tween(150)) using
                                SizeTransform(clip = false) { initialSize, targetSize ->
                                    if (targetState) {
                                        keyframes {
                                            IntSize(initialSize.width, targetSize.height) at 150
                                            durationMillis = 300
                                        }
                                    } else {
                                        keyframes {
                                            IntSize(targetSize.width, targetSize.height) at 150
                                            durationMillis = 300
                                        }

                                    }
                                }
                    }
                ) { state ->
                    if (state) {
                        if (currentEditItem?.id == deeplink.id) {
                            DeeplinkItemEntryInput(
                                name = name,
                                onNameChange = onNameChange,
                                scheme = scheme,
                                onSchemeChange = onSchemeChange,
                                path = path,
                                onPathChange = onPathChange,
                                onSubmit = onSubmit,
                                onEditDone = onEditDone,
                                currentEditItem = currentEditItem!!,
                                errorMessage = errorMessage
                            )
                        }
                    } else {
                        DeeplinkListItem(
                            deeplink = deeplink,
                            onEditStarted = onEditStarted
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DeeplinkListItem(
    deeplink: DeeplinkItem,
    onEditStarted: (DeeplinkItem) -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            LetterImage(
                letter = deeplink.name?.first().toString().uppercase(),
                gradient = deeplink.imageGradient!!
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {

                deeplink.name?.let { Text(text = it, style = MaterialTheme.typography.h6) }

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
                onClick = { onEditStarted(deeplink) }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit Deeplink"
                )
            }
        }
    }
}


@Preview
@Composable
fun DeeplinkTopBarPreview() {
    DeeplinkTopBar(onClick = {}, onDelete = {})
}
