// ../ui/MainActivity.kt

package com.example.lab7_notifz

import Notification
import NotificationType
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
        var selected by remember { mutableStateOf<NotificationType?>(null) } // null = Todas

        val list = remember(vm.items, selected) {
            selected?.let { t -> vm.items.filter { it.type == t } } ?: vm.items
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Tipos de notificaciones",
                style = MaterialTheme.typography.labelMedium
            )

            NotificationFilter(
                selected = selected,
                onSelectedChange = { selected = it }
            )

            Spacer(Modifier.height(16.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                tonalElevation = 0.dp,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
                    itemsIndexed(list, key = { _, it -> it.id }) { index, item ->
                        Notification(n = item)
                        if (index != list.lastIndex) Divider(thickness = 0.6.dp)
                    }
                }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationFilter(
    selected: NotificationType?,                         // null = Todas
    onSelectedChange: (NotificationType?) -> Unit
) {
    val options: List<Pair<NotificationType?, String>> = listOf(
        null to "Todas",
        NotificationType.GENERAL to "Informativas",
        NotificationType.NEW_MEETING to "Capacitaciones"
    )

    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index, (value, label) ->
            SegmentedButton(
                selected = selected == value,
                onClick = { onSelectedChange(value) },
                shape = SegmentedButtonDefaults.itemShape(index, options.size)
            ) { Text(label) }
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

        Spacer(Modifier.width(12.dp))

        Column() {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = n.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = n.sendAt,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = n.body,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab7notifzTheme {
        NotificationDisplay(Modifier)
    }
}