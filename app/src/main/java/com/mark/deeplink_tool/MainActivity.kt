package com.mark.deeplink_tool


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mark.deeplink_tool.data.LocalDataSource
import com.mark.deeplink_tool.data.database.DeeplinkDatabase
import com.mark.deeplink_tool.data.repository.DeeplinkRepository
import com.mark.deeplink_tool.ui.DeeplinkApp
import com.mark.deeplink_tool.viewmodel.DeeplinkViewModel

class MainActivity : ComponentActivity() {
    lateinit var deeplinkViewModel: DeeplinkViewModel
    val database by lazy { DeeplinkDatabase.getDatabase(this) }
    val localDataSource by lazy { LocalDataSource(database.deeplinkItemDao()) }
    val repository by lazy { DeeplinkRepository(localDataSource) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            deeplinkViewModel = viewModel(factory = DeeplinkViewModel.provideFactory(repository))
            DeeplinkApp(deeplinkViewModel)
        }
    }

}




