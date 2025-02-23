package com.example.xspand_app.screen.setup

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xspand_app.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(modifier: Modifier = Modifier, navigateToLogin: () -> Unit) {
    var offset = remember{ androidx.compose.animation.core.Animatable(-70f) }

    LaunchedEffect(Unit) {
        delay(500)
        offset.animateTo(
            targetValue=0f,
            tween(1000)
        )
        delay(3000)
        navigateToLogin()
    }
    
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = modifier.padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
                .padding(0.dp)
        ) {
            Image(painter = painterResource(R.drawable.background), contentDescription = null,
                modifier=Modifier.fillMaxSize())

            // Logo and text
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 50.dp)
                    .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(128.dp)
                        .padding(2.dp),
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "App Logo"
                )
                Text(
                    text = "spand",
                    fontSize = 50.sp,
                    color = Color(0xFF48A6A7),
                    modifier=Modifier.offset(x=offset.value.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen {}
}