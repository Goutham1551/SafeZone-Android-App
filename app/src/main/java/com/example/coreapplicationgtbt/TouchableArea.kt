package com.example.coreapplicationgtbt

import androidx.compose.ui.geometry.Rect

data class TouchableArea(
    val name: String,
    val rect: Rect,
    val isPrivate: Boolean = false
)

val girlPrivateAreas = listOf<TouchableArea>(
    TouchableArea(
        name = "Lips",
        rect = Rect(0.47f, 0.26f, 0.54f, 0.3f),
        isPrivate = true
    ),
    TouchableArea(
        name = "Chest",
        // Rect: left, top, right, bottom as fractions of the image
        rect = Rect(0.4f, 0.35f, 0.625f, 0.45f),
        isPrivate = true
    ),
    TouchableArea(
        name = "Between Legs",
        rect = Rect(0.35f, 0.5f, 0.67f, 0.7f),
        isPrivate = true
    ),
    TouchableArea(
        name = "Between head to toe",
        rect = Rect(0.35f, 0.1f, 0.67f, 0.94f),
        isPrivate = false
    ),
    TouchableArea(
        name = "hair",
        rect = Rect(0.25f, 0.22f, 0.78f, 0.37f),
        isPrivate = false
    ),
    TouchableArea(
        name = "left Hand",
        rect = Rect(0.05f, 0.45f, 0.2f,0.53f,),
        isPrivate = false
    ),
    TouchableArea(
        name = "left arm to right arm",
        rect = Rect(0.2f, 0.32f, 0.8f,0.53f),
    ),
    TouchableArea(
        name = "right Hand",
        rect = Rect(0.8f, 0.45f, 0.98f,0.53f,),
        isPrivate = false
    )
)
val boyPrivateAreas = listOf<TouchableArea>(
    TouchableArea(
        name = "Lips",
        rect = Rect(0.44f, 0.26f, 0.54f, 0.3f),
        isPrivate = true
    ),
    TouchableArea(
        name = "Chest",
        // Rect: left, top, right, bottom as fractions of the image
        rect = Rect(0.37f, 0.38f, 0.625f, 0.45f),
        isPrivate = true
    ),
    TouchableArea(
        name = "Between Legs",
        rect = Rect(0.31f, 0.5f, 0.67f, 0.7f),
        isPrivate = true
    ),
    TouchableArea(
        name = "Between head to toe",
        rect = Rect(0.3f, 0.09f, 0.67f, 0.94f),
        isPrivate = false
    )
)