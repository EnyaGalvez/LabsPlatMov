package com.example.lab4_jcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab4_jcompose.ui.theme.Lab4jComposeTheme
import androidx.compose.ui.unit.dp

// Main
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab4jComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Portada(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Composables
@Composable
fun Portada(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(
                width = 6.dp,
                color = Color(18, 110, 34)
            )
    )
}

// Preview
@Preview(showBackground = true)
@Composable
fun PortadaPreview() {
    Lab4jComposeTheme {
        Portada(modifier = Modifier.fillMaxSize())
    }
}