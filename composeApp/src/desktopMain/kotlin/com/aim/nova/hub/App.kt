package com.aim.nova.hub

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.aim.nova.hub.navigation.AppNavHost
import novahub.composeapp.generated.resources.Res
import novahub.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() = MaterialTheme {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(Res.string.app_name)) })
        }
    ) {
        AppNavHost()
    }
}