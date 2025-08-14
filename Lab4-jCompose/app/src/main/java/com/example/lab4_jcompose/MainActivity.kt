package com.example.lab4_jcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
                width = 4.dp,
                color = Color(18, 110, 34)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .padding(
                    vertical = 25.dp,
                    horizontal = 6.dp
                ),
            verticalArrangement = Arrangement.Center

        ) {
            CenteredText(
                titleText = "Universidad del Valle de Guatemala",
                textStyle = MaterialTheme.typography.headlineMedium,
                weight = FontWeight.Bold,
                phorizontal = 45
            )

            CenteredText(
                titleText = "Programación de plataformas móviles, Sección 30",
                textStyle = MaterialTheme.typography.headlineSmall,
                weight = FontWeight.Normal,
                phorizontal = 1
            )

            Info(
                subtitle = "integrantes",
                content = "Javier Chávez\nAxel Cruz\nEnya Gálvez"
            )

            Info(
                subtitle = "catedrático",
                content = "Juan Carlos Durini"
            )

            CenteredText(
                titleText = "Enya Gálvez\n24693",
                textStyle = MaterialTheme.typography.bodyLarge,
                weight = FontWeight.Normal,
                phorizontal = 45
            )
        }
        Image(
            painter = painterResource(R.drawable.logouvg),
            contentDescription = "Logo UVG",
            modifier = modifier
                .padding(horizontal = 100.dp)
                .alpha(0.2f)
        )
    }
}

@Composable
fun CenteredText(
    titleText: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle,
    weight: FontWeight,
    phorizontal: Int
) {
    Text(
        text = titleText,
        modifier = modifier
            .padding(horizontal = phorizontal.dp, vertical = 10.dp)
            .fillMaxWidth(),
        style = textStyle,
        textAlign = TextAlign.Center,
        fontWeight = weight
    )
}

@Composable
fun Info(
    modifier: Modifier = Modifier,
    subtitle: String,
    content: String,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 50.dp, vertical = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = subtitle.uppercase(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = content,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun PortadaPreview() {
    Lab4jComposeTheme {
        Portada(modifier = Modifier.fillMaxSize())
    }
}