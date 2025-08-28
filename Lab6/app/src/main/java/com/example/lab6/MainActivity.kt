package com.example.lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab6.ui.theme.Lab6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab6Theme {
                Counter(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Counter(modifier: Modifier = Modifier) {
    // variables
    var count by rememberSaveable { mutableStateOf(0) }

    val historySaver = listSaver<SnapshotStateList<Int>, Int>(
        save = { it.toList() },
        restore = { it.toMutableStateList() }
    )

    val history = rememberSaveable(saver = historySaver) { mutableStateListOf(0) }

    val stats by remember(history) {
        derivedStateOf {
            val diffs = history.zipWithNext { a, b -> b - a }
            Stats(
                inc = diffs.count { it > 0 },
                dec = diffs.count { it < 0 },
                max = history.maxOrNull() ?: count,
                min = history.minOrNull() ?: count,
                changes = diffs.size)
        }
    }

    val historyTrend by remember(history) {
        derivedStateOf {
            history.mapIndexed {
                i, v ->
                if (i == 0) v to true
                else v to (v > history[i - 1])
            }
        }
    }

    // Diseño general
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),
        contentPadding = PaddingValues(vertical = 25.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment =  Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Enya Gálvez",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(vertical = 15.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Button(
                        onClick = {
                            count--
                            history.add(count)
                        },
                        modifier = Modifier
                            .size(30.dp),
                        shape = CircleShape,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "-",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                    }

                    Text(
                        text = "$count",
                        style = MaterialTheme.typography.displayLarge,
                        modifier = Modifier.padding(horizontal = 14.dp)
                    )

                    Button(
                        onClick = {
                            count++
                            history.add(count)
                        },
                        modifier = Modifier.size(30.dp),
                        shape = CircleShape,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "+",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                HorizontalDivider( modifier = Modifier.padding(vertical = 5.dp) )

                StatLine( "Total incrementos: ", stats.inc )
                StatLine( "Total decrementos: ", stats.dec )
                StatLine( "Valor máximo: ", stats.max )
                StatLine( "Valor mínimo: ", stats.min )
                StatLine( "Total cambios: ", stats.changes )

                Text(
                    text = "Historial:",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 27.dp),
                    textAlign = TextAlign.Left
                )

                HistoryGrid(entries = historyTrend)
            }
        }
        item {
            Button(
                onClick = {
                    count = 0
                    history.clear()
                    history.add(0)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .padding(horizontal = 27.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Reiniciar")
            }
        }
    }
}

data class Stats( // Registro de datos especificos de estadisticas
    val inc: Int,
    val dec: Int,
    val max: Int,
    val min: Int,
    val changes: Int
)

@Composable
fun StatLine(
    dato: String,
    cantidad: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 27.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = dato,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "$cantidad",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
fun History(
    n: Int,
    ok: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(44.dp)
            .width(25.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(if (ok) Color(0xFF1a7d28)
                else Color(0xFFb3261e)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$n",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun HistoryGrid(
    entries: List<Pair<Int, Boolean>>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
        
    ) {
        entries.drop(1).chunked(5).forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 25.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                row.forEach { (n, ok) ->
                    History(n = n, ok = ok, modifier = Modifier.weight(1f))
                }
                repeat(5 - row.size) {
                    Spacer(modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(1f)
                        .height(10.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab6Theme {
        Counter(modifier = Modifier.fillMaxSize())
    }
}