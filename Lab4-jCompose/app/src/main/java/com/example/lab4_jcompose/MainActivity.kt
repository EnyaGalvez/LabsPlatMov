package com.example.lab4_jcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        modifier = modifier
            .fillMaxSize()
            .border(
                width = 6.dp,
                color = Color(18, 110, 34)
            )
    ) {
        Column(
            modifier = modifier
                .padding(
                    vertical = 20.dp
                )
        ) {
            Titulo(
                titleText = "Universidad del Valle de Guatemala",
                weight = FontWeight.Bold,
                phorizontal = 50
            )
            Titulo(
                titleText = "Programación de plataformas móviles, Sección 30",
                weight = FontWeight.Normal,
                phorizontal = 10
            )
        }
    }
}

@Composable
fun Titulo(
    titleText: String,
    modifier: Modifier = Modifier,
    weight: FontWeight,
    phorizontal: Int
) {
    Text(
        text = titleText,
        modifier = modifier.padding(horizontal = phorizontal.dp, vertical = 4.dp),
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        fontWeight = weight
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