package com.example.lab7_notifz

import Notification
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab7_notifz.ui.theme.Lab7notifzTheme
import generateFakeNotifications

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab7notifzTheme {
                NotificationDisplay(
                    modifier = Modifier
                )
            }
        }
    }
}

class NotificationsViewModel : ViewModel() {
    val items: List<Notification> = generateFakeNotifications()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationDisplay(
    modifier: Modifier = Modifier,
    vm: NotificationsViewModel = viewModel()) {
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

@Composable
fun Notification(
    n: Notification
) {
    val icon = when (n.type) {
        NotificationType.GENERAL -> Icons.Outlined.Notifications
        NotificationType.NEW_MEETING -> Icons.Outlined.DateRange
    }

    val badgeColor = when (n.type) {
        NotificationType.GENERAL -> MaterialTheme.colorScheme.tertiaryContainer
        NotificationType.NEW_MEETING -> MaterialTheme.colorScheme.primaryContainer
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Icono de notificación",
            tint = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .size(36.dp)
                .background(badgeColor, CircleShape)
                .padding(5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab7notifzTheme {
        NotificationDisplay(Modifier)
    }
}