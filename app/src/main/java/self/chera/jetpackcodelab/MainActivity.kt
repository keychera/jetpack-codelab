@file:OptIn(ExperimentalMaterial3Api::class)

package self.chera.jetpackcodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = "Expanding animation"
    )

    Card(
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(24.dp),
        ) {
            Column(
                modifier = modifier
                    .weight(1.0F)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello ")
                Text(text = name, style = MaterialTheme.typography.labelLarge)
            }
            ElevatedButton(
                onClick = { expanded = !expanded }
            ) {
                Text(if (expanded) "Show less" else "Show more")
            }

        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "Test Subject #$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) {
            Greeting(it)
        }
    }
}

@Composable
fun BasicCompose(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit = {}
) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        Column {
            IconButton(onClick = onClickBack) {
                Icon(Icons.Default.ArrowBack, "back")
            }
            if (shouldShowOnboarding) {
                OnboardingScreen(onContinueClicked = {
                    shouldShowOnboarding = false
                })
            } else {
                Greetings()
            }
        }

    }
}

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
                )
            )
        }
        composable("basic-compose") {
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
        BrowseProjects(
            projects = listOf(
                Pair("basic-compose") { },
                Pair("basic-compose") { },
            )
        )
    }
}