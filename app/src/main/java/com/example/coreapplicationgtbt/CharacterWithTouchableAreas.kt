// In CharacterWithTouchableAreas.kt

package com.example.coreapplicationgtbt

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun CharacterWithTouchableAreas(
    modifier: Modifier = Modifier,
    characterDrawableRes: Int,
    areas: List<TouchableArea>,
    onPrivateAreaTouched: (TouchableArea) -> Unit,
    onSafeAreaTouched: () -> Unit
) {
    // BoxWithConstraints is still needed to provide a max size for the inner Box
    BoxWithConstraints(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    // 👇 THIS IS THE KEY CHANGE 👇
                    // 'this.size' gives us the pixel size of the Box this modifier
                    // is attached to. This is a more direct approach inside pointerInput.
                    val boxWidthPx = this.size.width.toFloat()
                    val boxHeightPx = this.size.height.toFloat()

                    detectTapGestures { offset ->
                        // Protect against division by zero, just in case.
                        if (boxWidthPx == 0f || boxHeightPx == 0f) return@detectTapGestures

                        val normalizedOffset = Offset(
                            x = offset.x / boxWidthPx,
                            y = offset.y / boxHeightPx
                        )

                        val touchedArea = areas.find {
                            it.rect.contains(normalizedOffset)
                        }

                        when {
                            touchedArea?.isPrivate == true -> onPrivateAreaTouched(touchedArea)
                            touchedArea?.isPrivate == false -> onSafeAreaTouched()
                        }
                    }
                }
        ) {
            Image(
                painter = painterResource(id = characterDrawableRes),
                contentDescription = "Character",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}