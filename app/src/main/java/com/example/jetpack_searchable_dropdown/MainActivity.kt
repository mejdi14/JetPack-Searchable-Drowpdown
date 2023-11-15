package com.example.jetpack_searchable_dropdown

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.KeyframesSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
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

            Box(modifier = Modifier.align(alignment = Alignment.CenterEnd)) {
                AnimatedIcon(rotationAngle, expanded)
            }

        }
        Spacer(modifier = Modifier.height(10.dp))

        // Animated dropdown content
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(
                animationSpec = tween(durationMillis = 3000)
            ) + fadeIn(
                initialAlpha = 0.3f,
                animationSpec = tween(durationMillis = 3000)
            ),
            exit = shrinkVertically(
                animationSpec = tween(durationMillis = 3000)
            ) + fadeOut(
                animationSpec = tween(durationMillis = 3000)
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
    private fun AnimatedIcon(rotationAngle: Dp, expanded: Boolean) {
        val scale = remember { Animatable(1f) }
        val alpha = remember { Animatable(1f) }

        LaunchedEffect(expanded) {
            if (expanded) {
                scale.animateTo(0.1f, animationSpec = TweenSpec(durationMillis = 500))
                scale.animateTo(1f, animationSpec = TweenSpec(durationMillis = 500))
                alpha.animateTo(0.5f, animationSpec = TweenSpec(durationMillis = 500))
                alpha.animateTo(1f, animationSpec = TweenSpec(durationMillis = 500))
            } else {
                scale.animateTo(0.1f, animationSpec = TweenSpec(durationMillis = 500))
                scale.animateTo(1f, animationSpec = TweenSpec(durationMillis = 500))
                alpha.animateTo(0.5f, animationSpec = TweenSpec(durationMillis = 500))
                alpha.animateTo(1f, animationSpec = TweenSpec(durationMillis = 500))
            }
        }
        Image(
            painter = painterResource(id = R.drawable.expand_less),
            contentDescription = "Toggle Dropdown",
            modifier = Modifier
                .scale(scale.value)
                .alpha(alpha.value)
                .rotate(rotationAngle.value, )
        )
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun enterTransition(): EnterTransition {
        val totalDuration = 2000 // Total duration for both animations

        // Define keyframes for the scale animation
        val scaleAnimation: KeyframesSpec<Float> = keyframes {
            durationMillis = totalDuration
            0f at 0 with LinearOutSlowInEasing // Start with shrink
            0f at 1000 with LinearOutSlowInEasing // End shrink at 1000ms
            1f at totalDuration with LinearOutSlowInEasing // Scale up to original size at 2000ms
        }

        return scaleIn(
            animationSpec = scaleAnimation,
            initialScale = 0f
        )
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

@Composable
fun CustomAnimatedVisibility(
    visible: Boolean,
    content: @Composable () -> Unit
) {
    // Create an Animatable for scale and alpha, default to the end values of the shrink and fade in
    val scale = remember { Animatable(0.8f) }
    val alpha = remember { Animatable(0f) }

    // Handle the enter animation sequence
    LaunchedEffect(visible) {
        if (visible) {
            // Animate from shrink and faded to expanded and visible
            scale.animateTo(1f, animationSpec = tween(300))
            alpha.animateTo(1f, animationSpec = tween(300))
        }
    }

    // Use the updateTransition API to handle enter and exit together
    val transition = updateTransition(targetState = visible, label = "visibilityTransition")
    val animatedScale by transition.animateFloat(
        transitionSpec = { tween(300) },
        label = "scaleTransition"
    ) { state ->
        if (state) 1f else 0.8f
    }
    val animatedAlpha by transition.animateFloat(
        transitionSpec = { tween(300) },
        label = "alphaTransition"
    ) { state ->
        if (state) 1f else 0f
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(200.dp)
            .background(Color.Gray)
    ) {
        if (visible) {
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = animatedScale * scale.value
                        scaleY = animatedScale * scale.value
                        this.alpha = animatedAlpha * alpha.value
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .size(100.dp)
            )
        }
    }
}

