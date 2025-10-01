// In CharacterWithTouchableAreas.kt

package com.example.coreapplicationgtbt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun CharacterWithTouchableAreas(
    modifier: Modifier = Modifier,
    characterDrawableRes: Int,
    areas: List<TouchableArea>,
    onPrivateAreaTouched: (TouchableArea) -> Unit,
    onSafeAreaTouched: () -> Unit,
    showDebugOverlays: Boolean = false // <-- NEW PARAMETER
) {
    BoxWithConstraints(modifier = modifier) {
        // This Box handles the touch input invisibly
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    // ... your existing pointerInput logic ...
                    val boxWidthPx = this.size.width.toFloat()
                    val boxHeightPx = this.size.height.toFloat()
                    detectTapGestures { offset ->
                        if (boxWidthPx == 0f || boxHeightPx == 0f) return@detectTapGestures
                        val normalizedOffset = Offset(
                            x = offset.x / boxWidthPx,
                            y = offset.y / boxHeightPx
                        )
                        val touchedArea = areas.find { it.rect.contains(normalizedOffset) }
                        when {
                            touchedArea?.isPrivate == true -> onPrivateAreaTouched(touchedArea)
                            touchedArea?.isPrivate == false -> onSafeAreaTouched()
                        }
                    }
                }
        ) {
            // The character image is drawn here
            Image(
                painter = painterResource(id = characterDrawableRes),
                contentDescription = "Character",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // 👇 THIS IS THE NEW PART 👇
        // Draw the visual debugging overlays on top ONLY if the flag is true
        if (showDebugOverlays) {
            areas.forEach { area ->
                Box(
                    modifier = Modifier
                        .offset(
                            x = this.maxWidth * area.rect.left,
                            y = this.maxHeight * area.rect.top
                        )
                        .size(
                            width = this.maxWidth * area.rect.width,
                            height = this.maxHeight * area.rect.height
                        )
                        .background(
                            if (area.isPrivate) Color.Red.copy(alpha = 0.4f)
                            else Color.Green.copy(alpha = 0.4f)
                        )
                )
            }
        }
    }
}