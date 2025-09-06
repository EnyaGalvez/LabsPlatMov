package com.example.lab7_notifz

import Notification
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.lab7_notifz.ui.theme.Lab7notifzTheme
import generateFakeNotifications

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab7notifzTheme {
                Notifications(
                    modifier = Modifier
                )
            }
        }
    }
}

class NotificationsViewModel : ViewModel() {
    var items by mutableStateOf<List<Notification>>(emptyList())
        private set

    init { items = generateFakeNotifications() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notifications(
    modifier: Modifier = Modifier,
    vm: NotificationsViewModel = androidx.lifecycle.viewmodel.compose.viewModel() ) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        text = "Notificaciones",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Tipos de notificaciones",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab7notifzTheme {
        Notifications(Modifier)
    }
}