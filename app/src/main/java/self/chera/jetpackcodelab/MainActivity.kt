@file:OptIn(ExperimentalMaterial3Api::class)

package self.chera.jetpackcodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import self.chera.jetpackcodelab.ui.theme.JetpackCodelabTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackCodelabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(
                        modifier = Modifier.padding(innerPadding),
                    )

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectCard(
    modifier: Modifier = Modifier,
    name: String = "a project",
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = name,
                modifier = Modifier.weight(1.0F)
            )
            Icon(Icons.Default.ArrowForward, "open")
        }
    }
}

@Composable
fun BrowseProjects(
    modifier: Modifier = Modifier,
    projects: List<Pair<String, () -> Unit>> = listOf()
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = projects) { (name, onClick) ->
            ProjectCard(name = name, onClick = onClick)
        }
    }
}


@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            BrowseProjects(
                modifier = modifier,
                projects = listOf(
                    Pair("basic-compose") { navController.navigate("basic-compose") },
                    Pair("state") { navController.navigate("state") },
                )
            )
        }
        composable("basic-compose") {
            BasicCompose(
                modifier = modifier,
                onClickBack = { navController.navigate("home") }
            )
        }
        composable("state") {
            BasicCompose(
                modifier = modifier,
                onClickBack = { navController.navigate("home") }
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)
@Composable
fun GreetingPreview() {
    JetpackCodelabTheme {
        MyApp()
    }
}