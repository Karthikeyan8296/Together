package com.example.together

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.together.core.navigation.RootNavGraph
import com.example.together.core.navigation.Routes
import com.example.together.ui.theme.TogetherTheme
import com.example.together.ui.theme.backgroundPrimary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                Color.TRANSPARENT,
            )
        )
        setContent {
            //navController
            val navController = rememberNavController()

            TogetherTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = backgroundPrimary
                ) { innerPadding ->
                    RootNavGraph(
                        paddingValues = innerPadding,
                        navController = navController,
                        startDestination = Routes.AUTH_GRAPH
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TogetherTheme {}
}