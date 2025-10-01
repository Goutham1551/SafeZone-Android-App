package com.example.coreapplicationgtbt

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coreapplicationgtbt.ui.theme.CoreApplicationGTBTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoreApplicationGTBTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GameScreen(modifier: Modifier = Modifier){
    var isGirl by remember { mutableStateOf(true) }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Good / Bad Touch !!",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.background,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))

        val characterToShow = if (isGirl) R.drawable.girl else R.drawable.boy
        val areasToShow = if (isGirl) girlPrivateAreas else boyPrivateAreas

        CharacterWithTouchableAreas(
            modifier = Modifier.weight(1f),
            characterDrawableRes = characterToShow,
            areas = areasToShow,
            onPrivateAreaTouched = {
                // Play "bad touch" sound
                if(isGirl) playSound(context, R.raw.girlbt)
                else playSound(context,R.raw.boybt)// Assumes your file is bad_touch.mp3
            },
            onSafeAreaTouched = {
                // Play "good touch" sound
                if(isGirl) playSound(context, R.raw.girlgt)
                else playSound(context,R.raw.boygt) // Assumes your file is good_touch.mp3
            }
        )

        Spacer(modifier = Modifier.padding(16.dp))

        CustomSquareSwitch(
            checked = !isGirl,
            onCheckedChange = { isGirl = !it },
            width = 120.dp,
            height = 60.dp,
            thumbSize = 52.dp,
            checkedIcon = Icons.Filled.Male,
            uncheckedIcon = Icons.Filled.Female,
            checkedTrackColor = Color(0xFFBBDEFB),
            uncheckedTrackColor = Color(0xFFF8BBD0),
            checkedThumbColor = Color(0xFF0D47A1),
            uncheckedThumbColor = Color(0xFFC2185B)
        )
    }
}

private fun playSound(context: Context, soundResId: Int) {
    val mediaPlayer = MediaPlayer.create(context, soundResId)
    mediaPlayer.setOnCompletionListener { mp -> mp.release() }
    mediaPlayer.start()
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoreApplicationGTBTTheme {
        GameScreen()
    }
}