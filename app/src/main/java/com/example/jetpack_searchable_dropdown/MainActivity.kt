package com.example.jetpack_searchable_dropdown

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpack_searchable_dropdown.ui.theme.JetpackSearchableDropdownTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackSearchableDropdownTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.error
                ) {
                    Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                        SelectYourSkill()
                        Spacer(modifier = Modifier.height(30.dp))
                        SelectYourSkill()
                    }
                }
            }
        }
    }

    @Composable
    private fun SelectYourSkill() {
        var expanded by remember { mutableStateOf(false) }
        val rotationAngle by animateDpAsState(targetValue = if (expanded) 180.dp else 0.dp)

        Box(
            Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(horizontal = 30.dp)
        ) {
            Text(
                text = "Select your skill",
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(vertical = 16.dp)
            )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Toggle Dropdown",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .rotate(rotationAngle.value)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Animated dropdown content
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(
                animationSpec = tween(durationMillis = 300)
            ) + fadeIn(
                initialAlpha = 0.3f,
                animationSpec = tween(durationMillis = 300)
            ),
            exit = shrinkVertically(
                animationSpec = tween(durationMillis = 300)
            ) + fadeOut(
                animationSpec = tween(durationMillis = 300)
            )
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .background(Color.White, RoundedCornerShape(10.dp))
            ) {
                // Repeat for the number of skills
                SkillItem("Skill 1")
                SkillItem("Skill 2")
                // ... add more items as needed
            }
        }
    }

    @Composable
    private fun SkillItem(skill: String) {
        Text(
            text = skill,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* Handle item click */ }
                .padding(16.dp),
            color = Color.Black
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

