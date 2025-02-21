package com.example.xspand_app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun Header(hText:String,color: Color,modifier: Modifier=Modifier){
    Row(
        modifier= modifier
            .fillMaxWidth()
            .background(color),
        horizontalArrangement = Arrangement.Center
    ){
        Text(hText, fontSize = 42.sp)
    }
}