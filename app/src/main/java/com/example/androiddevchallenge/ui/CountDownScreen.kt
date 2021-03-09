package com.example.androiddevchallenge.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Easing
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.CountDownTheme

private enum class CountState { Clear, Ready }

@ExperimentalAnimationApi
@Composable
fun CountDownScreen() {
    var countState by remember { mutableStateOf(CountState.Clear) }

    LaunchedEffect("CountDownScreen") {
        countState = CountState.Ready
    }
    Box(Modifier.fillMaxSize(1f)) {
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
                onClick = { countState = CountState.Clear },
                modifier = Modifier
                    .align(Alignment.Center)
                    .wrapContentSize()
            ) {
                Text(text = "start")
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun CountDownScreenPreview() {
    CountDownTheme {
        CountDownScreen()
    }
}