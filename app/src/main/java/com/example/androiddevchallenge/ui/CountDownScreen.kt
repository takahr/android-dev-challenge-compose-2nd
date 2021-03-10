package com.example.androiddevchallenge.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.CountDownTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds

val barImageVector = ImageVector.Builder(defaultHeight = 100f.dp, defaultWidth = 100f.dp, viewportWidth = 100f, viewportHeight = 100f).apply {
    group(rotate = 45f, pivotX = 50f, pivotY = 50f) {
        path(stroke = SolidColor(Color.Black), strokeLineWidth = 0.3f) {
            moveTo(45f,45f)
            lineTo(0f, 0f)
        }
    }
}.build()

val circleImageVector = ImageVector.Builder(defaultHeight = 100f.dp, defaultWidth = 100f.dp, viewportWidth = 100f, viewportHeight = 100f).apply {
    group {
        path(stroke = SolidColor(Color.Black), strokeLineWidth = 1.5f) {
            moveTo(45f, 45f)
            arcTo(
                horizontalEllipseRadius = 7.7f,
                verticalEllipseRadius = 7.7f,
                theta = 0f, isMoreThanHalf = false, isPositiveArc = true,
                x1 = 55f, y1 = 45f)
            arcTo(
                horizontalEllipseRadius = 7.7f,
                verticalEllipseRadius = 7.7f,
                theta = 0f, isMoreThanHalf = false, isPositiveArc = true,
                x1 = 55f, y1 = 55f)
            arcTo(
                horizontalEllipseRadius = 7.7f,
                verticalEllipseRadius = 7.7f,
                theta = 0f, isMoreThanHalf = false, isPositiveArc = true,
                x1 = 45f, y1 = 55f)
            arcTo(
                horizontalEllipseRadius = 7.7f,
                verticalEllipseRadius = 7.7f,
                theta = 0f, isMoreThanHalf = false, isPositiveArc = true,
                x1 = 45f, y1 = 45f)
            close()
        }

        path(stroke = SolidColor(Color.Black), strokeLineWidth = 0.3f) {
            moveTo(41f, 50f)
            arcTo(
                horizontalEllipseRadius = 9f,
                verticalEllipseRadius = 9f,
                theta = 0f, isMoreThanHalf = false, isPositiveArc = true,
                x1 = 50f, y1 = 41f
            )
            arcTo(
                horizontalEllipseRadius = 9f,
                verticalEllipseRadius = 9f,
                theta = 0f, isMoreThanHalf = false, isPositiveArc = true,
                x1 = 59f, y1 = 50f
            )
            arcTo(
                horizontalEllipseRadius = 9f,
                verticalEllipseRadius = 9f,
                theta = 0f, isMoreThanHalf = false, isPositiveArc = true,
                x1 = 50f, y1 = 59f
            )
            arcTo(
                horizontalEllipseRadius = 9f,
                verticalEllipseRadius = 9f,
                theta = 0f, isMoreThanHalf = false, isPositiveArc = true,
                x1 = 41f, y1 = 50f
            )
            close()
        }
    }
}.build()

fun ImageVector.Builder.flowerPetalPath(rotate: Float) {
    group(rotate = rotate, pivotX = 50f, pivotY = 50f) {
        path(stroke = SolidColor(Color.Black), fill = SolidColor(Color.Black)) {
            moveTo(50f, 45f)
            arcTo(
                horizontalEllipseRadius = 22f,
                verticalEllipseRadius = 22f,
                theta = 0f,
                isMoreThanHalf = false,
                isPositiveArc = true,
                x1 = 45f,
                y1 = 10f
            )
            lineTo(50f, 15f)
            lineTo(55f, 10f)
            arcTo(
                horizontalEllipseRadius = 22f,
                verticalEllipseRadius = 22f,
                theta = 0f,
                isMoreThanHalf = false,
                isPositiveArc = true,
                x1 = 50f,
                y1 = 45f
            )
        }
    }
}

val flowerImageVector = ImageVector.Builder(defaultHeight = 100f.dp, defaultWidth = 100f.dp, viewportWidth = 100f, viewportHeight = 100f).apply {
    group(pivotX = 50f, pivotY = 50f) {
        flowerPetalPath(0f)
        flowerPetalPath(72f)
        flowerPetalPath(144f)
        flowerPetalPath(216f)
        flowerPetalPath(288f)
    }
}.build()

@ExperimentalAnimationApi
@Composable
fun CountView(count: Int, visible: Boolean) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(1f, animationSpec = tween(10)),
        exit = fadeOut(0f, tween(0))
    ) {
        var rotateDegree by remember { mutableStateOf(0f)}
        val rotateAnim: Float by animateFloatAsState(targetValue = rotateDegree, animationSpec = tween(durationMillis = 900, easing = LinearEasing))

        LaunchedEffect(key1 = "rotateDegree") {
            rotateDegree = 360f
        }

        Box {
            Image(
                imageVector = barImageVector,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(rotateAnim))
            Image(
                imageVector = circleImageVector,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize())
            Text(text = count.toString(), fontSize = 60.sp, modifier = Modifier.align(Alignment.Center))
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun CountViewPreview() {
    CountView(count = 3, visible = true)
}

@ExperimentalAnimationApi
@Composable
fun SingleFlower(modifier: Modifier = Modifier) {
    val sat = 0.13f + (Random.nextFloat() - 0.5f) * 0.08f
    val v = 0.98f + (Random.nextFloat() - 0.5f) * 0.08f
    val tintColor = Color(android.graphics.Color.HSVToColor(floatArrayOf(352f, sat, v)))
    val rotate = Random.nextFloat() * 360f
    val delay = (Random.nextFloat() * 1200).toInt()
    val scale = Random.nextFloat() * 0.4f + 0.6f
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(0f, tween(200, delay)),
        modifier = modifier
    ) {
        Image(
            imageVector = flowerImageVector,
            colorFilter = ColorFilter.tint(tintColor),
            contentDescription = null,
            modifier = Modifier.rotate(rotate).scale(scale)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun Flower(visible: Boolean) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(1f, animationSpec = tween(10)),
        exit = fadeOut(0f, tween(0)),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            for (i in 1..8) {
                SingleFlower(Modifier.absoluteOffset((Random.nextFloat() * 300).dp, (Random.nextFloat() * 100).dp).align(Alignment.TopStart))
                SingleFlower(Modifier.absoluteOffset((Random.nextFloat() * 300).dp, (Random.nextFloat() * 100).dp).align(Alignment.TopEnd))
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun FlowerPreview() {
    Flower(visible = true)
}

private enum class CountState { Clear, Start, Ready, Count3, Count2, Count1, Launch }

@ExperimentalTime
@ExperimentalAnimationApi
@Composable
fun CountDownScreen() {
    var countState by remember { mutableStateOf(CountState.Clear) }
    val coroutineScope = rememberCoroutineScope()

    val countDown: ()->Unit = {
        coroutineScope.launch {
            countState = CountState.Ready
            delay(1000.milliseconds)
            countState = CountState.Count3
            delay(1000.milliseconds)
            countState = CountState.Count2
            delay(1000.milliseconds)
            countState = CountState.Count1
            delay(1000.milliseconds)
            countState = CountState.Launch
            delay(5000.milliseconds)
            countState = CountState.Clear
            delay(100.milliseconds)
            countState = CountState.Start
        }
    }

    LaunchedEffect("CountDownScreen") {
        countState = CountState.Start
    }
    Box(Modifier.fillMaxSize(1f)) {
        // start button for Ready state.
        AnimatedVisibility(
            visible = countState == CountState.Start,
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
        CountView(count = 3, visible = countState == CountState.Count3)
        CountView(count = 2, visible = countState == CountState.Count2)
        CountView(count = 1, visible = countState == CountState.Count1)
        // flower
        Flower(visible = countState == CountState.Launch)
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
