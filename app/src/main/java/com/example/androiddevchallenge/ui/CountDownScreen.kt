package com.example.androiddevchallenge.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.CountDownTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds

private enum class CountState { Clear, Ready, Count3, Count2, Count1, Launch }

@ExperimentalAnimationApi
@Composable
fun CountView(count: Int, visible: Boolean, modifier: Modifier = Modifier) {
    AnimatedVisibility(visible = visible, modifier = modifier.fillMaxSize()) {
        Box {
            Text(text = count.toString(), modifier = Modifier.align(Alignment.Center))
        }
    }
}

@ExperimentalTime
@ExperimentalAnimationApi
@Composable
fun CountDownScreen() {
    var countState by remember { mutableStateOf(CountState.Clear) }
    val coroutineScope = rememberCoroutineScope()

    val countDown: ()->Unit = {
        coroutineScope.launch {
            countState = CountState.Count3
            delay(1000.milliseconds)
            countState = CountState.Count2
            delay(1000.milliseconds)
            countState = CountState.Count1
            delay(1000.milliseconds)
            countState = CountState.Launch
            delay(1000.milliseconds)
            countState = CountState.Clear
            delay(100.milliseconds)
            countState = CountState.Ready
        }
    }

    LaunchedEffect("CountDownScreen") {
        countState = CountState.Ready
    }
    Box(Modifier.fillMaxSize(1f)) {
        // start button for Ready state.
        AnimatedVisibility(
            visible = countState == CountState.Ready,
            enter = slideInVertically(
                initialOffsetY = { it / 4 },
                animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing))
                + fadeIn(),
            exit = slideOutVertically(
                targetOffsetY = { it / 4 },
                animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing))
                + fadeOut(),
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = countDown,
                modifier = Modifier
                    .align(Alignment.Center)
                    .wrapContentSize()
            ) {
                Text(text = "start")
            }
        }
        // countdown views
        CountView(count = 3, visible = countState == CountState.Count3, modifier = Modifier.fillMaxSize())
        CountView(count = 2, visible = countState == CountState.Count2, modifier = Modifier.fillMaxSize())
        CountView(count = 1, visible = countState == CountState.Count1, modifier = Modifier.fillMaxSize())
    }
}

@ExperimentalTime
@ExperimentalAnimationApi
@Preview
@Composable
fun CountDownScreenPreview() {
    CountDownTheme {
        CountDownScreen()
    }
}