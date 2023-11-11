package com.example.jetpack_searchable_dropdown

import CustomDropdown
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_searchable_dropdown.ui.theme.JetpackSearchableDropdownTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackSearchableDropdownTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val items = listOf(
                        "Robert Johnson",
                        "Emily Davis",
                        "Michael Thompson",
                        "Olivia Martinez",
                        "William Brown"
                    )
                    Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                        Box(
                            Modifier
                                .background(
                                    color = Color.Companion.White,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(horizontal = 30.dp)
                        ) {
                            Text(
                                text = "Select item",
                                color = Color.Black,
                                modifier = Modifier.align(
                                    Alignment.Center
                                )
                            )
                        }
                        CustomDropdown(items) { selectedItem ->
                            // Handle item selection
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
    JetpackSearchableDropdownTheme {
        Greeting("Android")
    }
}