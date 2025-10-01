package com.example.coreapplicationgtbt

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CustomSquareSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    checkedIcon: ImageVector,
    uncheckedIcon: ImageVector,
    checkedTrackColor: Color,
    uncheckedTrackColor: Color,
    checkedThumbColor: Color,
    uncheckedThumbColor: Color,
    iconColor: Color = Color.White,
    width: Dp = 80.dp,
    height: Dp = 40.dp,
    thumbSize: Dp = 32.dp,
    gap: Dp = 4.dp,
    borderColor: Color = Color.Black, // <-- New parameter for border color
    borderWidth: Dp = 2.dp          // <-- New parameter for border width
) {
    val trackShape = RoundedCornerShape(8.dp)
    val thumbShape = RoundedCornerShape(6.dp)

    val thumbPosition by animateDpAsState(
        targetValue = if (checked) width - thumbSize - gap else gap,
        label = "Thumb Position"
    )

    val trackColor by animateColorAsState(
        targetValue = if (checked) checkedTrackColor else uncheckedTrackColor,
        label = "Track Color"
    )
    val thumbColor by animateColorAsState(
        targetValue = if (checked) checkedThumbColor else uncheckedThumbColor,
        label = "Thumb Color"
    )

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .width(width)
            .size(height)
            .clip(trackShape)
            .background(trackColor)
            .border(borderWidth, borderColor, trackShape) // <-- Add border here!
            .clickable { onCheckedChange(!checked) }
    ) {
        // This is the Thumb
        Box(
            modifier = Modifier
                .offset(x = thumbPosition)
                .size(thumbSize)
                .clip(thumbShape)
                .background(thumbColor)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (checked) checkedIcon else uncheckedIcon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(thumbSize * 0.7f)
            )
        }
    }
}
