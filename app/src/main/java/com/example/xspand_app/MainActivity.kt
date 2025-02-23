package com.example.xspand_app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.xspand_app.data.sampleXRayItems
import com.example.xspand_app.screen.radiologist.XrayPatientDetailsScreen
import com.example.xspand_app.ui.theme.XspandappTheme
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            XspandappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {  innerPadding ->
                    NavigationGraph(navController = navController, modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

