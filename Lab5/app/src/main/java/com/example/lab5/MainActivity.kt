@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab5.ui.theme.Lab5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppComida(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AppComida(
    modifier: Modifier = Modifier
) {
    Scaffold(
    containerColor = MaterialTheme.colorScheme.background,
    topBar = {
        TopAppBar(
            title = {},
            navigationIcon = {},
            actions = {},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )
    },

    bottomBar = {
        BottomAppBar(
            actions = {},
            floatingActionButton = {},
            containerColor = MaterialTheme.colorScheme.surface
        )
    }

) { padding -> Column(
    modifier = modifier.padding(padding)
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconoCircular(modifier)

    }

}

    }
}

@Composable
fun IconoCircular(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .background(MaterialTheme.colorScheme.secondary, CircleShape)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.outline_update_24),
            contentDescription = "Actualizar",
            tint = MaterialTheme.colorScheme.surface
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab5Theme {
        AppComida(modifier = Modifier.fillMaxSize())
    }
}