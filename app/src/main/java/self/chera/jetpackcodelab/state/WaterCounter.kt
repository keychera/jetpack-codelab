package self.chera.jetpackcodelab.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import self.chera.jetpackcodelab.ui.theme.JetpackCodelabTheme


@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    WaterCounter(modifier)
}

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }

    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            var showTask by remember { mutableStateOf(true) }
            if (showTask) {
                WellnessTaskItem(
                    onClose = { showTask = false },
                    taskName = "Have you taken your 15 minute walk today?"
                )
            }
            Text(text = "You've had $count glasses.")
        }
        Row(Modifier.padding(top = 8.dp)) {
            Button(onClick = { count++ }) {
                Text("Add one")
            }
            Button(onClick = { count = 0 }, Modifier.padding(start = 8.dp)) {
                Text("Clear water count")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun StatePreview() {
    JetpackCodelabTheme {
        WellnessScreen()
    }
}