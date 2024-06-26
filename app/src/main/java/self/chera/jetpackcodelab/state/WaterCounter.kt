package self.chera.jetpackcodelab.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import self.chera.jetpackcodelab.ui.theme.JetpackCodelabTheme


@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableIntStateOf(0) }
    Column {

        WaterCounter(count, { count++ }, { count = 0 } ,modifier)
        val wellnessViewModel: WellnessViewModel = viewModel()
        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCheckedTask = wellnessViewModel::changeTaskChecked,
            onCloseTask = wellnessViewModel::remove
        )
    }
}

@Composable
fun WaterCounter(
    count: Int,
    onIncrement: () -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) Text(text = "You've had $count glasses.")
        Row(Modifier.padding(top = 8.dp)) {
            Button(onClick = onIncrement) {
                Text("Add one")
            }
            Button(onClick = onClear, Modifier.padding(start = 8.dp)) {
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