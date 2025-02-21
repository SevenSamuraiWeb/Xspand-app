package com.example.xspand_app.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun SplashScreen(modifier: Modifier =Modifier,navigateToLogin:()->Unit){
    LaunchedEffect(Unit) {
        delay(3000)
        navigateToLogin()
    }

    Column(
        modifier=Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .size(256.dp)
                .align(Alignment.End)
                .padding(bottom = 64.dp),
            painter = painterResource(R.drawable.ellipse_3),
            contentDescription = null
        )
        Row(modifier=Modifier.fillMaxWidth().padding(vertical=50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                modifier = Modifier.size(128.dp).padding(2.dp),
                painter = painterResource(R.drawable.logo),
                contentDescription = null
            )
            Text(
                text="spand",
                fontSize = 50.sp,
                color = Color(0xFF48A6A7)
            )
        }
            Image(
                modifier = Modifier
                    .size(256.dp)
                    .align(Alignment.Start)
                    .padding(top=30.dp),
                painter = painterResource(R.drawable.ellipse_4),
                contentDescription = null
            )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen{}
}