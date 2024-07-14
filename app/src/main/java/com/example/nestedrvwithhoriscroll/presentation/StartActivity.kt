package com.example.nestedrvwithhoriscroll.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nestedrvwithhoriscroll.ui.theme.NestedRVWithHoriScrollTheme

class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NestedRVWithHoriScrollTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .padding(40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(
                            14.dp,
                            Alignment.CenterVertically
                        )
                    ) {
                        Button(onClick = {
                            startActivity(Intent(this@StartActivity, RVActivity::class.java))
                        }, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "EpoxyActivity")
                        }
                        Button(onClick = {
                            startActivity(Intent(this@StartActivity, ComposeActivity::class.java))
                        }, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "ComposeActivity")
                        }
                        Button(onClick = {
                            startActivity(Intent(this@StartActivity, MainActivity::class.java))
                        }, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "MainActivity")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NestedRVWithHoriScrollTheme {
        Greeting("Android")
    }
}