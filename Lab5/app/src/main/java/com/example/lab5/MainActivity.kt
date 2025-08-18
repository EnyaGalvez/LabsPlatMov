@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lab5

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab5.ui.theme.Lab5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab5Theme {
                    AppComida()
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

    ) { innerPadding ->
        Column(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
    ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconoCircular()

                Text(
                    text = "Actualización disponible",
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.surface
                )

                // Botón de redireccionamiento a aplicacion en playstore
                val context = LocalContext.current

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    onClick = {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("https://play.google.com/store/apps/details?id=com.hidea.cat")
                    }
                    context.startActivity(intent)
                }) {
                    Text(
                        text = "Descargar",
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sabado",
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.headlineSmall

                )
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
            .size(30.dp)
            .background(MaterialTheme.colorScheme.secondary, CircleShape)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.outline_update_24),
            contentDescription = "Actualizar",
            tint = MaterialTheme.colorScheme.surface,
            modifier = modifier
                .background(MaterialTheme.colorScheme.secondary, CircleShape)
                .padding(5.dp)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun AppComidaPreview() {
    Lab5Theme {
        AppComida(modifier = Modifier.fillMaxSize())
    }
}