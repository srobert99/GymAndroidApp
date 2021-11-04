package com.example.gymappandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gymappandroid.ui.theme.GymAppAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymAppAndroidTheme {
                GymAppNavGraph()
            }
        }
    }
}


