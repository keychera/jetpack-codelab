package self.chera.jetpackcodelab

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import self.chera.jetpackcodelab.ui.theme.JetpackCodelabTheme

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    val count = 0
    Text(
        text = "You've had $count glasses.",
        modifier = modifier.padding(16.dp)
    )
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun StatePreview() {
    JetpackCodelabTheme {
        WaterCounter()
    }
}